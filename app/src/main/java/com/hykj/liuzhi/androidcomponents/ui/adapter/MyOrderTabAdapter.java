package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;

import java.util.List;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */
public class MyOrderTabAdapter extends BaseQuickAdapter<UserordersBean.DataBean.ArrayBean, BaseViewHolder> {
    private Context context;

    public MyOrderTabAdapter(@Nullable List<UserordersBean.DataBean.ArrayBean> data, Context context1) {
        super(R.layout.layout_item_my_order_tab, data);
        context = context1;
    }

    private int sum = 0;
    RelativeLayout item1, item2, item3;
    private RecyclerView recyclerView;

    @Override
    protected void convert(BaseViewHolder helper, UserordersBean.DataBean.ArrayBean item) {
        helper.setText(R.id.oder_number, "订单号:" + item.getOrders_id());
        helper.setText(R.id.oder_status, item.getOrdertype());
        for (int i = 0; i < item.getGoodsdata().size(); i++) {
            sum += Integer.valueOf(item.getGoodsdata().get(i).getOrdersgoods_money());
        }
        item1 = helper.getView(R.id.oder_fukuan);
        item2 = helper.getView(R.id.oder_shouhuo);
        item3 = helper.getView(R.id.oder_wancheng);
        String money = sum + "";
        helper.setText(R.id.oder_pricer, "￥" + money);
        switch (item.getOrdertype()) {
            case "待付款":
                item1.setVisibility(View.VISIBLE);
                item2.setVisibility(View.GONE);
                item3.setVisibility(View.GONE);
                helper.addOnClickListener(R.id.oder_cancel);
                helper.addOnClickListener(R.id.oder_pay);
                break;
            case "待收货":
                item1.setVisibility(View.GONE);
                item2.setVisibility(View.VISIBLE);
                item3.setVisibility(View.GONE);
                helper.addOnClickListener(R.id.oder_wuliu);
                helper.addOnClickListener(R.id.oder_centerAddr);
                break;
            case "已完成":
                item1.setVisibility(View.GONE);
                item2.setVisibility(View.GONE);
                item3.setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.oder_delete);
                break;
        }
        recyclerView = helper.getView(R.id.oder_recyclerview);
        MyOrderChlideTabAdapter adapter = new MyOrderChlideTabAdapter(item.getGoodsdata(), context);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
