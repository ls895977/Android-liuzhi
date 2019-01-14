package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.LoGinRecordBean;

import java.util.List;

public class LoginRecordAdapter extends BaseQuickAdapter< LoGinRecordBean.DataBean.ArrayBean, BaseViewHolder> {
    public LoginRecordAdapter(int layoutResId, @Nullable List< LoGinRecordBean.DataBean.ArrayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,  LoGinRecordBean.DataBean.ArrayBean item) {
        helper.setText(R.id.tv_login_record_time, item.getRegistry_logintime())
                .setText(R.id.tv_login_record_loginadress, item.getRegistry_address())
                .setImageResource(R.id.iv_loginrecord_point, R.mipmap.normal_state);
    }
}
