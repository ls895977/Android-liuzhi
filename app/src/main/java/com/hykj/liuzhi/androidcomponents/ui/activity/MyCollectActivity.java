package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CollectPagerAdapter;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.tab_layout_mycollect)
    SlidingTabLayout tabLayoutMycollect;
    @BindView(R.id.view_pager_mycollect)
    ViewPager viewPagerMycollect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        ButterKnife.bind(this);
        initView();
        iniData();
    }

    private void iniData() {
        CollectPagerAdapter collectPagerAdapter = new CollectPagerAdapter(getSupportFragmentManager());
        viewPagerMycollect.setAdapter(collectPagerAdapter);
        tabLayoutMycollect.setViewPager(viewPagerMycollect);
    }

    private void initView() {
        new TitleBuilder(MyCollectActivity.this).setTitleText("我的收藏").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
