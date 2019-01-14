package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.MineCameFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.UserAdvertorialFragment;

import java.util.ArrayList;

public class PersonDetailAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;

    public PersonDetailAdapter(FragmentManager fm, String userId) {
        super(fm);
        list = new ArrayList<>();
        MineCameFragment mineCameFragment = new MineCameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);//这里的values就是我们要传的值
        mineCameFragment.setArguments(bundle);
        list.add(mineCameFragment);
        UserAdvertorialFragment userAdvertorialFragment = new UserAdvertorialFragment();
        userAdvertorialFragment.setArguments(bundle);
        list.add(userAdvertorialFragment);
        titleList = new ArrayList<>();
        titleList.add("拍照");
        titleList.add("相册");


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
