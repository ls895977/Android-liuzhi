package com.hykj.liuzhi.androidcomponents.ui.activity.min;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.LoginActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.WatchHistoryPagerAdapter;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 我的订单
 * @author: lujialei
 * @date: 2018/10/16
 * @describe:
 */
public class Act_MyOrder extends BaseActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_my_order);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        new TitleBuilder(Act_MyOrder.this).setTitleText("我的订单").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager.setAdapter(new MyOrderPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }
}
