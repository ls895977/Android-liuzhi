package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.bean.PersonDetailBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.PersonDetailAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.RoundImageView;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

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
    @BindView(R.id.user_fans)
    TextView user_fans;
    @BindView(R.id.user_follow)
    TextView user_follow;
    @BindView(R.id.circle_Userfans)
    TextView circle_Userfans;
    RoundImageView iv_person_detail_userhead;
    private ACache aCache;
    ZLoadingDialog dialog;
    private String User_id;

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
        iv_person_detail_userhead = findViewById(R.id.iv_person_detail_userhead);
        aCache = ACache.get(this);
        User_id = getIntent().getStringExtra("User_id");
        getUserfirstpagetitle();
    }

    private void initView() {
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据加载中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
    }

    @OnClick({R.id.iv_person_detail_back, R.id.circle_Userfans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_person_detail_back:
                finish();
                break;
            case R.id.circle_Userfans://关注
                if (circle_Userfans.getText().toString().equals("+ 关注")) {//走关注
                    setClick(User_id);
                } else {
                    getUsernotfans(User_id);
                }
                break;
        }
    }

    /**
     * 关注
     *
     * @param clickId
     */
    private void setClick(String clickId) {
        dialog.show();
        HttpHelper.getUserClickAttention(clickId, LocalInfoUtils.getUserId() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                if (entity.getCode() == 0) {
                    if (entity.getError() == 0) {
                        circle_Userfans.setText("已关注");
                    }
                    Toast.makeText(getContext(), "关注成功！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消关注
     *
     * @param clickId
     */
    private void getUsernotfans(String clickId) {
        dialog.show();
        HttpHelper.getUsernotfans(LocalInfoUtils.getUserId() + "", clickId, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();

            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                if (entity.getCode() == 0) {
                    if (entity.getError() == 0) {
                        circle_Userfans.setText("+ 关注");
                    }
                    Toast.makeText(getContext(), "已取消关注！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 首页=其他用户主页 头部信息
     */
    private void getUserfirstpagetitle() {
        dialog.show();
        HttpHelper.getUserfirstpagetitle(User_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                PersonDetailBean entity = FastJSONHelper.getPerson(succeed, PersonDetailBean.class);
                Glide.with(getApplicationContext()).load(entity.getData().getUser_pic()).into(iv_person_detail_userhead);
                userName.setText(entity.getData().getUser_nickname());
                viewPagerPersonDetail.setAdapter(new PersonDetailAdapter(getSupportFragmentManager(), entity.getData().getUser_id() + ""));
                tabLayoutPersonDetail.setViewPager(viewPagerPersonDetail);
                user_follow.setText(entity.getData().getUser_follow() + "");
                user_fans.setText(entity.getData().getUser_fans() + "");
                if (entity.getData().getUserfans() == 1) {
                    circle_Userfans.setText("已关注");
                } else {
                    circle_Userfans.setText("+ 关注");
                }
                user_lable.setText(entity.getData().getUser_autograph());
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
            }
        });
    }


}
