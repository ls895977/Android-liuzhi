package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.mock.Mock;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GoodDetailDetailBean;
import com.youth.banner.Banner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */


public class GoodsDetailHeader extends LinearLayout {
    @BindView(R.id.banner)
    Banner banner;
    GoodDetailDetailBean bean;
    private TextView shopName, shopPrice, shopIntegral, jifen;

    public GoodsDetailHeader(Context context, GoodDetailDetailBean bean1) {
        super(context);
        this.bean = bean1;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_header_goods_detail_item, this, true);
        ButterKnife.bind(this);
        if (bean.getData().getImage() != null) {
            banner.setImages(bean.getData().getImage());
        } else {
            ArrayList<Integer> imageUrls = new ArrayList<>();
            imageUrls.add(R.mipmap.default_banner);
            banner.setImages(imageUrls);
        }
        banner.setImageLoader(new GlideImageLoader())
                .setDelayTime(5000)
                .start();
        shopName = view.findViewById(R.id.goods_detail_shopName);
        shopName.setText(bean.getData().getName());
        shopPrice = view.findViewById(R.id.goods_detail_price);
        shopPrice.setText("¥" + bean.getData().getPrice());
        shopIntegral = view.findViewById(R.id.goods_detail_integral);
        shopIntegral.setText("已售" + bean.getData().getIntegral());
        jifen = view.findViewById(R.id.goods_detail_Integra2);
        jifen.setText("可获得" + bean.getData().getIntegral() + "积分");
    }
}
