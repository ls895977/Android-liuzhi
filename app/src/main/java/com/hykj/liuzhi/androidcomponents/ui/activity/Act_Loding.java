package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.bean.UserInfo;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;

public class Act_Loding extends BaseActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(Act_Loding.this, LoginActivity.class));
            finish();
        }
    };
    String mLoginPhone, mLoginPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loding);
        aCache = ACache.get(this);
        if (aCache.getAsString("mLoginPhone") != null) {
            mLoginPhone = aCache.getAsString("mLoginPhone");
            mLoginPass = aCache.getAsString("mLoginPass");
            UserLogin();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    ACache aCache;

    //登陆操作
    private void UserLogin() {
        if (TextUtils.isEmpty(mLoginPhone) || TextUtils.isEmpty(mLoginPass)) {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            HttpHelper.PhonePassWordLoGin(mLoginPhone, mLoginPass, new HttpHelper.HttpUtilsCallBack<String>() {
                @Override
                public void onFailure(String failure) {
                    Toast.makeText(Act_Loding.this, failure, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSucceed(String succeed) {
                    LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                    if (entity != null) {
                        UserInfo userInfo = LocalInfoUtils.getUserInfo();
                        LocalInfoUtils.saveUserInfo(mLoginPhone, userInfo.getCode(), mLoginPass);
                        LocalInfoUtils.saveUserdata(succeed);
                        aCache.put("user_id", String.valueOf(entity.getUserdata().getUser_id()));
                        getUserself(entity.getUserdata().getUser_id());
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(Act_Loding.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //获取用户数据
    public void getUserself(int user_id) {
        HttpHelper.getUserself(user_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(Act_Loding.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                LocalInfoUtils.saveUserself(succeed);
                startActivity(new Intent(Act_Loding.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(Act_Loding.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
