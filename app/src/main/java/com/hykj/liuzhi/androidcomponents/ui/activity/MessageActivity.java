package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MessagePagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.widget.DefaultTopBar;
import com.hykj.liuzhi.androidcomponents.ui.widget.MessageTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息页
 * @author: lujialei
 * @date: 2018/10/9
 * @describe:
 */
public class MessageActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_message);
        ButterKnife.bind(this);
        viewPager.setAdapter(new MessagePagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }
    @Override
    protected View onCreateTopBar(ViewGroup view) {
        MessageTopBar defaultTopBar = new MessageTopBar(this, "消息", true);
        return defaultTopBar;
    }
}
