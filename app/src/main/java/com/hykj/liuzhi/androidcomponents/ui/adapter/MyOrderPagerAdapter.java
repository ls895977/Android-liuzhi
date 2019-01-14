package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.hykj.liuzhi.androidcomponents.ui.fragment.MyOrderTabFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.FashionFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.RecommendFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.TextureFragment;

import java.util.ArrayList;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class MyOrderPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;

    public MyOrderPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        MyOrderTabFragment myOrderTabFragment1 = new MyOrderTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", "0");//这里的values就是我们要传的值
        myOrderTabFragment1.setArguments(bundle);
        list.add(myOrderTabFragment1);
        MyOrderTabFragment myOrderTabFragment2 = new MyOrderTabFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("type", "1");//这里的values就是我们要传的值
        myOrderTabFragment2.setArguments(bundle1);
        list.add(myOrderTabFragment2);
        MyOrderTabFragment myOrderTabFragment3 = new MyOrderTabFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("type", "2");//这里的values就是我们要传的值
        myOrderTabFragment3.setArguments(bundle2);
        list.add(myOrderTabFragment3);
        MyOrderTabFragment myOrderTabFragment4 = new MyOrderTabFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("type", "3");//这里的values就是我们要传的值
        myOrderTabFragment4.setArguments(bundle3);
        list.add(myOrderTabFragment4);
        titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("待付款");
        titleList.add("待收货");
        titleList.add("已完成");
    }

    //PagerAdapter中 该方法直接throw exception 所以需要子类重写
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
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
