package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AppmodelBean;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.HomeSearchActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.LoginActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.MessageActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.HomeFragmentPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.HomeAllFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.RecommendFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.VideoContextBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.widget.SignDialog;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 *
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    ZLoadingDialog loding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        loding = new ZLoadingDialog(getContext());
        loding.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据加载中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        return view;
    }

    SignDialog dialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postBackData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    Intent intent = null;

    @OnClick({R.id.iv_sign, R.id.rl_search, R.id.iv_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sign:
                dialog = new SignDialog(getContext());
                dialog.setCancelable(true);
                dialog.show();
                break;
            case R.id.rl_search:
                intent = new Intent(getContext(), HomeSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_message:
                intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
                break;
        }
    }


    private List<String> titleList = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();

    public void postBackData() {
        loding.show();
        HttpHelper.getHome_appmodel("1", "1", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                AppmodelBean bean = gson.fromJson(succeed, AppmodelBean.class);
                for (int i = 0; i < bean.getData().getArray().size(); i++) {
                    if(i==0){
                        titleList.add("推荐");
                        list.add(new RecommendFragment());
                    }else {
                        titleList.add(bean.getData().getArray().get(i).getModel_name());
                        HomeAllFragment homeAllFragment = new HomeAllFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("modelid", bean.getData().getArray().get(i).getModel_id() + "");
                        bundle.putString("modeltype", bean.getData().getArray().get(i).getModel_type() + "");
                        homeAllFragment.setArguments(bundle);
                        list.add(homeAllFragment);
                    }
                }

                viewPager.setAdapter(new HomeFragmentPagerAdapter(titleList, list, getChildFragmentManager()));
                tabLayout.setViewPager(viewPager);
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
            }
        });
    }
}
