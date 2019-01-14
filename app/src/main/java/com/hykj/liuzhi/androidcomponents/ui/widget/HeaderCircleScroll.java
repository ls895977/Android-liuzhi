package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.activity.PersonDetailActivity;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class HeaderCircleScroll extends RelativeLayout {
    @BindView(R.id.iv0)
    RoundedImageView iv0;
    @BindView(R.id.iv1)
    RoundedImageView iv1;
    @BindView(R.id.iv2)
    RoundedImageView iv2;
    @BindView(R.id.name1)
    TextView name1;
    @BindView(R.id.name2)
    TextView name2;
    @BindView(R.id.name3)
    TextView name3;
    private List<CircleFragmentBean.DataBean.UserdataBean> datas;

    public HeaderCircleScroll(Context context, List<CircleFragmentBean.DataBean.UserdataBean> datas1) {
        super(context);
        datas = datas1;
        initView(context);
    }

    private void initView(final Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_header_circle_horizon_scroll, this, true);
        RelativeLayout rl_header_user1 = root.findViewById(R.id.rl_header_user1);
        RelativeLayout rl_header_user2 = root.findViewById(R.id.rl_header_user2);
        RelativeLayout rl_header_user3 = root.findViewById(R.id.rl_header_user3);
        ButterKnife.bind(this);
        Log.e("aa", "-----------" + datas.size());
        if (datas.size() >= 1) {
            Glide.with(this).load(datas.get(0).getUserdetail().getUser_pic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv0);
            name1.setText(datas.get(0).getUserdetail().getUser_nickname());
        } else {
            Glide.with(this).load(R.mipmap.test1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv0);
        }
        if (datas.size() >= 2) {
            name2.setText(datas.get(1).getUserdetail().getUser_nickname());
            Glide.with(this).load(datas.get(1).getUserdetail().getUser_pic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv1);
        } else {
            Glide.with(this).load(R.mipmap.test2).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv1);
        }
        if (datas.size() >= 3) {
            name3.setText(datas.get(2).getUserdetail().getUser_nickname());
            Glide.with(this).load(datas.get(2).getUserdetail().getUser_pic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv2);
        } else {
            Glide.with(this).load(R.mipmap.test3).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv2);
        }
        rl_header_user1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {//作者图文
                if (datas.size() >= 1) {
                    Intent intent = new Intent();
                    intent.setClass(context, PersonDetailActivity.class);
                    intent.putExtra("userBean", datas.get(0));
                    context.startActivity(intent);
                }
            }
        });
        rl_header_user2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() >= 2) {
                    Intent intent = new Intent(context, PersonDetailActivity.class);
                    intent.putExtra("userBean", datas.get(1));
                    context.startActivity(intent);
                }
            }
        });
        rl_header_user3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size() >= 3) {
                    Intent intent = new Intent(context, PersonDetailActivity.class);
                    intent.putExtra("userBean", datas.get(2));
                    context.startActivity(intent);
                }
            }
        });
    }


}
