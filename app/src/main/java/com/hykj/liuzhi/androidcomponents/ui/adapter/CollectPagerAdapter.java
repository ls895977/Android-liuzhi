package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.AdvertorialFragment;

import java.util.ArrayList;

public class CollectPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;

    public CollectPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        titleList = new ArrayList<>();

        list.add(new AdvertorialFragment().newInstance("1"));
        list.add(new AdvertorialFragment().newInstance("2"));
        list.add(new AdvertorialFragment().newInstance("3"));
        list.add(new AdvertorialFragment().newInstance("4"));

        titleList.add("软文");
        titleList.add("美图");
        titleList.add("视频");
        titleList.add("商品");
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
