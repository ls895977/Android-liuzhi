package com.hykj.liuzhi.androidcomponents.ui.activity.dailog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.hykj.liuzhi.R;

/**
 * Created by lishan on 2017/12/22.
 */
public class Dlg_Share extends BaseDialog {
    OnClick onClick;

    public Dlg_Share(Context context, OnClick click) {
        super(context);
        this.onClick = click;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlg_share;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.share_shutdown);
        setOnClickListener(R.id.weixin);
        setOnClickListener(R.id.pengyouquan);
        setOnClickListener(R.id.weibo);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.share_shutdown:
                dismiss();
                break;
            case R.id.weixin://微信
                onClick.onItemShear(1);
                dismiss();
                break;
            case R.id.pengyouquan://朋友圈
                onClick.onItemShear(2);
                dismiss();
                break;
            case R.id.weibo://微博
                onClick.onItemShear(3);
                dismiss();
                break;
        }
    }

    public interface OnClick {
        void onItemShear(int p);
    }
}
