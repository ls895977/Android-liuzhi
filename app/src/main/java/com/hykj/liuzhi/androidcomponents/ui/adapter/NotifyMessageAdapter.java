package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.NotifyFragmentBean;
import com.hykj.liuzhi.androidcomponents.utils.TimeUtils;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */


public class NotifyMessageAdapter extends BaseQuickAdapter<NotifyFragmentBean.DataBean.ArrayBean, BaseViewHolder> {
    public NotifyMessageAdapter(@Nullable List<NotifyFragmentBean.DataBean.ArrayBean> data) {
        super(R.layout.layout_item_message_notify, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,NotifyFragmentBean.DataBean.ArrayBean item) {
        helper.setText(R.id.mesage_title,item.getSystemnotification_name()).setText(R.id.mesage_context,item.getSystemnotification_text())
        .setText(R.id.mesage_time,TimeUtils.Hourmin(item.getSystemnotification_creattime()+""));

    }
}
