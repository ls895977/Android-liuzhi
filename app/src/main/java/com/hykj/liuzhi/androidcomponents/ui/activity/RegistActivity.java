package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.PhoneCodeBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity {
    @BindView(R.id.title_leftIco)
    ImageView titleLeftIco;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_rightIco)
    ImageView titleRightIco;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.et_regist_phone)
    EditText etRegistPhone;
    @BindView(R.id.et_regist_authcode)
    EditText etRegistAuthcode;
    @BindView(R.id.et_regist_password)
    EditText etRegistPassword;
    @BindView(R.id.tv_regist_registdeal)
    TextView tvRegistRegistdeal;
    @BindView(R.id.tv_regist_regist)
    TextView tvRegistRegist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
    }

    /*设置数据*/
    private void initData() {

    }


    @OnClick({R.id.et_regist_authcode, R.id.tv_regist_regist, R.id.et_regist_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_regist_authcode:
                break;
            case R.id.tv_regist_regist:
                saveUserData();
                break;
            case R.id.et_regist_button://验证码
                if (TextUtils.isEmpty(etRegistPhone.getText().toString().trim())) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                backPhotCode();
                break;
        }
    }

    private void saveUserData() {
        if (TextUtils.isEmpty(etRegistPhone.getText().toString().trim())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etRegistAuthcode.getText().toString().trim())) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(etRegistPassword.getText().toString().trim())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etRegistPassword.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "密码至少6位字符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!etRegistAuthcode.getText().toString().equals(code)) {
            Toast.makeText(this, "验证码不正确！", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.register(etRegistPhone.getText().toString().trim(), etRegistAuthcode.getText().toString().trim(), etRegistPassword.getText().toString().trim(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(RegistActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                LocalInfoUtils.saveUserInfo(etRegistPhone.getText().toString().trim(), etRegistAuthcode.getText().toString().trim(), etRegistPassword.getText().toString().trim());
                finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(RegistActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String code;
    public void backPhotCode() {
        HttpHelper.phonecode(etRegistPhone.getText().toString().trim(), "2", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(RegistActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                PhoneCodeBean entity = FastJSONHelper.getPerson(succeed, PhoneCodeBean.class);
                if (entity.getCode() == 0 && entity.getError() == 0) {
                    Toast.makeText(RegistActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    code = entity.getData().getCode();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(RegistActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
