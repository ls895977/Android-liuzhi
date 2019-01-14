package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.content.Intent;
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
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.HomeSearchActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.LoginActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.MessageActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.HomeFragmentPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.VideoContextBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.SignDialog;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    SignDialog dialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(new HomeFragmentPagerAdapter(getChildFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_sign, R.id.rl_search, R.id.iv_message})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_sign:
                postSignIn();
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

    /**
     * 签到
     */
    SignInBean entity;
    ACache aCache;
    public void postSignIn() {
        aCache = ACache.get(getContext());
        HttpHelper.getSignIn(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
//                您已连续签到3天,再接再厉哦
                dialog = new SignDialog(getContext(), entity.getMsg());
                dialog.setCancelable(true);
                dialog.show();
            }

            @Override
            public void onError(String error) {
                if (error.equals("1")) {
                    Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
                } else if (error.equals("2")) {
                    Toast.makeText(getContext(), "已签到", Toast.LENGTH_SHORT).show();
                } else if (error.equals("3")) {
                    Toast.makeText(getContext(), "签到失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
