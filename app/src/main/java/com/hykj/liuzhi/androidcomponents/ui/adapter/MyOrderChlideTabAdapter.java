package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */
public class MyOrderChlideTabAdapter extends BaseQuickAdapter<UserordersBean.DataBean.ArrayBean.GoodsdataBean, BaseViewHolder> {
    private Context context;

    public MyOrderChlideTabAdapter(@Nullable List<UserordersBean.DataBean.ArrayBean.GoodsdataBean> data, Context context1) {
        super(R.layout.item_shop_oder, data);
        context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserordersBean.DataBean.ArrayBean.GoodsdataBean item) {
        Glide.with(context).load(item.getGoods_pic()).into((ImageView) helper.getView(R.id.oder_img));
        helper.setText(R.id.oder_name, item.getGoods_name()).setText(R.id.oder_price, "¥" + item.getOrdersgoods_money());
        helper.setText(R.id.oder_num,"X"+item.getOrdersgoods_num());
    }
}
