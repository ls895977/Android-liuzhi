package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CartBean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/16
 * @describe:
 */


public class CartAdapter extends BaseQuickAdapter<CartBean.DataBean.ArrayBean, BaseViewHolder> {
    private Context context;

    public CartAdapter(@Nullable List<CartBean.DataBean.ArrayBean> data, Context context1) {
        super(R.layout.layout_cart_content, data);
        this.context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartBean.DataBean.ArrayBean item) {
        TextView cb = helper.getView(R.id.ch_delete);
        if (item.isCartShop()) {
            cb.setSelected(true);
        } else {
            cb.setSelected(false);
        }
        helper.setText(R.id.shop_cart_num, item.getGoodsshopcar_num() + "")
                .setText(R.id.tv_title, item.getGoodsdata().getGoods_name())
                .setText(R.id.tv_price, ("¥" + item.getDanjiaprice() * item.getGoodsshopcar_num()));
        ImageView img = helper.getView(R.id.ch_hader);
        Glide.with(context).load(item.getGoodsdata().getGoods_pic()).into(img);
        helper.addOnClickListener(R.id.tv_jian);
        helper.addOnClickListener(R.id.tv_add);
    }
}
