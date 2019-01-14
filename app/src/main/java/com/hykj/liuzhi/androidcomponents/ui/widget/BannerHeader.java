package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hykj.liuzhi.R;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */
public class BannerHeader extends RelativeLayout {
    @BindView(R.id.banner)
    Banner banner;

    public BannerHeader(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_header_banner, this, true);
        ButterKnife.bind(this);
    }

    public Banner getBanner(){
        return banner;
    }
}
