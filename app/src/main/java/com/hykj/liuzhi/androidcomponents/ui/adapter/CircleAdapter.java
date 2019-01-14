package com.hykj.liuzhi.androidcomponents.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CircleAdapter extends BaseMultiItemQuickAdapter<CircleBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CircleAdapter(List<CircleBean> data) {
        super(data);
        addItemType(CircleBean.THREE_SMALL, R.layout.layout_item_circle_three_small);
        addItemType(CircleBean.RIGHT_BIG, R.layout.layout_item_circle_right_big);
        addItemType(CircleBean.LEFT_BIG, R.layout.layout_item_circle_left_big);
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleBean item) {

    }
}
