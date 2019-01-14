package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author: lujialei
 * @date: 2018/9/29
 * @describe:
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 是否为悬浮ToolBar 默认:false
     */
    private boolean isOverly = false;
    /**
     * 根布局容器
     */
    private ViewGroup mRootContainer;

    /**
     * 自己的根布局
     */
    View contentView;

    View topBarLayout;


    @Override
    public void setContentView(int layoutResID) {
        mRootContainer = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        if (isShowTopBar()) {
            setContentView(getLayoutInflater().inflate(layoutResID, mRootContainer, false));
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentView = view;
        if (isShowTopBar()) {
            if (null == params)
                params = contentView.getLayoutParams();

            if (isOverly) {
                // 1、创建根布局
                mRootContainer = new FrameLayout(this);

                // 2、加入contentView
                mRootContainer.addView(contentView,
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                // 3、加入topBar
                initTopBar();

            } else {
                // 1、创建根布局
                mRootContainer = new LinearLayout(this);
                ((LinearLayout) mRootContainer).setOrientation(LinearLayout.VERTICAL);

                // 2、加入topBar
                initTopBar();

                // 3、加入contentView
                mRootContainer.addView(contentView,
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

            if (null == params) {
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            super.setContentView(mRootContainer, params);
        } else {
            if (mRootContainer == null) {
                mRootContainer = (ViewGroup)
                        getWindow().getDecorView().findViewById(android.R.id.content);
            }
            if (null == params) {
                super.setContentView(view);
            } else {
                super.setContentView(view, params);
            }
        }
    }

    private void initTopBar() {
        topBarLayout = onCreateTopBar(mRootContainer);
        if (topBarLayout == null) {
            Log.e("topBar", getClass().getSimpleName() + " onCreateTopBar()不应该返回null");
        } else {
            mRootContainer.addView(topBarLayout);
        }
    }

    public boolean isOverly() {
        return isOverly;
    }

    /**
     * 设置topBar是否为悬浮
     *
     * @param overly
     */
    public void setOverly(boolean overly) {
        isOverly = overly;
    }

    /**
     * 是否显示toolBar
     *
     * @return true:显示   false:隐藏
     */
    public boolean isShowTopBar() {
        return true;
    }

    /**
     * 设置Toolbar的数据，也可以设置自定义toolBar视图 如需则交给子类去实现
     */
    protected View onCreateTopBar(ViewGroup view) {
        // 注意：此段代码在子类的onCreate()方法的setContentView(layoutResId)中执行
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideTopBar() {
        if (topBarLayout != null) {
            topBarLayout.setVisibility(View.GONE);
        }
    }

    public void showTopBar() {
        if (topBarLayout != null) {
            topBarLayout.setVisibility(View.VISIBLE);
        }
    }
}
