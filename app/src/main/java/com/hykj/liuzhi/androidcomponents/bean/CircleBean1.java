package com.hykj.liuzhi.androidcomponents.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CircleBean1 implements MultiItemEntity {

    public static final int THREE_SMALL = 1;
    public static final int RIGHT_BIG = 2;
    public static final int LEFT_BIG = 3;
    private int type;

    @Override
    public int getItemType() {
        return type;
    }

    public CircleBean1(int type, List<ArrayBean> array) {
        this.type = type;
        this.array = array;
    }

    private List<ArrayBean> array;

    public List<ArrayBean> getArray() {
        return array;
    }
    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {

        private int user_id;
        private int goods_id;
        private int collection_type;
        private String goods_pic;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getCollection_type() {
            return collection_type;
        }

        public void setCollection_type(int collection_type) {
            this.collection_type = collection_type;
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

        private String goods_name;
        private String goods_price;


        private int imagetext_id;
        public int getImagetext_id() {
            return imagetext_id;
        }

        public void setImagetext_id(int imagetext_id) {
            this.imagetext_id = imagetext_id;
        }
        /**
         * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg
         */
        private String imagetextimage_url;

        public String getImagetextimage_url() {
            return imagetextimage_url;
        }

        public void setImagetextimage_url(String imagetextimage_url) {
            this.imagetextimage_url = imagetextimage_url;
        }
    }
}
