package com.hykj.liuzhi.androidcomponents.ui.fragment.home.seacrch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hykj.liuzhi.androidcomponents.ui.fragment.home.FashionFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.RecommendFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.TextureFragment;

import java.util.ArrayList;

/**
 * 搜索界面
 *
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */

public class HomeSeacrchPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private ArrayList<String> titleList;

    public HomeSeacrchPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        SeacrchViedoFragment seacrchViedoFragment1 = new SeacrchViedoFragment();
        Bundle args = new Bundle();
        args.putString("pid", "1");
        seacrchViedoFragment1.setArguments(args);
        list.add(seacrchViedoFragment1);
        SeacrchViedoFragment seacrchViedoFragment2 = new SeacrchViedoFragment();
        Bundle args1 = new Bundle();
        args1.putString("pid", "2");
        seacrchViedoFragment2.setArguments(args1);
        list.add(seacrchViedoFragment2);
        titleList = new ArrayList<>();
        titleList.add("软文");
        titleList.add("视频");
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
