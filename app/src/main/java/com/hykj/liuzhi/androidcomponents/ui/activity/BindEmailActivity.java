package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

public class BindEmailActivity extends BaseActivity {
    @BindView(R.id.iv_bindemail_back)
    ImageView ivBindemailBack;
    @BindView(R.id.iv_bindemail_save)
    TextView ivBindemailSave;
    @BindView(R.id.ll_mine_bindemail_wait)
    LinearLayout llMineBindemailWait;
    @BindView(R.id.rl_mine_bindemail_input)
    RelativeLayout rlMineBindemailInput;
    @BindView(R.id.tv_mine_bindemail_descrip)
    TextView tvMineBindemailDescrip;
    @BindView(R.id.rl_mine_et_input)
    TextView edInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindemail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_bindemail_back, R.id.iv_bindemail_save, R.id.im_delte})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bindemail_back:
                finish();
                break;
            case R.id.iv_bindemail_save:
                setEmail(edInput.getText().toString());
                break;
            case R.id.im_delte:
                edInput.setText("");
                break;
        }
    }

    /**
     * 修改邮箱
     */
    private ACache aCache;

    public void setEmail(final String Email) {
        if (Email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+") && Email.length() > 0) {
            HttpHelper.getChangEmail(Email, new HttpHelper.HttpUtilsCallBack<String>() {
                @Override
                public void onFailure(String failure) {
                    Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSucceed(String succeed) {
                    SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                    Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                    aCache.put("user_mail", Email);
                    Toast.makeText(BindEmailActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    rlMineBindemailInput.setVisibility(View.GONE);
                    tvMineBindemailDescrip.setVisibility(View.GONE);
                    llMineBindemailWait.setVisibility(View.VISIBLE);
                    setResult(2);
                    finish();
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "邮箱格式有误 ", Toast.LENGTH_SHORT).show();
        }
    }
}
