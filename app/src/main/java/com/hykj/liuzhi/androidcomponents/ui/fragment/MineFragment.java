package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.UserData;
import com.hykj.liuzhi.androidcomponents.ui.activity.AttentionActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.EditUserDataActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.MyCollectActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.MyJiFenActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.OffLineVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.SetUpActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.Act_MyOrder;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.Act_WatchHistory;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MinePagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.MineCameFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.UserAdvertorialFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.RxPermissions;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class MineFragment extends Fragment {
    @BindView(R.id.iv_mine_userhead)
    RoundImageView ivMineUserhead;
    @BindView(R.id.tv_mineuser_nike)
    TextView tvMineuserNike;
    @BindView(R.id.tv_mineuser_id)
    TextView tvMineuserId;
    @BindView(R.id.tv_mine_edit_userdata)
    TextView tvMineEditUserdata;
    Unbinder unbinder;
    @BindView(R.id.rl_mine_setup)
    RelativeLayout rlMineSetup;
    @BindView(R.id.ll_mine_mycollect)
    LinearLayout llMineMycollect;
    @BindView(R.id.ll_mine_myfocus)
    LinearLayout llMineMyfocus;
    @BindView(R.id.ll_mine_myfans)
    LinearLayout llMineMyfans;
    @BindView(R.id.tv_mine_set)
    ImageView tvMineSet;
    @BindView(R.id.tv_mine_sead)
    TextView tvMineSead;
    @BindView(R.id.tv_mine_offline_down)
    TextView tvMineOfflineDown;
    @BindView(R.id.mineTvCollection)
    TextView tvConllection;
    @BindView(R.id.mineTvmyfocus)
    TextView tvMyFocus;
    @BindView(R.id.mineTvmyfans)
    TextView tvMyFans;
    Gson gson = new Gson();
    private RxPermissions rxPermissions;
    TextView[] tvItem;
    private ACache aCache;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    private List<Fragment> fgtData = new ArrayList<>();

    private void initView(View view) {
        aCache = ACache.get(getActivity());
        restoreInfo();
        tvItem = new TextView[3];
        tvItem[0] = view.findViewById(R.id.tab_Camer);
        tvItem[1] = view.findViewById(R.id.tab_Img);
        tvItem[0].setSelected(true);
    }

    private void initData() {
        MineCameFragment mineCameFragment = new MineCameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", aCache.getAsString("user_id"));//这里的values就是我们要传的值
        mineCameFragment.setArguments(bundle);
        fgtData.add(mineCameFragment);
        UserAdvertorialFragment userAdvertorialFragment = new UserAdvertorialFragment();
        userAdvertorialFragment.setArguments(bundle);
        fgtData.add(userAdvertorialFragment);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.myFrame, fgtData.get(0)).add(R.id.myFrame, fgtData.get(1)).hide(fgtData.get(1)).show(fgtData.get(0)).commit();
        rxPermissions = new RxPermissions(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        seetingUser();
    }

    /*获取保存的信息*/
    private void restoreInfo() {
        SharedPreferences sp = getActivity().getSharedPreferences("data", 0);
        String userPhone = sp.getString("phone", "");
        if (!userPhone.isEmpty()) {
            tvMineuserNike.setText(userPhone);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    Intent intent = null;

    @OnClick({R.id.iv_mine_userhead, R.id.tv_mine_edit_userdata, R.id.rl_mine_setup, R.id.ll_mine_mycollect,
            R.id.ll_mine_myfocus, R.id.ll_mine_myfans, R.id.tv_mine_sead, R.id.tv_mine_offline_down,
            R.id.tab_History, R.id.tab_Order, R.id.tab_Camer, R.id.tab_Img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_userhead:
            case R.id.tv_mine_edit_userdata:
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    intent = new Intent(getContext(), EditUserDataActivity.class);
                                } else {
                                    Toast.makeText(getActivity(), "请打开读写存储卡权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.rl_mine_setup:
                intent = new Intent(getContext(), SetUpActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_mycollect:
                intent = new Intent(getContext(), MyCollectActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_mine_myfocus:   //关注
                intent = new Intent(getContext(), AttentionActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;

            case R.id.ll_mine_myfans://粉丝
                intent = new Intent(getContext(), AttentionActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.tv_mine_sead:
                intent = new Intent(getContext(), MyJiFenActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mine_offline_down:
                intent = new Intent(getContext(), OffLineVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_Order://订单
                intent = new Intent(getContext(), Act_MyOrder.class);
                startActivity(intent);
                break;
            case R.id.tab_History://浏览记录
                intent = new Intent(getContext(), Act_WatchHistory.class);
                startActivity(intent);
                break;
            case R.id.tab_Camer://相机
                setCurrent(0);
                break;
            case R.id.tab_Img://图片
                setCurrent(1);
                break;
        }
    }

    public void seetingUser() {
        Glide.with(getContext()).load(LocalInfoUtils.getUserself("user_pic")).into(ivMineUserhead);
        tvMineuserNike.setText(LocalInfoUtils.getUserself("user_nickname"));
        tvMineuserId.setText("ID:" + LocalInfoUtils.getUserself("user_id"));
        tvConllection.setText(LocalInfoUtils.getUserself("user_collection"));
        tvMyFocus.setText(LocalInfoUtils.getUserself("user_follow"));
        tvMyFans.setText(LocalInfoUtils.getUserself("user_fans"));
        tvMineSead.setText(LocalInfoUtils.getUserself("user_integral"));
    }

    public int currentTabIndex = 0;

    public void setCurrent(int indext) {
        if (currentTabIndex != indext) {
            FragmentTransaction trx = getActivity().getSupportFragmentManager().beginTransaction();
            trx.hide(fgtData.get(currentTabIndex));
            if (!fgtData.get(indext).isAdded()) {
                trx.add(R.id.myFrame, fgtData.get(indext));
            }
            trx.show(fgtData.get(indext)).commit();
        }
        tvItem[currentTabIndex].setSelected(false);
        tvItem[indext].setSelected(true);
        currentTabIndex = indext;
    }
}
