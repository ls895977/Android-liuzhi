package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AddCodeBean;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.mock.Mock;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.CircleDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.DetailCirclelmageBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Videoreward;
import com.hykj.liuzhi.androidcomponents.ui.activity.video.bean.VideoPointBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailCircleAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.DefaultTopBar;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class DetailCircleImageActivity extends BaseActivity implements Dlg_Videoreward.OnClick {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.ll_detail_circle_tougao)
    LinearLayout llDetailCircleTougao;
    @BindView(R.id.detailcircleimage_context)
    TextView tv_context;
    @BindView(R.id.detailcircleimage_nickName)
    TextView nickName;
    @BindView(R.id.detailcircleimage_table)
    TextView table;
    @BindView(R.id.detailcircleimage_concell)
    TextView Zan;
    @BindView(R.id.tv_collect)
    TextView concell;
    @BindView(R.id.circle_Userfans)
    TextView userFans;
    private String imagetext_id;
    private ACache aCache;
    private Dlg_Videoreward dlg_videoreward;
    private int mImageTextCollect;
    private int mImageTextPoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_circle);
        ButterKnife.bind(this);
        initView();
    }

    ZLoadingDialog dialog;

    private void initView() {
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据加载中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        dlg_videoreward = new Dlg_Videoreward(this, this);
        aCache = ACache.get(this);
        imagetext_id = getIntent().getStringExtra("imagetext_id");
        viewPager.setAdapter(new DetailCircleAdapter(getSupportFragmentManager(), imagetext_id, "imagetext"));
        tabLayout.setViewPager(viewPager);
        backData(imagetext_id);
        postAdd();
    }


    @Override
    protected View onCreateTopBar(ViewGroup view) {
        DefaultTopBar topBar = new DefaultTopBar(this, "详情", true);
        return topBar;
    }

    @OnClick({R.id.ll_collect, R.id.ll_detail_circle_tougao, R.id.detailcircleimage_concell, R.id.ll_pay, R.id.tv_collect,
            R.id.circle_Userfans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_collect:
                break;
            case R.id.ll_detail_circle_tougao:
                Intent intent = new Intent(this, IssueClumnActivity.class);
                intent.putExtra("position", 2);
                intent.putExtra("title", "我要投稿");
                startActivity(intent);
                break;
            case R.id.detailcircleimage_concell://点赞
                if (Zan.isSelected()) {
                    imagetextisnotpoint();
                } else {
                    imagetextispoint();
                }
                break;
            case R.id.tv_collect://收藏
                if (concell.isSelected()) {
                    imagetextnotcollection();
                } else {
                    imagetextcollection();
                }
                break;
            case R.id.ll_pay://打赏
                dlg_videoreward.show();
                break;
            case R.id.circle_Userfans://关注
                if (entity.getData() == null) {
                    return;
                }
                if (userFans.getText().toString().equals("+ 关注")) {//走关注
                    setClick(entity.getData().getUser_id());
                } else {
                    getUsernotfans(entity.getData().getUser_id());
                }
                break;
        }
    }

    DetailCirclelmageBean entity;

    public void backData(String messageid) {
        dialog.show();
        HttpHelper.imagetextpage(messageid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                entity = FastJSONHelper.getPerson(succeed, DetailCirclelmageBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                llDetailCircleTougao.setVisibility(View.GONE);
                if (entity.getData().getUserdata().getUser_pic() != null) {
                    Glide.with(DetailCircleImageActivity.this).load(entity.getData().getUserdata().getUser_pic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivAvatar);
                }
                tv_context.setText(entity.getData().getImagetext_text());
                nickName.setText(entity.getData().getUserdata().getUser_nickname());
                table.setText(entity.getData().getUserdata().getUser_autograph());
                List<String> pric = new ArrayList<>();
                for (int i = 0; i < entity.getData().getImagetextpic().size(); i++) {
                    pric.add(entity.getData().getImagetextpic().get(i).getImagetextimage_url());
                }
                banner.setImages(pric);
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                banner.setImageLoader(new GlideImageLoader())
                        .setDelayTime(5000)
                        .start();
                mImageTextCollect = entity.getData().getImagetext_collection();
                concell.setText(String.valueOf(mImageTextCollect));
                if (entity.getData().getUsercollection() == 1) {
                    concell.setSelected(true);
                } else {
                    concell.setSelected(false);
                }
                if (entity.getData().getUserpoint() == 1) {
                    Zan.setSelected(true);
                } else {
                    Zan.setSelected(false);
                }
                if (entity.getData().getUserfans() == 1) {
                    userFans.setText("已关注");
                } else {
                    userFans.setText("+ 关注");
                }
                mImageTextPoint = entity.getData().getImagetext_point();
                Zan.setText(String.valueOf(mImageTextPoint));

            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 图文取消收藏
     */
    public void imagetextnotcollection() {
        HttpHelper.imagetextnotcollection(imagetext_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if (entity.getMsg().equals("访问成功")) {
                    concell.setSelected(false);
                    mImageTextCollect--;
                    concell.setText(String.valueOf(mImageTextCollect));
                    Toast.makeText(getContext(), "取消收藏成功！", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (entity.getError()) {
                    case 1:
                        Toast.makeText(getContext(), "未登录！", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(), "取消失败！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 图文收藏
     */
    public void imagetextcollection() {
        HttpHelper.imagetextcollection(imagetext_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                Log.e("aa", "---------" + entity.getMsg());
                if (entity.getMsg().equals("访问成功")) {
                    concell.setSelected(true);
                    mImageTextCollect++;
                    concell.setText(String.valueOf(mImageTextCollect));
                    Toast.makeText(getContext(), "收藏成功！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 图文点赞
     */
    public void imagetextispoint() {
        HttpHelper.imagetextispoint(imagetext_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if (entity.getMsg().equals("访问成功")) {
                    Zan.setSelected(true);
                    mImageTextPoint++;
                    Zan.setText(String.valueOf(mImageTextPoint));
                    Toast.makeText(getContext(), "点赞成功！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 图文取消点赞
     */
    public void imagetextisnotpoint() {
        HttpHelper.imagetextisnotpoint(imagetext_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if (entity.getMsg().equals("访问成功")) {
                    Zan.setSelected(false);
                    mImageTextPoint--;
                    Zan.setText(String.valueOf(mImageTextPoint));
                    Toast.makeText(getContext(), "取消点赞成功！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 打赏
     *
     * @param p
     */
    @Override
    public void onItem(String p) {
        number = p;
        Cirde_imagetextreward();
    }

    /**
     * 图文打赏
     */
    private String number = "";

    public void Cirde_imagetextreward() {
        HttpHelper.Cirde_imagetextreward(number, imagetext_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Log.e("aa", succeed);
                CircleDetailBean entity = FastJSONHelper.getPerson(succeed, CircleDetailBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if (entity.getError() != 0) {
                    Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加到软文瀏覽记录
     */
    private Gson gson;

    public void postAdd() {
        gson = new Gson();
        HttpHelper.imagetextbrowses(imagetext_id + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                AddCodeBean bean = gson.fromJson(succeed, AddCodeBean.class);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 关注
     *
     * @param clickId
     */
    private void setClick(int clickId) {
        HttpHelper.getUserClickAttention(clickId + "", LocalInfoUtils.getUserId() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                if (entity.getCode() == 0) {
                    if (entity.getError() == 0) {
                        userFans.setText("已关注");
                    }
                    Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消关注
     *
     * @param clickId
     */
    private void getUsernotfans(int clickId) {
        HttpHelper.getUsernotfans(clickId + "", LocalInfoUtils.getUserId() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {

            }

            @Override
            public void onSucceed(String succeed) {
                LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                if (entity.getCode() == 0) {
                    if (entity.getError() == 0) {
                        userFans.setText("+ 关注");
                    }
                    Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
