package com.hykj.liuzhi.androidcomponents.ui.activity.dailog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.hykj.liuzhi.R;
/**
 * Created by lishan on 2017/12/22.
 */
public class Dlg_Photograph extends BaseDialog {
    OnClick onClick;
    public Dlg_Photograph(Context context, OnClick click) {
        super(context);
        this.onClick = click;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlg_photocll;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.information_paizhao);
        setOnClickListener(R.id.information_xiangce);
        setOnClickListener(R.id.share_cancel);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.share_cancel:
                dismiss();
                break;
            case R.id.information_paizhao://拍照
                onClick.onItem(1);
                break;
            case R.id.information_xiangce://从手机相册选择
                onClick.onItem(2);
                break;
        }
    }

    public interface OnClick {
        void onItem(int p);
    }
}
