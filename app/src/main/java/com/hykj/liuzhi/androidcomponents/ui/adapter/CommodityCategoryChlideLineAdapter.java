package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.GetgoodScatesBean;
import com.hykj.liuzhi.androidcomponents.bean.UserAttentionBean;

import java.util.List;

public class CommodityCategoryChlideLineAdapter extends BaseQuickAdapter<GetgoodScatesBean.DataBean.ArrayBean.ChildrenBean, BaseViewHolder> {
    private Context context;
    public CommodityCategoryChlideLineAdapter(Context context1, @Nullable List<GetgoodScatesBean.DataBean.ArrayBean.ChildrenBean> data) {
        super(R.layout.item_commoditycategoryline, data);
        this.context=context1;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final GetgoodScatesBean.DataBean.ArrayBean.ChildrenBean item) {
        TextView tv = helper.getView(R.id.commodity_tv);
        tv.setText(item.getGoodscate_name());
        tv.setTextColor(context.getResources().getColor(R.color.coloer_yellow));
        tv.setTextSize(16);
        helper.addOnClickListener(R.id.commodity_tv);
    }
}
