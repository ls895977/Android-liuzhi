package com.hykj.liuzhi.androidcomponents.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CircleBean implements MultiItemEntity {

    public static final int THREE_SMALL = 1;
    public static final int RIGHT_BIG = 2;
    public static final int LEFT_BIG = 3;

    private int type;

    @Override
    public int getItemType() {
        return type;
    }

    public CircleBean(int type) {
        this.type = type;
    }
}
