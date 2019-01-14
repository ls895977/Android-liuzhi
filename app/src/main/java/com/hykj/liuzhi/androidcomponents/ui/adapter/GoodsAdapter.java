package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.view.MyLinearLayout;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/14
 * @describe:
 */


public class GoodsAdapter extends BaseQuickAdapter<ShopHomeBean.DataBean.ArrayBean, BaseViewHolder> {
    private Context context;

    public GoodsAdapter(@Nullable List<ShopHomeBean.DataBean.ArrayBean> data, Context context1) {
        super(R.layout.layout_item_goods, data);
        context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopHomeBean.DataBean.ArrayBean item) {

        Glide.with(context).load(item.getGoods_pic()).into((ImageView) helper.getView(R.id.iv_goods));
        helper.setText(R.id.tv_title, item.getGoods_name())
                .setText(R.id.tv_stock, "限量" + item.getGoods_stock() + "件")
                .setText(R.id.tv_goods_price, "¥" + item.getGoods_price())
                .setText(R.id.tv_goods_goods_volume, item.getGoods_volume() + "已拥有");
        MyLinearLayout linearLayout = helper.getView(R.id.tv_goods_lab);
        linearLayout.removeAllViews();
        if(item.getLabelname()!=null)
        for (int i = 0; i < item.getLabelname().size(); i++) {
            if (i < 8) {
                View view = LayoutInflater.from(context).inflate(R.layout.tv_shoplab, null);
                TextView tab = view.findViewById(R.id.tab_shop);
                tab.setText(item.getLabelname().get(i));
                linearLayout.addView(view);
            }
        }
    }
}
