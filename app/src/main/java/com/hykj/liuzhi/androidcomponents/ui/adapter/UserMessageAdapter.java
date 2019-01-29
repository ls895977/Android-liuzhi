package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.UserMessageBean;
import com.hykj.liuzhi.androidcomponents.utils.RoundImageView;
import com.hykj.liuzhi.androidcomponents.utils.TimeUtils;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */


public class UserMessageAdapter extends BaseQuickAdapter<UserMessageBean.DataBean.ArrayBean, BaseViewHolder> {
    Context context;

    public UserMessageAdapter(@Nullable List<UserMessageBean.DataBean.ArrayBean> data, Context context1) {
        super(R.layout.layout_item_usermessage, data);
        context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserMessageBean.DataBean.ArrayBean item) {
        RoundImageView icon = helper.getView(R.id.iv_icon);
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.test1);
        Glide.with(context).load(item.getUserdata().getUser_pic()).apply(options).into(icon);
        helper.setText(R.id.nickName_user, item.getUserdata().getUser_nickname()).setText(R.id.Context_user, item.getMessage())
                .setText(R.id.time_user, TimeUtils.Hourmin(item.getMessage_creattime() + ""));
    }
}
