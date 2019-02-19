package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import static com.zhouyou.http.EasyHttp.getContext;

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
    ZLoadingDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loding);
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据加载中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        aCache = ACache.get(this);
        if (aCache.getAsString("mLoginPhone") != null) {
            mLoginPhone = aCache.getAsString("mLoginPhone");
            mLoginPass = aCache.getAsString("mLoginPass");
            Debug.e("------------------");
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
            dialog.show();
            HttpHelper.PhonePassWordLoGin(mLoginPhone, mLoginPass, new HttpHelper.HttpUtilsCallBack<String>() {
                @Override
                public void onFailure(String failure) {
                    dialog.dismiss();
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
                        Debug.e("-----------登录成功--");
                        getUserself(entity.getUserdata().getUser_id());
                    }else {
                        dialog.dismiss();
                    }
                }

                @Override
                public void onError(String error) {
                    dialog.dismiss();
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
                dialog.dismiss();
                Toast.makeText(Act_Loding.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                LocalInfoUtils.saveUserself(succeed);
                dialog.dismiss();
                startActivity(new Intent(Act_Loding.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(Act_Loding.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
