package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.InForMationBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.UserMessageBean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */


public class MessageAdapter extends BaseQuickAdapter<InForMationBean.DataBean.ArrayBean, BaseViewHolder> {
    public MessageAdapter(@Nullable List<InForMationBean.DataBean.ArrayBean> data) {
        super(R.layout.layout_item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InForMationBean.DataBean.ArrayBean item) {
        helper.setText(R.id.shop_name,item.getTitle()).setText(R.id.shop_context,item.getShowmessage());
    }
}
