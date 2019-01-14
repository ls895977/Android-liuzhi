package com.hykj.liuzhi.androidcomponents.ui.bottomnavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hykj.liuzhi.R;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class NavigationItem extends RelativeLayout {
    public NavigationItem(Context context) {
        this(context,null);
    }

    public NavigationItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NavigationItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_navi_item,this,true);
        ImageView iv = view.findViewById(R.id.iv);
        iv.setImageResource(R.drawable.selector_home);
    }
}
