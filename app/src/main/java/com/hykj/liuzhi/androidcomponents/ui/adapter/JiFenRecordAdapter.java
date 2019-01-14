package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.JiFenRecordBean;

import java.util.List;

public class JiFenRecordAdapter extends BaseQuickAdapter<JiFenRecordBean, BaseViewHolder> {
    public JiFenRecordAdapter(int layoutResId, @Nullable List<JiFenRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiFenRecordBean item) {

        helper.setText(R.id.tv_jifen_record_title, item.getJifentitle())
                .setText(R.id.tv_jifen_record_time, item.getJifentime())
                .setText(R.id.tv_jifen_record_count, item.getJifencount());

    }
}
