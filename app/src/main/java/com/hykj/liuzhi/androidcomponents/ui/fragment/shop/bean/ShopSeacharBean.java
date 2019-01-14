package com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean;

import java.util.List;

public class ShopSeacharBean {


    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"goodsselect_name":"商","goodsselect_num":6,"goodsselect_creattime":1542627829}],"total":1,"amount":1,"page":1,"number":10}
     */

    private int code;
    private String msg;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    private DataBean data;
    private int error;
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

    public static class DataBean {
        /**
         * array : [{"goodsselect_name":"商","goodsselect_num":6,"goodsselect_creattime":1542627829}]
         * total : 1
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

        public static class ArrayBean {
            /**
             * goodsselect_name : 商
             * goodsselect_num : 6
             * goodsselect_creattime : 1542627829
             */

            private String goodsselect_name;
            private int goodsselect_num;
            private int goodsselect_creattime;

            public String getGoodsselect_name() {
                return goodsselect_name;
            }

            public void setGoodsselect_name(String goodsselect_name) {
                this.goodsselect_name = goodsselect_name;
            }

            public int getGoodsselect_num() {
                return goodsselect_num;
            }

            public void setGoodsselect_num(int goodsselect_num) {
                this.goodsselect_num = goodsselect_num;
            }

            public int getGoodsselect_creattime() {
                return goodsselect_creattime;
            }

            public void setGoodsselect_creattime(int goodsselect_creattime) {
                this.goodsselect_creattime = goodsselect_creattime;
            }
        }
    }
}
