package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.MineCameFragment;

import java.util.ArrayList;

public class MinePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;
    public MinePagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        list.add(new MineCameFragment());
        list.add(new MineCameFragment());
//        list.add(new WatchHistoryFragment());
//        list.add(new MyOrderFragment());
        titleList = new ArrayList<>();
        titleList.add("拍照");
        titleList.add("相册");


        titleList.add("浏览记录");
        titleList.add("我的订单");



    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

}
