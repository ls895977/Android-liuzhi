package com.hykj.liuzhi.androidcomponents.ui.activity.min;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CartBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.ConfirmOrderActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.SetUpActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CartAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.WatchHistoryPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.CollectionBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 浏览记录
 * @author: lujialei
 * @date: 2018/10/16
 * @describe:
 */
public class Act_WatchHistory extends BaseActivity {
    ACache aCache;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_watch_history);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        new TitleBuilder(Act_WatchHistory.this).setTitleText("浏览记录").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        aCache = ACache.get(this);
        viewPager.setAdapter(new WatchHistoryPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }
}
