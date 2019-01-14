package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.ChangePasswordBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.iv_change_password_back)
    ImageView ivChangePasswordBack;
    @BindView(R.id.iv_change_password_changepass)
    TextView ivChangePasswordChangepass;
    EditText et_load, et_new, et_new1;
    private ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        et_load = findViewById(R.id.CP_loadpas);
        et_new = findViewById(R.id.CP_newpas);
        et_new1 = findViewById(R.id.CP_newpas1);
        aCache = ACache.get(this);
    }

    @OnClick({R.id.iv_change_password_back, R.id.iv_change_password_changepass, R.id.CP_loadpas_delte, R.id.CP_newpas1_delte, R.id.CP_newpas_delte})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_change_password_back:
                finish();
                break;
            case R.id.iv_change_password_changepass://修改密码
                if (TextUtils.isEmpty(et_load.getText().toString())) {
                    Toast.makeText(this, "请输入旧密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_new.getText().toString())) {
                    Toast.makeText(this, "新密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_new1.getText().toString())) {
                    Toast.makeText(this, "在次输入您的新密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!et_new.getText().toString().equals(et_new1.getText().toString())) {
                    Toast.makeText(this, "您输入的两个新密码不一直！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!et_new1.getText().toString().equals(et_new.getText().toString())) {
                    Toast.makeText(this, "您输入的两个新密码不一直！", Toast.LENGTH_SHORT).show();
                    return;
                }
                ChangePassword(et_load.getText().toString(), et_new.getText().toString(), et_new1.getText().toString(), aCache.getAsString("user_id"));
                break;
            case R.id.CP_loadpas_delte:
                et_load.setText("");
                break;
            case R.id.CP_newpas1_delte:
                et_new1.setText("");
                break;
            case R.id.CP_newpas_delte:
                et_new.setText("");
                break;
        }
    }


    //更改密码
    public void ChangePassword(String old, String psNew, String repeat, String user_id) {
        HttpHelper.getChangePassword(old, psNew, repeat, user_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ChangePasswordActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ChangePasswordBean entity = FastJSONHelper.getPerson(succeed, ChangePasswordBean.class);
                if (entity.getCode() == 0) {
                    Toast.makeText(ChangePasswordActivity.this, "修改成功!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setClass(ChangePasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    aCache.clear();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ChangePasswordActivity.this, error, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
