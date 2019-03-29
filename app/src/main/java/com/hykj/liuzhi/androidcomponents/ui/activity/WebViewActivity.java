package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.utils.WebViewHelper;
import com.hykj.liuzhi.androidcomponents.view.HProgressBarLoading;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Terminator on 2019/3/28.
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.wv)
    WebView mWv;
    @BindView(R.id.top_progress)
    HProgressBarLoading mTopProgress;
    @BindView(R.id.tv_center_badnet)
    TextView mTvCenterBadnet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        WebViewHelper.initWebViewSetting(WebViewActivity.this, mWv, mTopProgress, mTvCenterBadnet, mTvTitle);
        mWv.loadUrl(getIntent().getStringExtra("url"));
    }

    @OnClick({R.id.iv_back, R.id.tv_center_badnet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_center_badnet:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWv.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mWv.onPause();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mWv != null) {
            mWv.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            ((ViewGroup) mWv.getParent()).removeView(mWv);
            mWv.removeAllViews();
            mWv.destroy();
            mWv = null;
        }
        super.onDestroy();
    }
}
