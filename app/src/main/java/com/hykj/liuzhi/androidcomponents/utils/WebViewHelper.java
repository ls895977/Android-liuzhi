package com.hykj.liuzhi.androidcomponents.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.hykj.liuzhi.androidcomponents.view.HProgressBarLoading;

/**
 * Created by Terminator on 2019/3/28.
 */
public class WebViewHelper {
    private static boolean isContinue = false;


    public static void initWebViewSetting(final Context context, final WebView webView, final HProgressBarLoading progressBarLoading, final TextView textView, final TextView tvTitle) {

        WebSettings settings = webView.getSettings();
        WebSettings webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setDomStorageEnabled(false);

        //正常网络流程
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //如果没有网络直接跳出方法
//                if (!NetUtils.isNetworkAvailable(context)) {
//                    return;
//                }
                //如果进度条隐藏则让它显示
                if (View.INVISIBLE == progressBarLoading.getVisibility()) {
                    progressBarLoading.setVisibility(View.VISIBLE);
                }
                //大于80的进度的时候,放慢速度加载,否则交给自己加载
                if (newProgress >= 80) {
                    //拦截webView自己的处理方式
                    if (isContinue) {
                        return;
                    }
                    progressBarLoading.setCurProgress(100, 0, new HProgressBarLoading.OnEndListener() {
                        @Override
                        public void onEnd() {
                            finishOperation(context, true, webView, progressBarLoading, textView);
                            isContinue = false;
                        }
                    });
                    isContinue = true;
                } else {
                    progressBarLoading.setNormalProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title.length() < 10) {
                    tvTitle.setText(title);
                }
            }

        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            //错误页面的逻辑处理
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                errorOperation(context, webView, progressBarLoading, textView);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }
        });
    }

    /**
     * 错误的时候进行的操作
     */
    private static void errorOperation(final Context context, final WebView webView, final HProgressBarLoading progressBarLoading, final TextView textView) {
        //隐藏webview
        webView.setVisibility(View.INVISIBLE);
        if (View.INVISIBLE == progressBarLoading.getVisibility()) {
            progressBarLoading.setVisibility(View.VISIBLE);
        }
        //3.5s 加载 0->80 进度的加载 为了实现,特意调节长了事件
        progressBarLoading.setCurProgress(80, 0, new HProgressBarLoading.OnEndListener() {
            @Override
            public void onEnd() {
                //3.5s 加载 80->100 进度的加载
                progressBarLoading.setCurProgress(100, 0, new HProgressBarLoading.OnEndListener() {
                    @Override
                    public void onEnd() {
                        finishOperation(context, false, webView, progressBarLoading, textView);
                    }
                });
            }
        });
    }

    /**
     * 结束进行的操作
     */
    private static void finishOperation(Context context, boolean flag, final WebView webView, HProgressBarLoading progressBarLoading, final TextView textView) {
        //最后加载设置100进度
        progressBarLoading.setNormalProgress(100);
        //显示网络异常布局
        textView.setVisibility(flag ? View.INVISIBLE : View.VISIBLE);
        //点击重新连接网络
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
                //重新加载网页
                webView.reload();
                webView.setVisibility(View.VISIBLE);
            }
        });
        hideProgressWithAnim(context, progressBarLoading);
    }

    /**
     * 隐藏加载对话框
     */
    private static void hideProgressWithAnim(Context context, final HProgressBarLoading progressBarLoading) {
        AnimationSet animation = getDismissAnim(context);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBarLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        progressBarLoading.startAnimation(animation);
    }

    /**
     * 获取消失的动画
     *
     * @param context
     * @return
     */
    private static AnimationSet getDismissAnim(Context context) {
        AnimationSet dismiss = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(500);
        dismiss.addAnimation(alpha);
        return dismiss;
    }

}
