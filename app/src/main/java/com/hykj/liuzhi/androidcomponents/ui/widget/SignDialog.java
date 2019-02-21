package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/9
 * @describe:
 */


public class SignDialog extends Dialog {

    @BindView(R.id.iv_close)
    ImageView ivClose;

    public SignDialog(@NonNull Context context) {
        super(context);
    }

    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_sign);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        msg = findViewById(R.id.tv_hint);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    dismiss();
                }
            }
        });
        findViewById(R.id.tv_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postSignIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    /**
     * 签到
     */
    SignInBean entity;
    ACache aCache;

    public void postSignIn() {
        aCache = ACache.get(getContext());
        HttpHelper.getSignIn(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                msg.setText("您已连续签到" + entity.getMsg() + "天,再接再厉哦");
            }
            @Override
            public void onError(String error) {
                if (error.equals("1")) {
                    Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
                } else if (error.equals("2")) {
                    Toast.makeText(getContext(), "已签到", Toast.LENGTH_SHORT).show();
                } else if (error.equals("3")) {
                    Toast.makeText(getContext(), "签到失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
