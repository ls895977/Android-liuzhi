package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;

import java.util.List;

public class OffLineVideoAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public OffLineVideoAdapter(@Nullable List<Object> data) {
        super(R.layout.item_offfline_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
