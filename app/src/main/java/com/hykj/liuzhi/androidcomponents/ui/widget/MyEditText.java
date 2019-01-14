package com.hykj.liuzhi.androidcomponents.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hykj.liuzhi.R;


/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class MyEditText extends RelativeLayout {
    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_my_edit_text,this,true);

    }
}
