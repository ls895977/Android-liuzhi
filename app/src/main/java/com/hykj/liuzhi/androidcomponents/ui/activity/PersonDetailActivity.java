package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.adapter.PersonDetailAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.hykj.liuzhi.androidcomponents.utils.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonDetailActivity extends BaseActivity {
    @BindView(R.id.iv_person_detail_userhead)
    RoundImageView ivPersonDetailUserhead;
    @BindView(R.id.ll_person_detail_myfans)
    LinearLayout llPersonDetailMyfans;
    @BindView(R.id.ll_person_datacount)
    LinearLayout llPersonDatacount;
    @BindView(R.id.tab_layout_person_detail)
    SlidingTabLayout tabLayoutPersonDetail;
    @BindView(R.id.view_pager_person_detail)
    ViewPager viewPagerPersonDetail;
    @BindView(R.id.iv_person_detail_back)
    ImageView ivPersonDetailBack;
    @BindView(R.id.ll_person_detail_myfocus)
    LinearLayout llPersonDetailMyfocus;
    @BindView(R.id.tv_person_detail_username)
    TextView userName;
    @BindView(R.id.user_lable)
    TextView user_lable;
    RoundImageView roundImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    CircleFragmentBean.DataBean.UserdataBean userBean;
    private void initData() {
        roundImageView=findViewById(R.id.iv_person_detail_userhead);
        userBean = (CircleFragmentBean.DataBean.UserdataBean) getIntent().getSerializableExtra("userBean");
        Glide.with(getApplicationContext()).load(userBean.getUserdetail().getUser_pic()).into(roundImageView);
        userName.setText(userBean.getUserdetail().getUser_nickname());
        viewPagerPersonDetail.setAdapter(new PersonDetailAdapter(getSupportFragmentManager(), userBean.getUser_id()+""));
        tabLayoutPersonDetail.setViewPager(viewPagerPersonDetail);
    }

    private void initView() {

    }

    @OnClick({R.id.iv_person_detail_back, R.id.ll_person_detail_myfocus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_person_detail_back:
                finish();
                break;
            case R.id.ll_person_detail_myfocus://关注


                break;
        }
    }
}
