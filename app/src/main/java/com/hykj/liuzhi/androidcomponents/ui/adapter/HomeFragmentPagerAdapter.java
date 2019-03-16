package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.FashionFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.PlayFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.RecommendFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.TextureFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titleList;
    public HomeFragmentPagerAdapter(List<String> titleList1, List<Fragment> list1, FragmentManager fm) {
        super(fm);
        list = list1;
        titleList = titleList1;
//        list = new ArrayList<>();
//        list.add(new RecommendFragment());
//        list.add(new TextureFragment());
//        list.add(new FashionFragment());
//        list.add(new RecommendFragment());
//        titleList = new ArrayList<>();
//        titleList.add("推荐");
//        titleList.add("纹理");
//        titleList.add("潮流");
//        titleList.add("玩品");
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
