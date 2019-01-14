package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;
import com.hykj.liuzhi.androidcomponents.bean.CommodityCategoryType;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CommodityCategoryAdapter extends BaseMultiItemQuickAdapter<CommodityCategoryType, BaseViewHolder> {
    private Context context;

    public CommodityCategoryAdapter(List<CommodityCategoryType> data, Context context1) {
        super(data);
        this.context = context1;
        addItemType(CircleBean.THREE_SMALL, R.layout.item_commoditycategory1);
        addItemType(CircleBean.RIGHT_BIG, R.layout.item_commoditycategory2);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityCategoryType item) {
        switch (helper.getItemViewType()) {
            case CircleBean.THREE_SMALL:
                RoundedImageView img = helper.getView(R.id.shop_1);
                Glide.with(context).load(item.getGoods_pic()).into(img);
                helper.setText(R.id.shop_detale, item.getGoods_name());
                helper.setText(R.id.money, "¥" + item.getGoods_price());
                break;
            case CircleBean.RIGHT_BIG:
                helper.addOnClickListener(R.id.item1).addOnClickListener(R.id.item2);
                RoundedImageView img1 = helper.getView(R.id.RoundedImageView1);
                Glide.with(context).load(item.getArray().get(0).getGoods_pic()).into(img1);
                helper.setText(R.id.shop_detale1, item.getArray().get(0).getGoods_name());
                helper.setText(R.id.money1, "¥" + item.getArray().get(0).getGoods_price());
                RoundedImageView img2 = helper.getView(R.id.RoundedImageView2);
                Glide.with(context).load(item.getArray().get(1).getGoods_pic()).into(img2);
                helper.setText(R.id.shop_detale2, item.getArray().get(1).getGoods_name());
                helper.setText(R.id.money2, "¥" + item.getArray().get(1).getGoods_price());
                break;
        }
    }
}
