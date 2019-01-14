package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hykj.liuzhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lujialei
 * @date: 2018/9/29
 * @describe:
 */


public class MessageTopBar extends RelativeLayout {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Activity activity;


    public interface OnTopBarClickListener{
        void onChatClick(View v);
    }
    private OnTopBarClickListener mOnTopBarClickListener;
    public void setOnTopBarClickListener(OnTopBarClickListener mOnTopBarClickListener){
        this.mOnTopBarClickListener = mOnTopBarClickListener;
    }

    public MessageTopBar(Context context) {
        this(context, null);
    }

    public MessageTopBar(Context context, String s) {
        this(context, s, false);
    }

    public MessageTopBar(Context context, String s, boolean isShowBack) {
        super(context);
        initView(context);
        if (s != null) {
            setTitle(s);
        }
        ivLeft.setVisibility(isShowBack ? VISIBLE : GONE);
    }

    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_topbar_message, this, true);
        ButterKnife.bind(this);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    public void setTitle(String s) {
        tvTitle.setText(s);
    }


    @OnClick({R.id.iv_left})
    public void onViewClicked(View v) {
        if (v.getId()==R.id.iv_left){
            if (activity != null) {
                activity.onBackPressed();
            }
        }

    }
}
