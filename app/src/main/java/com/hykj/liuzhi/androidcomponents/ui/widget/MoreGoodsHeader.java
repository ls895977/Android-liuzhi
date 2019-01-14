package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.activity.Act_CommodityCategory;
import com.hykj.liuzhi.androidcomponents.ui.activity.GoodDetailActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */


public class MoreGoodsHeader extends RelativeLayout {
    public MoreGoodsHeader(Context context) {
        super(context);
        initView(context);
    }

    private void initView(final Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_goods_more_header, this, true);
        ButterKnife.bind(this);
        root.findViewById(R.id.goods_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, Act_CommodityCategory.class);
                context.startActivity(intent);
            }
        });

    }


}
