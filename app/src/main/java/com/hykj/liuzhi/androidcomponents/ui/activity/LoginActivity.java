package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        ButterKnife.bind(this);

    }

    private void initView() {

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
            mLoginPass = "";
            HttpHelper.login(mLoginPhone, mLoginPass, new HttpHelper.HttpUtilsCallBack<String>() {
                @Override
                public void onFailure(String failure) {
                    Toast.makeText(LoginActivity.this, failure, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LoginActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    //获取用户数据
    public void getUserself(int user_id) {
        Log.e("aa", "---------" + user_id);
        HttpHelper.getUserself(user_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(LoginActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                LocalInfoUtils.saveUserself(succeed);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onError(String error) {
                Toast.makeText(LoginActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
