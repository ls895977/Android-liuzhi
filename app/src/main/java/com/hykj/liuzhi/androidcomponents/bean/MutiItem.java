package com.hykj.liuzhi.androidcomponents.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author: lujialei
 * @date: 2018/9/28
 * @describe:
 */


public class MutiItem implements MultiItemEntity {
    public static final int IMAGE_TEXT_INSIDE = 1; //图在底文字里面
    public static final int SECTION_HEADER = 2;//分区标题
    public static final int IMAGE_TEXT_TOP = 3;
    public static final int IMAGE_TEXT_BOTTOM = 4;
    public static final int SOFT_ARTICLE = 5;
    public static final int MORE = 6;
    private int type;

    public MutiItem(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

}
