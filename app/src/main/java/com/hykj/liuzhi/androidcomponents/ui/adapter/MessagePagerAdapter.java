package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.hykj.liuzhi.androidcomponents.ui.fragment.message.NotifyFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.TradeInfoFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.UserMessageFragment;

import java.util.ArrayList;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class MessagePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;

    public MessagePagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        NotifyFragment notifyFragment = new NotifyFragment();
        list.add(notifyFragment);
        UserMessageFragment userMessageFragment = new UserMessageFragment();
        list.add(userMessageFragment);
        TradeInfoFragment tradeInfoFragment = new TradeInfoFragment();
        list.add(tradeInfoFragment);
        titleList = new ArrayList<>();
        titleList.add("平台通知");
        titleList.add("用户消息");
        titleList.add("交易信息");
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
