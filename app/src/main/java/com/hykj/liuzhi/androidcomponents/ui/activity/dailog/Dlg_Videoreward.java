package com.hykj.liuzhi.androidcomponents.ui.activity.dailog;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.hykj.liuzhi.R;

import java.util.Random;

/**
 * Created by lishan on 2017/12/22.
 */
public class Dlg_Videoreward extends BaseDialog {
    private EditText input;
    OnClick onClick;
    public Dlg_Videoreward(Context context, OnClick click) {
        super(context);
        this.onClick = click;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlg_videoreward;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
    }

    @Override
    protected void initView() {
        input = getView(R.id.video_input);
        setOnClickListener(R.id.video_ivImg);
        setOnClickListener(R.id.video_suiji);
        setOnClickListener(R.id.share_shutdown);
        setOnClickListener(R.id.video_dashang);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.video_ivImg:
                input.setEnabled(true);
                break;
            case R.id.video_suiji://随机
                Random random = new Random();
                int dext = random.nextInt(30);
                input.setText(dext + "");
                break;
            case R.id.share_shutdown:
                dismiss();
                break;
            case R.id.video_dashang:
                dismiss();
                onClick.onItem(input.getText().toString());
                break;
        }
    }

    public interface OnClick {
        void onItem(String p);
    }
}
