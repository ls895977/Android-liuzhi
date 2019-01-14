package com.hykj.liuzhi.androidcomponents.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CommodityCategoryType implements MultiItemEntity {

    public static final int THREE_SMALL = 1;
    public static final int RIGHT_BIG = 2;

    private int type;

    @Override
    public int getItemType() {
        return type;
    }

    public CommodityCategoryType(int type) {
        this.type = type;
    }

    private List<ArrayBean> array;

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    /**
     * goods_id : 1
     * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg
     * goods_name : 商品4
     * goods_price : 10
     */
    private int goods_id;
    private String goods_pic;
    private String goods_name;
    private String goods_price;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public static class ArrayBean {
        /**
         * goods_id : 1
         * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg
         * goods_name : 商品4
         * goods_price : 10
         */
        private int goods_id;
        private String goods_pic;
        private String goods_name;
        private String goods_price;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }
    }
}
