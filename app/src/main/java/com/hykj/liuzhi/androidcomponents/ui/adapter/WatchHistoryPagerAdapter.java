package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hykj.liuzhi.androidcomponents.ui.fragment.BeautifulImageFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.CircleFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.MineSoftArticleFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.FashionFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.RecommendFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.TextureFragment;

import java.util.ArrayList;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class WatchHistoryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;


    public WatchHistoryPagerAdapter(FragmentManager fm) {
        super(fm);

        list = new ArrayList<>();
        list.add(new MineSoftArticleFragment());
        list.add(new BeautifulImageFragment());
        titleList = new ArrayList<>();
        titleList.add("软文");
        titleList.add("美图");
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
