package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DetailCommetListBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.PersonDetailActivity;

import java.util.List;

/**
 * Created by Terminator on 2019/3/28.
 */
public class ReplyAdapter extends BaseQuickAdapter<DetailCommetListBean.DataBean.ArrayBean, BaseViewHolder> {

    public ReplyAdapter(@Nullable List<DetailCommetListBean.DataBean.ArrayBean> data) {
        super(R.layout.item_reply, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DetailCommetListBean.DataBean.ArrayBean item) {
        helper.setText(R.id.tv_reply, item.getMessage_message())
                .setText(R.id.tv_reply_name, item.getUserdata().getUser_nickname())
                .getView(R.id.tv_reply_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, PersonDetailActivity.class);
                intent.putExtra("User_id", item.getUser_id() + "");
                mContext.startActivity(intent);
            }
        });
    }
}
