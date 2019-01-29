package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.bean.PhoneCodeBean;
import com.hykj.liuzhi.androidcomponents.bean.UserInfo;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.hykj.liuzhi.androidcomponents.view.CountdownButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 动态密码登录
 * Created by user on 2018/10/12.
 */

public class DongStateCodeActivity extends BaseActivity {
    @BindView(R.id.et_dongtailogin_phone)
    EditText etDongtailoginPhone;
    @BindView(R.id.et_dongtailogin_authcode)
    EditText etDongtailoginAuthcode;
    @BindView(R.id.tv_dongtailogin_authcode)
    CountdownButton tvDongtailoginAuthcode;
    @BindView(R.id.tv_dongtailogin_login)
    TextView tvDongtailoginLogin;
    @BindView(R.id.tv_dongtailogin_forgetpassword)
    TextView tvDongtailoginForgetpassword;
    @BindView(R.id.tv_dongtailogin_pass2login)
    TextView tvDongtailoginPass2login;
    @BindView(R.id.tv_dongtailogin_toregist)
    TextView tvDongtailoginToregist;
    private String mLoginPhone;
    private String mLoginCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongstate_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }

    @OnClick({R.id.tv_dongtailogin_login, R.id.tv_dongtailogin_pass2login, R.id.tv_dongtailogin_forgetpassword, R.id.tv_dongtailogin_toregist
            , R.id.tv_dongtailogin_authcode, R.id.login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dongtailogin_login:
                UserLogin();
                break;
            case R.id.tv_dongtailogin_forgetpassword:
                startActivity(new Intent(DongStateCodeActivity.this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_dongtailogin_pass2login:
                startActivity(new Intent(DongStateCodeActivity.this, LoginActivity.class));
                break;
            case R.id.tv_dongtailogin_toregist:
                startActivity(new Intent(DongStateCodeActivity.this, RegistActivity.class));
                break;
            case R.id.tv_dongtailogin_authcode://获取验证码
                if (TextUtils.isEmpty(etDongtailoginPhone.getText().toString().trim())) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                backPhotCode();
                break;
            case R.id.login_back:
                finish();
                break;
        }
    }

    ACache aCache;

    private void UserLogin() {
        aCache = ACache.get(this);
        mLoginPhone = etDongtailoginPhone.getText().toString().trim();
        mLoginCode = etDongtailoginAuthcode.getText().toString().trim();
        if (TextUtils.isEmpty(mLoginPhone) || TextUtils.isEmpty(mLoginCode)) {
            Toast.makeText(this, "账号验证码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!etDongtailoginAuthcode.getText().toString().equals(code)) {
                Toast.makeText(this, "验证码不正确！", Toast.LENGTH_SHORT).show();
                return;
            }
            HttpHelper.login(mLoginPhone, mLoginCode, new HttpHelper.HttpUtilsCallBack<String>() {
                @Override
                public void onFailure(String failure) {
                    Toast.makeText(DongStateCodeActivity.this, failure, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSucceed(String succeed) {
                    LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                    if (entity != null) {
                        UserInfo userInfo = LocalInfoUtils.getUserInfo();
                        LocalInfoUtils.saveUserInfo(mLoginPhone, mLoginCode, userInfo.getPassword());
                        aCache.put("user_id", String.valueOf(entity.getUserdata().getUser_id()));
                        getUserself(entity.getUserdata().getUser_id());
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(DongStateCodeActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //获取用户数据
    public void getUserself(int user_id) {
        HttpHelper.getUserself(user_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(DongStateCodeActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                LocalInfoUtils.saveUserself(succeed);
                startActivity(new Intent(DongStateCodeActivity.this, MainActivity.class));
            }

            @Override
            public void onError(String error) {
                Toast.makeText(DongStateCodeActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String code;

    public void backPhotCode() {
        HttpHelper.phonecode(etDongtailoginPhone.getText().toString().trim(), "1", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(DongStateCodeActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                PhoneCodeBean entity = FastJSONHelper.getPerson(succeed, PhoneCodeBean.class);
                if (entity.getCode() == 0 && entity.getError() == 0) {
                    Toast.makeText(DongStateCodeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    code = entity.getData().getCode();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(DongStateCodeActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
