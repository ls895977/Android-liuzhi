package com.hykj.liuzhi.androidcomponents.ui.activity.min.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AddContextBean;

import java.util.List;

public class AddChoseAdapter extends BaseQuickAdapter<AddContextBean.DataBean.ArrayBean, BaseViewHolder> {
    public AddChoseAdapter(@Nullable List<AddContextBean.DataBean.ArrayBean> data) {
        super(R.layout.item_addchose, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, AddContextBean.DataBean.ArrayBean item) {
        helper.setText(R.id.add_text, item.getName());
    }
}
