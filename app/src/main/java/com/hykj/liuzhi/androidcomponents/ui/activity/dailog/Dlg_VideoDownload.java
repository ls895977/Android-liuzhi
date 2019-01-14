package com.hykj.liuzhi.androidcomponents.ui.activity.dailog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.hykj.liuzhi.R;

import java.util.Random;

/**
 * 下载
 * Created by lishan on 2017/12/22.
 */
public class Dlg_VideoDownload extends BaseDialog {
    private EditText input;
    OnClick onClick;

    public Dlg_VideoDownload(Context context) {
        super(context);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.layout_bottom_download_video;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.downlod_shudown);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.downlod_shudown:
                dismiss();
                break;
        }
    }

    public interface OnClick {
        void onItem(String p);
    }
}
