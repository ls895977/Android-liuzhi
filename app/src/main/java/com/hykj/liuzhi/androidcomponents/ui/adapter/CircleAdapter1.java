package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean1;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CircleAdapter1 extends BaseMultiItemQuickAdapter<CircleBean1, BaseViewHolder> {
    private Context context;

    public CircleAdapter1(List<CircleBean1> data, Context context1) {
        super(data);
        this.context = context1;
        addItemType(CircleBean1.THREE_SMALL, R.layout.layout_item_circle_three_small);
        addItemType(CircleBean1.RIGHT_BIG, R.layout.layout_item_circle_right_big);
        addItemType(CircleBean1.LEFT_BIG, R.layout.layout_item_circle_left_big);
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleBean1 item) {
        switch (helper.getItemViewType()) {
            case CircleBean1.THREE_SMALL:
                helper.addOnClickListener(R.id.THREE_SMALL_img1);
                if (item.getArray().size() >= 1) {
                    Glide.with(context).load(item.getArray().get(0).getImagetextimage_url()).into((ImageView) helper.getView(R.id.THREE_SMALL_img1));
                }
                helper.addOnClickListener(R.id.THREE_SMALL_img2);
                if (item.getArray().size() >= 2) {
                    Glide.with(context).load(item.getArray().get(1).getImagetextimage_url()).into((ImageView) helper.getView(R.id.THREE_SMALL_img2));
                }
                helper.addOnClickListener(R.id.THREE_SMALL_img3);
                if (item.getArray().size() >= 3) {
                    Glide.with(context).load(item.getArray().get(2).getImagetextimage_url()).into((ImageView) helper.getView(R.id.THREE_SMALL_img3));
                }
                break;
            case CircleBean1.RIGHT_BIG:
                helper.addOnClickListener(R.id.RIGHT_BIG_img1);
                if (item.getArray().size() >= 1) {
                    Glide.with(context).load(item.getArray().get(0).getImagetextimage_url()).into((ImageView) helper.getView(R.id.RIGHT_BIG_img1));
                }
                helper.addOnClickListener(R.id.RIGHT_BIG_img2);
                if (item.getArray().size() >= 2) {
                    Glide.with(context).load(item.getArray().get(1).getImagetextimage_url()).into((ImageView) helper.getView(R.id.RIGHT_BIG_img2));
                }
                helper.addOnClickListener(R.id.RIGHT_BIG_img3);
                if (item.getArray().size() >= 3) {
                    Glide.with(context).load(item.getArray().get(2).getImagetextimage_url()).into((ImageView) helper.getView(R.id.RIGHT_BIG_img3));
                }
                break;
            case CircleBean1.LEFT_BIG:
                helper.addOnClickListener(R.id.LEFT_BIG_img1);
                if (item.getArray().size() >= 1) {
                    Glide.with(context).load(item.getArray().get(0).getImagetextimage_url()).into((ImageView) helper.getView(R.id.LEFT_BIG_img1));
                }
                helper.addOnClickListener(R.id.LEFT_BIG_img2);
                if (item.getArray().size() >= 2) {
                    Glide.with(context).load(item.getArray().get(1).getImagetextimage_url()).into((ImageView) helper.getView(R.id.LEFT_BIG_img2));
                }
                helper.addOnClickListener(R.id.LEFT_BIG_img3);
                if (item.getArray().size() >= 3) {
                    Glide.with(context).load(item.getArray().get(2).getImagetextimage_url()).into((ImageView) helper.getView(R.id.LEFT_BIG_img3));
                }
                break;
        }
    }
}
