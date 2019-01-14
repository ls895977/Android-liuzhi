package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.hykj.liuzhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class WatchHistoryActivity extends BaseActivity {

    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_history);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
//        WatchHistoryFragment fragment = new WatchHistoryFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
    }
}
