package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.UserMessageBean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */


public class MessageAdapter extends BaseQuickAdapter<UserMessageBean,BaseViewHolder> {
    public MessageAdapter(@Nullable List<UserMessageBean> data) {
        super(R.layout.layout_item_message,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserMessageBean item) {

    }
}
