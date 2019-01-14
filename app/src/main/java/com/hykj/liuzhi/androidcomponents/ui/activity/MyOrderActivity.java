package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MinePagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.widget.DefaultTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class MyOrderActivity extends BaseActivity {


    @Override
    protected View onCreateTopBar(ViewGroup view) {
        return new DefaultTopBar(this,"我的订单",true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }
}
