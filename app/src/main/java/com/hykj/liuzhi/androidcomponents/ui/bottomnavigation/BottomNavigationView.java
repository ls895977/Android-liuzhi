package com.hykj.liuzhi.androidcomponents.ui.bottomnavigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.adapter.AttentionAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.AddFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.CircleFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.HomeFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.MineFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.ShopFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class BottomNavigationView extends RelativeLayout {
    @BindView(R.id.iv0)
    ImageView iv0;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigation_view, this, true);
        ButterKnife.bind(this);
        iv0.setSelected(true);
        iv1.setSelected(false);
        iv2.setSelected(false);
        iv3.setSelected(false);
        iv4.setSelected(false);
    }

    private ArrayList<Fragment> fragmentList;
    private FragmentManager supportFragmentManager;
    public void initFragment(int container, FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
        HomeFragment homeFragment = new HomeFragment();
        CircleFragment circleFragment = new CircleFragment();
        AddFragment addFragment = new AddFragment();
        ShopFragment messageFragment = new ShopFragment();
        MineFragment mineFragment = new MineFragment();
        fragmentList = new ArrayList();
        fragmentList.add(homeFragment);
        fragmentList.add(circleFragment);
        fragmentList.add(addFragment);
        fragmentList.add(messageFragment);
        fragmentList.add(mineFragment);
        supportFragmentManager.beginTransaction()
                .add(R.id.container,fragmentList.get(0),"home")
                .add(R.id.container,fragmentList.get(1),"circle")
                .add(R.id.container,fragmentList.get(2),"add")
                .add(R.id.container,fragmentList.get(3),"message")
                .add(R.id.container,fragmentList.get(4),"mine")
                .hide(fragmentList.get(1))
                .hide(fragmentList.get(2))
                .hide(fragmentList.get(3))
                .hide(fragmentList.get(4))
                .commitAllowingStateLoss();
    }

    public interface Listener{
        void onClick(int index);
    }
    private Listener listener;
    public void setListener(Listener listener){
        this.listener = listener;
    }

    @OnClick({R.id.iv0, R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv0:
                iv0.setSelected(true);
                iv1.setSelected(false);
                iv2.setSelected(false);
                iv3.setSelected(false);
                iv4.setSelected(false);
                changeTab(0);
                listener.onClick(0);
                break;
            case R.id.iv1:
                iv0.setSelected(false);
                iv1.setSelected(true);
                iv2.setSelected(false);
                iv3.setSelected(false);
                iv4.setSelected(false);
                changeTab(1);
                listener.onClick(1);
                break;
            case R.id.iv2:
//                iv0.setSelected(false);
//                iv1.setSelected(false);
//                iv2.setSelected(true);
//                iv3.setSelected(false);
//                iv4.setSelected(false);
//                changeTab(2);
                listener.onClick(2);
                break;
            case R.id.iv3:
                iv0.setSelected(false);
                iv1.setSelected(false);
                iv2.setSelected(false);
                iv3.setSelected(true);
                iv4.setSelected(false);
                changeTab(3);
                listener.onClick(3);
                break;
            case R.id.iv4:
                iv0.setSelected(false);
                iv1.setSelected(false);
                iv2.setSelected(false);
                iv3.setSelected(false);
                iv4.setSelected(true);
                changeTab(4);
                listener.onClick(4);

                break;
        }
    }

    private void changeTab(int index) {
        if (index==0){
            supportFragmentManager.beginTransaction()
                    .show(fragmentList.get(0))
                    .hide(fragmentList.get(1))
                    .hide(fragmentList.get(2))
                    .hide(fragmentList.get(3))
                    .hide(fragmentList.get(4))
                    .commitAllowingStateLoss();
        } else if (index==1){
            supportFragmentManager.beginTransaction()
                    .hide(fragmentList.get(0))
                    .show(fragmentList.get(1))
                    .hide(fragmentList.get(2))
                    .hide(fragmentList.get(3))
                    .hide(fragmentList.get(4))
                    .commitAllowingStateLoss();
        } else if (index==2){
//            supportFragmentManager.beginTransaction()
//                    .hide(fragmentList.get(0))
//                    .hide(fragmentList.get(1))
//                    .show(fragmentList.get(2))
//                    .hide(fragmentList.get(3))
//                    .hide(fragmentList.get(4))
//                    .commitAllowingStateLoss();
        } else if (index==3){
            supportFragmentManager.beginTransaction()
                    .hide(fragmentList.get(0))
                    .hide(fragmentList.get(1))
                    .hide(fragmentList.get(2))
                    .show(fragmentList.get(3))
                    .hide(fragmentList.get(4))
                    .commitAllowingStateLoss();
        } else if (index==4){
            supportFragmentManager.beginTransaction()
                    .hide(fragmentList.get(0))
                    .hide(fragmentList.get(1))
                    .hide(fragmentList.get(2))
                    .hide(fragmentList.get(3))
                    .show(fragmentList.get(4))
                    .commitAllowingStateLoss();
        }
    }
}
