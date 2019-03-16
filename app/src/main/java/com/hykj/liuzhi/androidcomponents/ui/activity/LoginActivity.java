package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.orhanobut.logger.Logger;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2018/10/12.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_login_forgetpassword)
    TextView mTvLoginForgetpassword;
    @BindView(R.id.tv_login_dongcode2login)
    TextView mTvLoginDongcode2login;
    @BindView(R.id.tv_login_toregist)
    TextView tvLoginToregist;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.tv_login_login)
    TextView tvLoginLogin;
    private String mLoginPhone;
    private String mLoginPass;
    private Object userself;
    ZLoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        ButterKnife.bind(this);

    }

    private void initView() {
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据加载中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
    }

    @OnClick({R.id.tv_login_forgetpassword, R.id.tv_login_dongcode2login, R.id.tv_login_toregist, R.id.tv_login_login})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_login_forgetpassword://忘记密码
                intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_dongcode2login://动态密码登录
                intent = new Intent(LoginActivity.this, DongStateCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_toregist://注册
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_login:
                UserLogin();
                break;
        }

    }

    ACache aCache;

    //登陆操作
    private void UserLogin() {
        aCache = ACache.get(this);
        mLoginPhone = etLoginPhone.getText().toString().trim();
        mLoginPass = etLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(mLoginPhone) || TextUtils.isEmpty(mLoginPass)) {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            dialog.show();
            HttpHelper.PhonePassWordLoGin(mLoginPhone, mLoginPass, new HttpHelper.HttpUtilsCallBack<String>() {
                @Override
                public void onFailure(String failure) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this,failure, Toast.LENGTH_SHORT).show();
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
                    } else {
                        dialog.dismiss();
                    }
                }

                @Override
                public void onError(String error) {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this,error, Toast.LENGTH_SHORT).show();
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
            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                LocalInfoUtils.saveUserself(succeed);
                aCache.put("mLoginPhone", mLoginPhone);
                aCache.put("mLoginPass", mLoginPass);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this,"用户信息获取失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
