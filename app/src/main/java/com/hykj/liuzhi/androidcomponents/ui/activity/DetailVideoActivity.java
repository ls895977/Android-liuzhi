package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DetailVideoBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_VideoDownload;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Videoreward;
import com.hykj.liuzhi.androidcomponents.ui.activity.video.DateUtils;
import com.hykj.liuzhi.androidcomponents.ui.activity.video.bean.VideoPointBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.DensityUtils;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/9/28
 * @describe:
 */
public class DetailVideoActivity extends BaseActivity implements Dlg_Videoreward.OnClick {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.video_title)
    TextView video_title;
    @BindView(R.id.video_time)
    TextView video_time;
    @BindView(R.id.video_zan)
    TextView video_zan;
    @BindView(R.id.video_collection)
    TextView video_collection;
    @BindView(R.id.tv_videoDetail_Zan)
    TextView zan;
    @BindView(R.id.choseGaoqing)
    ImageView gaoqing;

    JzvdStd mJzvdStd;
    private String videoid;
    private ACache aCache;
    private Dlg_Videoreward dialog;
    private Dlg_VideoDownload download;
    private TextView[] pt = new TextView[4];
    private TextView[] ppt = new TextView[4];
    private LinearLayout myChaoQingView;
    private String definition = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        ButterKnife.bind(this);
        videoid = getIntent().getStringExtra("videoid");
        initView();
        initData();
    }

    private void initView() {
        viewPager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager(), videoid, "video"));
        tabLayout.setViewPager(viewPager);
        mJzvdStd = findViewById(R.id.jz_video);
        myChaoQingView = findViewById(R.id.chaoqingstatus);
        pt[0] = findViewById(R.id.pt1);
        pt[1] = findViewById(R.id.pt2);
        pt[2] = findViewById(R.id.pt3);
        pt[3] = findViewById(R.id.pt4);
        ppt[0] = findViewById(R.id.ppt1);
        ppt[1] = findViewById(R.id.ppt2);
        ppt[2] = findViewById(R.id.ppt3);
        ppt[3] = findViewById(R.id.ppt4);

    }

    private void initData() {
        dialog = new Dlg_Videoreward(this, this);
        aCache = ACache.get(this);
        mJzvdStd.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        postBackData();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tv_collect, R.id.ll_download, R.id.videoDetail_Collection, R.id.tv_videoDetail_Zan, R.id.videoDetail_videoreward, R.id.choseGaoqing,
            R.id.p1, R.id.p2, R.id.p3, R.id.p4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                break;
            case R.id.ll_download:
                if (definition.equals("0")) {
                    Toast.makeText(getContext(), "请在视频右上角选择清晰度", Toast.LENGTH_SHORT).show();
                    return;
                }
                download = new Dlg_VideoDownload(this, videoid,entity.getData().getVideo_name(),entity.getData().getVideo_image());
                download.setDefinition(definition);
                download.show();
                break;
            case R.id.videoDetail_Collection://收藏
                if (video_collection.isSelected()) {//取消
                    videonotcollection();
                } else {//收藏
                    videocollection();
                }
                break;
            case R.id.tv_videoDetail_Zan://赞
                if (zan.isSelected()) {//取消
                    videoisnotpoint();
                } else {//赞
                    videoispoint();
                }
                break;
            case R.id.videoDetail_videoreward://打赏
                dialog.show();
                break;
            case R.id.choseGaoqing://选择视频放置清晰度
                myChaoQingView.setVisibility(View.VISIBLE);
                setChoseStatus(0);
                break;
            case R.id.p1://标清
                definition = "1";
                setChoseStatus(0);
                myChaoQingView.setVisibility(View.GONE);
                break;
            case R.id.p2://高清
                definition = "2";
                setChoseStatus(1);
                myChaoQingView.setVisibility(View.GONE);
                break;
            case R.id.p3://超清
                definition = "3";
                setChoseStatus(2);
                myChaoQingView.setVisibility(View.GONE);
                break;
            case R.id.p4://蓝光
                definition = "4";
                setChoseStatus(3);
                myChaoQingView.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 获取详情数据
     */
    DetailVideoBean entity;

    public void postBackData() {
        HttpHelper.videoshow(videoid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, DetailVideoBean.class);
                mJzvdStd.setUp(entity.getData().getVideodefinitiondata().get(0).getVideodefinition_url(), ""
                        , JzvdStd.SCREEN_WINDOW_NORMAL);
                Glide.with(DetailVideoActivity.this)
                        .load(entity.getData().getVideo_image())
                        .into(mJzvdStd.thumbImageView);
                Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                video_title.setText(entity.getData().getVideo_name());
                video_time.setText(DateUtils.timesTwo(entity.getData().getVideo_creattime() + ""));
                video_zan.setText(entity.getData().getVideo_point() + "");
                if (entity.getData().getUsercollection() == 0) {
                    video_collection.setSelected(false);
                } else {
                    video_collection.setSelected(true);
                }
                if (entity.getData().getUserpoint() == 1) {
                    zan.setSelected(true);
                } else {
                    zan.setSelected(false);
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 视频取藏
     */
    public void videocollection() {
        HttpHelper.videocollection(videoid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
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
                if (entity.getCode() == 0) {
                    Toast.makeText(getContext(), "收藏成功！", Toast.LENGTH_SHORT).show();
                    video_collection.setSelected(true);
                    return;
                }
                switch (entity.getError()) {
                    case 1:
                        Toast.makeText(getContext(), "未登录！", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(), "您已经收藏了该视频，无法重复收藏！", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(), "收藏失败，请稍后重试！", Toast.LENGTH_SHORT).show();
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
     * 视频取消收藏
     */
    public void videonotcollection() {
        HttpHelper.videonotcollection(videoid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, DetailVideoBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if (entity.getCode() == 0) {
                    Toast.makeText(getContext(), "取消成功！", Toast.LENGTH_SHORT).show();
                    video_collection.setSelected(false);
                    return;
                }
                switch (entity.getError()) {
                    case 1:
                        Toast.makeText(getContext(), "未登录！", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(), "取消收藏失败！", Toast.LENGTH_SHORT).show();
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
     * 视频取消赞
     */
    public void videoisnotpoint() {
        HttpHelper.videoisnotpoint(videoid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
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
                    zan.setSelected(false);
                    Toast.makeText(getContext(), "取消点赞成功！", Toast.LENGTH_SHORT).show();
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
     * 视频点赞
     */
    public void videoispoint() {
        HttpHelper.videoispoint(videoid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
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
                    zan.setSelected(true);
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
     * 视频打赏
     */
    private String number = "";

    public void videoreward() {
        HttpHelper.videoreward(videoid + "", number, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Log.e("aa", succeed);
                VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItem(String p) {
        number = p;
        videoreward();
    }

    private int idnex = 1;

    public void setChoseStatus(int stauts) {
        pt[stauts].setSelected(true);
        ppt[stauts].setSelected(true);
        pt[idnex].setSelected(false);
        ppt[idnex].setSelected(false);
        idnex = stauts;
    }

}
