package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.UserAttentionBean;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class AttentionAdapter extends BaseQuickAdapter<UserAttentionBean.DataBean.ArrayBean, BaseViewHolder> {
    private List<UserAttentionBean.DataBean.ArrayBean> list;
    private Context mContext;
    private String type;

    public AttentionAdapter(Context context, @Nullable List<UserAttentionBean.DataBean.ArrayBean> data, String type) {
        super(R.layout.item_attention, data);
        this.list = data;
        this.mContext = context;
        this.type = type;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final UserAttentionBean.DataBean.ArrayBean item) {
        TextView tvAttentionName = helper.getView(R.id.tv_attention_name);
        TextView tvAttentionMsg = helper.getView(R.id.tv_attention_msg);
        ImageView ivAttentionIma = helper.getView(R.id.iv_attention_ima);
        new GlideImageLoader().displayImage(mContext, item.getUserdata().getUser_pic(), ivAttentionIma);
        tvAttentionName.setText(item.getUserdata().getUser_nickname());
        tvAttentionMsg.setText(item.getUserdata().getUser_autograph());
        final TextView textView = helper.getView(R.id.textView);

        helper.addOnClickListener(R.id.textView);

        if (item.getUserfans() != null && !item.getUserfans().isEmpty()) {
            if (item.getUserfans().equals("1")) {
                //已关注
                textView.setBackground(mContext.getDrawable(R.drawable.bg_button_el_2dp));
                textView.setTextColor(mContext.getResources().getColor(R.color.public_aaaaaa));
                textView.setText("已关注");
            } else {
                textView.setBackground(mContext.getDrawable(R.drawable.bg_button_ffb400_2dp));
                textView.setTextColor(mContext.getResources().getColor(R.color.white));
                textView.setText("关注");
            }
        } else {
            textView.setBackground(mContext.getDrawable(R.drawable.bg_button_el_2dp));
            textView.setTextColor(mContext.getResources().getColor(R.color.public_aaaaaa));
            textView.setText("已关注");
        }
        textView.setTag(false);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tag = (boolean) textView.getTag();
                tag = !tag;
                if (tag) {
                    textView.setBackground(mContext.getDrawable(R.drawable.bg_button_ffb400_2dp));
                    textView.setTextColor(mContext.getResources().getColor(R.color.white));
                    textView.setText("关注");
                    setClick(item.getUser_collid());
                } else {
                    textView.setBackground(mContext.getDrawable(R.drawable.bg_button_el_2dp));
                    textView.setTextColor(mContext.getResources().getColor(R.color.public_aaaaaa));
                    textView.setText("已关注");
                    setClick(item.getUser_collid());
                }
                textView.setTag(tag);
            }
        });
    }

    private void setClick(int clickId) {

        HttpHelper.getUserClickAttention(clickId+"", LocalInfoUtils.getUserId()+"", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {

            }

            @Override
            public void onSucceed(String succeed) {
                Logger.t("点击按钮是否关注").i(succeed);
            }

            @Override
            public void onError(String error) {
                ToastManage.s(mContext, error);
            }
        });
    }
}
