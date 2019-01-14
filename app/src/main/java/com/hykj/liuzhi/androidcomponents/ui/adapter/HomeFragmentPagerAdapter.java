package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.hykj.liuzhi.androidcomponents.ui.fragment.home.FashionFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.PlayFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.RecommendFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.TextureFragment;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;
    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        list.add(new RecommendFragment());
        list.add(new TextureFragment());
        list.add(new FashionFragment());
        list.add(new RecommendFragment());
        titleList = new ArrayList<>();
        titleList.add("推荐");
        titleList.add("纹理");
        titleList.add("潮流");
        titleList.add("玩品");
    }
//    @Override
//    public void onDestroyView() {
//        LogUtils.d(TAG, "-->onDestroyView");
//        super.onDestroyView();
//        if (null != FragmentView) {
//            ((ViewGroup) mFragmentView.getParent()).removeView(mFragmentView);
//        }
//    }
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
