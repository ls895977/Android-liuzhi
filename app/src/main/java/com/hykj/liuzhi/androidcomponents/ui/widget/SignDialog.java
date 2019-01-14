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
    String mesg;

    public SignDialog(@NonNull Context context, String mesg1) {
        super(context);
        mesg = mesg1;
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
        msg=findViewById(R.id.tv_hint);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    dismiss();
                }
            }
        });
        msg.setText("您已连续签到"+mesg+"天,再接再厉哦");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


}
