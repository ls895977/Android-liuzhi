package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DetailVideoBean;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.bean.ShearBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.CommentActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.PersonDetailActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Share;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Videoreward;
import com.hykj.liuzhi.androidcomponents.ui.activity.softtext.Act_addsofttext;
import com.hykj.liuzhi.androidcomponents.ui.activity.softtext.SofttextFirstPageBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.video.bean.VideoPointBean;
import com.hykj.liuzhi.androidcomponents.ui.glide.GlideRoundTransform;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.TimeUtils;
import com.hykj.liuzhi.androidcomponents.utils.WxShareUtils;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */
public class SoftDetailHeader extends LinearLayout implements View.OnClickListener, Dlg_Videoreward.OnClick, Dlg_Share.OnClick {
    @BindView(R.id.iv1)
    ImageView iv1;
    SofttextFirstPageBean bean;
    private ACache aCache;
    private Activity activity;
    private TextView circle_Userfans, tv_num, tv_type;
    private String stType;
    private int mSoftTextPoint;

    public SoftDetailHeader(Activity context, SofttextFirstPageBean bean1, String stType1) {
        super(context);
        this.bean = bean1;
        this.activity = context;
        stType = stType1;
        initView(context);
    }

    private TextView title, Date, nickName, tv_autograp, softtextimage, collection, userpoint;
    private Dlg_Videoreward dialog;
    private Context context;
    Dlg_Share share;
    private ZLoadingDialog loding;

    private void initView(Context context1) {
        this.context = context1;
        loding = new ZLoadingDialog(context1);
        loding.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据提交中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        share = new Dlg_Share(context1, this);
        aCache = ACache.get(context);
        dialog = new Dlg_Videoreward(context, this);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_header_soft_article_detail, this, true);
        circle_Userfans = view.findViewById(R.id.circle_Userfans);
        tv_num = view.findViewById(R.id.tv_num);
        tv_type = view.findViewById(R.id.tv_type);
        tv_type.setText(stType);
        ButterKnife.bind(this);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new GlideRoundTransform(context, 6)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context).load(R.mipmap.test2).apply(requestOptions).into(iv1);
        title = view.findViewById(R.id.softtext_title);
        title.setText(bean.getData().getSofttext_title());
        Date = view.findViewById(R.id.Date);
        String time = bean.getData().getSofttext_creattime() + "";
        Log.e("aa", time);
        if (time != null) {
            Date.setText(TimeUtils.YerMothDay(time));
        }
        CircleImageView haderimg = view.findViewById(R.id.iv_avatar);
        haderimg.setOnClickListener(new OnClickListener() {//头像详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, PersonDetailActivity.class);
                intent.putExtra("User_id", bean.getData().getUser_id() + "");
                context.startActivity(intent);
            }
        });
        circle_Userfans.setOnClickListener(this);
        nickName = view.findViewById(R.id.tv_nickname);
        tv_autograp = view.findViewById(R.id.tv_autograp);
        softtextimage = view.findViewById(R.id.tv_Softtextimage);
        if (bean.getData().getUserdata() != null) {
            Glide.with(getContext()).load(bean.getData().getUserdata().getUser_pic()).into(haderimg);
            nickName.setText(bean.getData().getUserdata().getUser_nickname());
            tv_autograp.setText(bean.getData().getUserdata().getUser_autograph());
        }
        softtextimage.setText(bean.getData().getSofttext_text());
        ImageView imv = view.findViewById(R.id.iv1);
        if (bean.getData().getSofttextimage().size() > 0) {
            Glide.with(getContext()).load(bean.getData().getSofttextimage().get(0).getSofttextimage_url()).into(imv);
        }
        collection = view.findViewById(R.id.softtextimage_collection);
        collection.setOnClickListener(this);
        if (bean.getData().getUsercollection() == 1) {
            collection.setSelected(true);
        } else {
            collection.setSelected(false);
        }
        tv_num.setText(bean.getData().getSofttext_collection() + "");
        userpoint = view.findViewById(R.id.softtextimage_Userpoint);
        userpoint.setOnClickListener(this);
        if (bean.getData().getUserpoint() == 1) {
            userpoint.setSelected(true);
        } else {
            userpoint.setSelected(false);
        }
        mSoftTextPoint = bean.getData().getSofttext_point();
        userpoint.setText(String.valueOf(mSoftTextPoint));
        if (bean.getData().getUserfans() == 1) {
            circle_Userfans.setText("已关注");
        } else {
            circle_Userfans.setText("+ 关注");
        }
        view.findViewById(R.id.softtextimage_dashang).setOnClickListener(this);
        view.findViewById(R.id.softtextimage_shear).setOnClickListener(this);
        view.findViewById(R.id.soft_tougao).setOnClickListener(this);
        findViewById(R.id.tv_more).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.softtextimage_collection://收藏
                if (collection.isSelected()) {
                    Advertorial_softtextnotcollection();
                } else {
                    Advertorial_softtextcollection();
                }
                break;
            case R.id.softtextimage_Userpoint://点赞
                if (userpoint.isSelected()) {
                    Advertorial_softtextisnotpoint();
                } else {
                    Advertorial_softtextispoint();
                }
                break;
            case R.id.softtextimage_dashang://打赏
                dialog.show();
                break;
            case R.id.softtextimage_shear://分享
                share.show();

                break;
            case R.id.soft_tougao://投稿
                intent = new Intent(context, Act_addsofttext.class);
                intent.putExtra("position", 2);
                intent.putExtra("title", "我要投稿");
                context.startActivity(intent);
                break;
            case R.id.circle_Userfans://关注
                if (circle_Userfans.getText().toString().equals("+ 关注")) {//走关注
                    setClick(bean.getData().getUser_id() + "");
                } else {
                    getUsernotfans(bean.getData().getUser_id() + "");
                }
                break;
            case R.id.tv_more:
                intent = new Intent(context, CommentActivity.class);
                intent.putExtra("soft_text_id", String.valueOf(bean.getData().getSofttext_id()));
                context.startActivity(intent);
                break;
        }
    }

    /**
     * 软文收藏
     */
    public void Advertorial_softtextcollection() {
        HttpHelper.Advertorial_softtextcollection(aCache.getAsString("user_id"),
                bean.getData().getSofttext_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
                            collection.setSelected(true);
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
     * 软文=软文取消收藏
     */
    DetailVideoBean entity;

    public void Advertorial_softtextnotcollection() {
        HttpHelper.Advertorial_softtextnotcollection(aCache.getAsString("user_id"),
                bean.getData().getSofttext_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
                            collection.setSelected(false);
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
     * 软文取消点赞
     */
    public void Advertorial_softtextisnotpoint() {
        HttpHelper.Advertorial_softtextisnotpoint(aCache.getAsString("user_id"), bean.getData().getSofttext_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
                    mSoftTextPoint--;
                    userpoint.setText(String.valueOf(mSoftTextPoint));
                    userpoint.setSelected(false);
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
     * 软文点赞
     */
    public void Advertorial_softtextispoint() {
        HttpHelper.Advertorial_softtextispoint(aCache.getAsString("user_id"), bean.getData().getSofttext_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
                    mSoftTextPoint--;
                    userpoint.setText(String.valueOf(mSoftTextPoint));
                    userpoint.setSelected(true);
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
     * 打赏
     *
     * @param p
     */
    @Override
    public void onItem(String p) {
        number = p;
        Advertorial_softtextreward();
    }


    /**
     * 软文打赏
     */
    private String number = "";

    public void Advertorial_softtextreward() {
        HttpHelper.Advertorial_softtextreward(number, aCache.getAsString("user_id"), bean.getData().getSofttext_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
    public void onItemShear(int p) {
        loding.show();
        partakeofvideosofttext(p);
    }

    /**
     * 分享接口
     */
    public void partakeofvideosofttext(final int type) {
        HttpHelper.partakeofvideosofttext(bean.getData().getSofttext_id() + "", "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                loding.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                ShearBean entity111 = gson.fromJson(succeed, ShearBean.class);
                if (entity111.getCode() == 0 && entity111.getMsg().equals("访问成功")) {
                    switch (type) {
                        case 1://分享到微信
                        case 2://分享到微信
                            WxShareUtils.shareWeb(type, getContext(), "wx153551c2cce0e6a8", entity111.getUrl(), bean.getData().getSofttext_title(), bean.getData().getSofttext_text(), null);
                            break;
                        case 3:
                            WxShareUtils.showShare(activity, bean.getData().getSofttext_title(), bean.getData().getSofttext_text(), entity111.getUrl());
                            break;
                    }

                }
                loding.dismiss();
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 关注
     *
     * @param clickId
     */
    private void setClick(String clickId) {
        loding.show();
        HttpHelper.getUserClickAttention(clickId, LocalInfoUtils.getUserId() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                loding.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
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
                loding.dismiss();
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
        loding.show();
        HttpHelper.getUsernotfans(LocalInfoUtils.getUserId() + "", clickId, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                loding.dismiss();

            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
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
                loding.dismiss();
            }
        });
    }

}
