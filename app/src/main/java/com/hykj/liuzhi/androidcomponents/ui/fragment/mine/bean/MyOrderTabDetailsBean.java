package com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean;

import java.util.List;

public class MyOrderTabDetailsBean {


    /**
     * code : 0
     * msg : 访问成功
     * data : {"orders_id":5,"address_id":1,"address_user":"都恒辰","address_phone":"18840518095","address_address":"东港市 五四小区 2-206","orders_goodsmoney":"4","deductible_money":"0","orders_ordersmoney":"4","orders_integral":"80","orders_number":"2018110816352398565510","orders_paymentmethod":1,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_creattime":1541666123,"orders_paymenttime":null,"orders_deliverytime":null,"orders_overtime":null,"orders_message":"快点送过来","user_id":1,"goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]}
     */
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    private int code;
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

    public static class DataBean {
        /**
         * orders_id : 5
         * address_id : 1
         * address_user : 都恒辰
         * address_phone : 18840518095
         * address_address : 东港市 五四小区 2-206
         * orders_goodsmoney : 4
         * deductible_money : 0
         * orders_ordersmoney : 4
         * orders_integral : 80
         * orders_number : 2018110816352398565510
         * orders_paymentmethod : 1
         * orders_paymentstatus : 0
         * orders_deliverystatus : 0
         * orders_receivingstate : 0
         * orders_creattime : 1541666123
         * orders_paymenttime : null
         * orders_deliverytime : null
         * orders_overtime : null
         * orders_message : 快点送过来
         * user_id : 1
         * goodsdata : [{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]
         */

        private int orders_id;
        private int address_id;
        private String address_user;
        private String address_phone;
        private String address_address;
        private String orders_goodsmoney;
        private String deductible_money;
        private String orders_ordersmoney;
        private String orders_integral;
        private String orders_number;
        private int orders_paymentmethod;
        private int orders_paymentstatus;
        private int orders_deliverystatus;
        private int orders_receivingstate;
        private int orders_creattime;
        private Object orders_paymenttime;
        private Object orders_deliverytime;
        private Object orders_overtime;
        private String orders_message;
        private int user_id;
        private List<GoodsdataBean> goodsdata;

        public int getOrders_id() {
            return orders_id;
        }

        public void setOrders_id(int orders_id) {
            this.orders_id = orders_id;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getAddress_user() {
            return address_user;
        }

        public void setAddress_user(String address_user) {
            this.address_user = address_user;
        }

        public String getAddress_phone() {
            return address_phone;
        }

        public void setAddress_phone(String address_phone) {
            this.address_phone = address_phone;
        }

        public String getAddress_address() {
            return address_address;
        }

        public void setAddress_address(String address_address) {
            this.address_address = address_address;
        }

        public String getOrders_goodsmoney() {
            return orders_goodsmoney;
        }

        public void setOrders_goodsmoney(String orders_goodsmoney) {
            this.orders_goodsmoney = orders_goodsmoney;
        }

        public String getDeductible_money() {
            return deductible_money;
        }

        public void setDeductible_money(String deductible_money) {
            this.deductible_money = deductible_money;
        }

        public String getOrders_ordersmoney() {
            return orders_ordersmoney;
        }

        public void setOrders_ordersmoney(String orders_ordersmoney) {
            this.orders_ordersmoney = orders_ordersmoney;
        }

        public String getOrders_integral() {
            return orders_integral;
        }

        public void setOrders_integral(String orders_integral) {
            this.orders_integral = orders_integral;
        }

        public String getOrders_number() {
            return orders_number;
        }

        public void setOrders_number(String orders_number) {
            this.orders_number = orders_number;
        }

        public int getOrders_paymentmethod() {
            return orders_paymentmethod;
        }

        public void setOrders_paymentmethod(int orders_paymentmethod) {
            this.orders_paymentmethod = orders_paymentmethod;
        }

        public int getOrders_paymentstatus() {
            return orders_paymentstatus;
        }

        public void setOrders_paymentstatus(int orders_paymentstatus) {
            this.orders_paymentstatus = orders_paymentstatus;
        }

        public int getOrders_deliverystatus() {
            return orders_deliverystatus;
        }

        public void setOrders_deliverystatus(int orders_deliverystatus) {
            this.orders_deliverystatus = orders_deliverystatus;
        }

        public int getOrders_receivingstate() {
            return orders_receivingstate;
        }

        public void setOrders_receivingstate(int orders_receivingstate) {
            this.orders_receivingstate = orders_receivingstate;
        }

        public int getOrders_creattime() {
            return orders_creattime;
        }

        public void setOrders_creattime(int orders_creattime) {
            this.orders_creattime = orders_creattime;
        }

        public Object getOrders_paymenttime() {
            return orders_paymenttime;
        }

        public void setOrders_paymenttime(Object orders_paymenttime) {
            this.orders_paymenttime = orders_paymenttime;
        }

        public Object getOrders_deliverytime() {
            return orders_deliverytime;
        }

        public void setOrders_deliverytime(Object orders_deliverytime) {
            this.orders_deliverytime = orders_deliverytime;
        }

        public Object getOrders_overtime() {
            return orders_overtime;
        }

        public void setOrders_overtime(Object orders_overtime) {
            this.orders_overtime = orders_overtime;
        }

        public String getOrders_message() {
            return orders_message;
        }

        public void setOrders_message(String orders_message) {
            this.orders_message = orders_message;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public List<GoodsdataBean> getGoodsdata() {
            return goodsdata;
        }

        public void setGoodsdata(List<GoodsdataBean> goodsdata) {
            this.goodsdata = goodsdata;
        }

        public static class GoodsdataBean {
            /**
             * goods_id : 1
             * ordersgoods_num : 4
             * ordersgoods_money : 4
             * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg
             * goods_name : 羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包
             */

            private int goods_id;
            private int ordersgoods_num;
            private String ordersgoods_money;
            private String goods_pic;
            private String goods_name;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getOrdersgoods_num() {
                return ordersgoods_num;
            }

            public void setOrdersgoods_num(int ordersgoods_num) {
                this.ordersgoods_num = ordersgoods_num;
            }

            public String getOrdersgoods_money() {
                return ordersgoods_money;
            }

            public void setOrdersgoods_money(String ordersgoods_money) {
                this.ordersgoods_money = ordersgoods_money;
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
        }
    }
}
