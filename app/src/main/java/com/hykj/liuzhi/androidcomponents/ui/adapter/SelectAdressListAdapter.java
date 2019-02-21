package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.AllAddBean;

import java.util.List;

public class SelectAdressListAdapter extends BaseQuickAdapter<AllAddBean.DataBean.ArrayBean, BaseViewHolder> {
    public SelectAdressListAdapter(@Nullable List<AllAddBean.DataBean.ArrayBean> data) {
        super(R.layout.item_select_address_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllAddBean.DataBean.ArrayBean item) {
        helper.addOnClickListener(R.id.item_select_delete);
        helper.addOnClickListener(R.id.item_select_binaji);
        helper.addOnClickListener(R.id.ch_delete);
        helper.setText(R.id.item_select_name, item.getAddress_user())
                .setText(R.id.item_select_phone, item.getAddress_phone())
                .setText(R.id.item_select_addr, item.getAddress_address());
        CheckBox cb=helper.getView(R.id.ch_delete);
        if (item.getAddress_status() == 0) {
            cb.setChecked(false);
        } else {
            cb.setChecked(true);
        }
    }
}
