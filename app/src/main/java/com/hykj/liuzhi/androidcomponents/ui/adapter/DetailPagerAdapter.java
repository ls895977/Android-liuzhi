package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.DetailCommentFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.DetailMoreVideoFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.NotifyFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.TradeInfoFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.UserMessageFragment;

import java.util.ArrayList;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class DetailPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;
    private String status;
    public DetailPagerAdapter(FragmentManager fm, String videoid1,String status) {
        super(fm);
        list = new ArrayList<>();
        DetailCommentFragment detailCommentFragment = new DetailCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("videoid", videoid1);//这里的values就是我们要传的值
        bundle.putString("status",status);
        detailCommentFragment.setArguments(bundle);
        list.add(detailCommentFragment);
        DetailMoreVideoFragment detailMoreVideoFragment = new DetailMoreVideoFragment();
        detailMoreVideoFragment.setArguments(bundle);
        list.add(detailMoreVideoFragment);
        titleList = new ArrayList<>();
        titleList.add("评论列表");
        titleList.add("更多视频");
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
