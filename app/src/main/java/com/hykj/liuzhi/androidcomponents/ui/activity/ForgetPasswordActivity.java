package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.ForgetpasswordBean;
import com.hykj.liuzhi.androidcomponents.bean.PhoneCodeBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.view.CountdownButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * Created by user on 2018/10/12.
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.et_forgetpassword_phone)
    EditText etForgetpasswordPhone;
    @BindView(R.id.et_forgetpassword_authcode)
    EditText etForgetpasswordAuthcode;
    @BindView(R.id.et_forgetpassword_password)
    EditText etForgetpasswordPassword;
    @BindView(R.id.tv_forgetpassword_resetpass)
    TextView tvForgetpasswordResetpass;
    @BindView(R.id.password_button)
    CountdownButton button;

    private String code = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_forgetpassword_resetpass, R.id.password_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forgetpassword_resetpass:
                requestData();
                break;
            case R.id.password_button://验证码
                if (TextUtils.isEmpty(etForgetpasswordPhone.getText().toString().trim())) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                backPhotCode();
                break;
            case R.id.forget_back://
                finish();
                break;
        }
    }

    private void requestData() {
        if (TextUtils.isEmpty(etForgetpasswordPhone.getText().toString().trim())) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etForgetpasswordAuthcode.getText().toString().trim())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(etForgetpasswordPassword.getText().toString().trim())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etForgetpasswordPassword.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "密码至少6位字符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!etForgetpasswordAuthcode.getText().toString().equals(code)) {
            Toast.makeText(this, "验证码不正确！", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.forgetpassword(etForgetpasswordPhone.getText().toString().trim(), etForgetpasswordPassword.getText().toString().trim(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ForgetPasswordActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ForgetpasswordBean entity = FastJSONHelper.getPerson(succeed, ForgetpasswordBean.class);
                if (entity.getCode() == 0 && entity.getError() == 0) {
                    Toast.makeText(ForgetPasswordActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ForgetPasswordActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backPhotCode() {
        HttpHelper.phonecode(etForgetpasswordPhone.getText().toString().trim(), "3", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ForgetPasswordActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                PhoneCodeBean entity = FastJSONHelper.getPerson(succeed, PhoneCodeBean.class);
                if (entity.getCode() == 0 && entity.getError() == 0) {
                    Toast.makeText(ForgetPasswordActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    code = entity.getData().getCode();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ForgetPasswordActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
