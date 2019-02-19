package com.hykj.liuzhi.androidcomponents.bean;

import java.io.Serializable;
import java.util.List;

public class CartBean implements Serializable {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"goods_id":1,"goodsshopcar_num":11,"goodsshopcar_price":"110","goodsdata":{"goods_name":"商品4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg"}},{"goods_id":2,"goodsshopcar_num":1,"goodsshopcar_price":"1","goodsdata":{"goods_name":"商品5","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/firstpic/35225e583b28d2a01825f540b064d738.jpg"}},{"goods_id":4,"goodsshopcar_num":1,"goodsshopcar_price":"1","goodsdata":{"goods_name":"商品7","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods4/firstpic/c5fed4d8313fe17df9645f68b3a914a3.jpg"}},{"goods_id":6,"goodsshopcar_num":1,"goodsshopcar_price":"1","goodsdata":{"goods_name":"商品42","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods6/firstpic/e55470102748bad05413a753be2edc26.jpg"}}],"total":4,"amount":1,"page":1,"number":10}
     */
    private int error;
    private int code;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * array : [{"goods_id":1,"goodsshopcar_num":11,"goodsshopcar_price":"110","goodsdata":{"goods_name":"商品4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg"}},{"goods_id":2,"goodsshopcar_num":1,"goodsshopcar_price":"1","goodsdata":{"goods_name":"商品5","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/firstpic/35225e583b28d2a01825f540b064d738.jpg"}},{"goods_id":4,"goodsshopcar_num":1,"goodsshopcar_price":"1","goodsdata":{"goods_name":"商品7","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods4/firstpic/c5fed4d8313fe17df9645f68b3a914a3.jpg"}},{"goods_id":6,"goodsshopcar_num":1,"goodsshopcar_price":"1","goodsdata":{"goods_name":"商品42","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods6/firstpic/e55470102748bad05413a753be2edc26.jpg"}}]
         * total : 4
         * amount : 1
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private int page;
        private int number;
        private List<ArrayBean> array;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean implements Serializable {
            /**
             * goods_id : 1
             * goodsshopcar_num : 11
             * goodsshopcar_price : 110
             * goodsdata : {"goods_name":"商品4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg"}
             */
            private Double danjiaprice;
            private String goodsshopcar_id;
            public String getGoodsshopcar_id() {
                return goodsshopcar_id;
            }

            public void setGoodsshopcar_id(String goodsshopcar_id) {
                this.goodsshopcar_id = goodsshopcar_id;
            }

            public Double getDanjiaprice() {
                return danjiaprice;
            }

            public void setDanjiaprice(Double danjiaprice) {
                this.danjiaprice = danjiaprice;
            }

            private boolean cartShop;

            public boolean isCartShop() {
                return cartShop;
            }

            public void setCartShop(boolean cartShop) {
                this.cartShop = cartShop;
            }

            private int goods_id;
            private int goodsshopcar_num;
            private String goodsshopcar_price;
            private GoodsdataBean goodsdata;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getGoodsshopcar_num() {
                return goodsshopcar_num;
            }

            public void setGoodsshopcar_num(int goodsshopcar_num) {
                this.goodsshopcar_num = goodsshopcar_num;
            }

            public String getGoodsshopcar_price() {
                return goodsshopcar_price;
            }

            public void setGoodsshopcar_price(String goodsshopcar_price) {
                this.goodsshopcar_price = goodsshopcar_price;
            }

            public GoodsdataBean getGoodsdata() {
                return goodsdata;
            }

            public void setGoodsdata(GoodsdataBean goodsdata) {
                this.goodsdata = goodsdata;
            }

            public static class GoodsdataBean implements Serializable {
                /**
                 * goods_name : 商品4
                 * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg
                 */

                private String goods_name;
                private String goods_pic;

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_pic() {
                    return goods_pic;
                }

                public void setGoods_pic(String goods_pic) {
                    this.goods_pic = goods_pic;
                }
            }
        }
    }
}
