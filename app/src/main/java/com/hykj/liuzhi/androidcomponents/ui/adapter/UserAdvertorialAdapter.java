package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.GetgoodScatesBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserAdvertorialBean;

import java.util.List;

public class UserAdvertorialAdapter extends BaseQuickAdapter<UserAdvertorialBean.DataBean.ArrayBean, BaseViewHolder> {
    private Context context1;

    public UserAdvertorialAdapter(Context context, @Nullable List<UserAdvertorialBean.DataBean.ArrayBean> data) {
        super(R.layout.layout_adapter_item_image_text_top, data);
        context1 = context;
    }
    @Override
    protected void convert(final BaseViewHolder helper, UserAdvertorialBean.DataBean.ArrayBean item) {
        Glide.with(context1).load(item.getUserdata().getUser_pic()).into((ImageView) helper.getView(R.id.iv_top_icon));
        if (item.getSofttextimagedata() != null) {
            Glide.with(context1).load(item.getSofttextimagedata().getSofttextimage_url()).into((ImageView) helper.getView(R.id.iv_center));
        }
        helper.setText(R.id.tv_nickName, item.getUserdata().getUser_nickname());
    }
}
