package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.DetailCircleImageListBean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */
public class DetailImageTextListAdapter extends BaseQuickAdapter<DetailCircleImageListBean.DataBean, BaseViewHolder> {
    public DetailImageTextListAdapter(@Nullable List<DetailCircleImageListBean.DataBean> data) {
        super(R.layout.layout_item_detail_comment, data);
    }
    @Override
    protected void convert(final BaseViewHolder helper, DetailCircleImageListBean.DataBean item) {
        ImageView avatar = helper.getView(R.id.iv_icon);
        Glide.with(helper.itemView.getContext()).load(item.getUserdata().getUser_pic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatar);
        helper.addOnClickListener(R.id.iv_more);
        helper.setText(R.id.iv_name, item.getUserdata().getUser_nickname());
        helper.setText(R.id.iv_neirong, item.getMessage_message());
        helper.setText(R.id.iv_time, com.hykj.liuzhi.androidcomponents.ui.activity.video.DateUtils.timesTwo(item.getMessage_creattime() + ""));
    }
}
