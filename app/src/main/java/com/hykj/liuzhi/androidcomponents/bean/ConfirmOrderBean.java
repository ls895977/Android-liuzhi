package com.hykj.liuzhi.androidcomponents.bean;

public class ConfirmOrderBean {

    /**
     * code : 0
     * msg : 对不起，您的订单金额太少，无法进行抵扣操作。
     * error : 1
     */

    private int code;
    private String msg;
    private int error;
    /**
     * data : {"orders_id":"72","orders_paymentmethod":"1"}
     */

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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orders_id : 72
         * orders_paymentmethod : 1
         */

        private String orders_id;
        private String orders_paymentmethod;

        public String getOrders_id() {
            return orders_id;
        }

        public void setOrders_id(String orders_id) {
            this.orders_id = orders_id;
        }

        public String getOrders_paymentmethod() {
            return orders_paymentmethod;
        }

        public void setOrders_paymentmethod(String orders_paymentmethod) {
            this.orders_paymentmethod = orders_paymentmethod;
        }
    }
}
