package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.library.AutoFlowLayout;
import com.hykj.liuzhi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lujialei
 * @date: 2018/10/22
 * @describe:
 */


public class FindSearchLayout extends RelativeLayout {
    @BindView(R.id.afl_cotent)
    AutoFlowLayout aflCotent;
    //    String[] arr = {"太阳眼镜","健身器","拉杆箱","行李箱","iphone 7 plus" ,"棉麻素色床品","桌面电风扇","鼠标","机械键盘","键盘","键鼠套装"};
    List<String> datas;

    public void setDatas(List<String> datas, Context context) {
        this.datas = datas;
        initView(context);
    }

    public FindSearchLayout(Context context) {
        this(context, null);
    }

    public FindSearchLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FindSearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_find_search_layout, this, true);
        ButterKnife.bind(this);
        aflCotent.setVerticalSpace(240);
        for (int i = 0; i < datas.size(); i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.sub_item, null);
            TextView tvAttrTag = (TextView) item.findViewById(R.id.tv_attr_tag);
            tvAttrTag.setText(datas.get(i));
            aflCotent.addView(item);
        }
    }
}
