package com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean;

import java.io.Serializable;
import java.util.List;

public class UserordersBean implements Serializable {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"orders_id":1,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816111248505510","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]},{"orders_id":2,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816162254539851","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]},{"orders_id":3,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816164499975251","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]},{"orders_id":5,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816352398565510","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]}],"total":4,"amount":1,"page":"1","number":"10"}
     */
    private int code;
    private String msg;
    private DataBean data;
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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

    public static class DataBean  implements Serializable{
        /**
         * array : [{"orders_id":1,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816111248505510","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]},{"orders_id":2,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816162254539851","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]},{"orders_id":3,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816164499975251","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]},{"orders_id":5,"orders_paymentstatus":0,"orders_deliverystatus":0,"orders_receivingstate":0,"orders_number":"2018110816352398565510","orders_ordersmoney":"4","ordertype":"待付款","goodsdata":[{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]}]
         * total : 4
         * amount : 1
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private String page;
        private String number;
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

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
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
             * orders_id : 1
             * orders_paymentstatus : 0
             * orders_deliverystatus : 0
             * orders_receivingstate : 0
             * orders_number : 2018110816111248505510
             * orders_ordersmoney : 4
             * ordertype : 待付款
             * goodsdata : [{"goods_id":1,"ordersgoods_num":4,"ordersgoods_money":"4","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c430d66b16716957dd80218fceb7af21.jpg","goods_name":"羊毛毡戳戳乐手工diy新手套装蒸笼小鸡羊毛毡手作材料包"}]
             */

            private int orders_id;
            private int orders_paymentstatus;
            private int orders_deliverystatus;
            private int orders_receivingstate;
            private String orders_number;
            private String orders_ordersmoney;
            private String ordertype;
            private List<GoodsdataBean> goodsdata;

            public int getOrders_id() {
                return orders_id;
            }

            public void setOrders_id(int orders_id) {
                this.orders_id = orders_id;
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

            public String getOrders_number() {
                return orders_number;
            }

            public void setOrders_number(String orders_number) {
                this.orders_number = orders_number;
            }

            public String getOrders_ordersmoney() {
                return orders_ordersmoney;
            }

            public void setOrders_ordersmoney(String orders_ordersmoney) {
                this.orders_ordersmoney = orders_ordersmoney;
            }

            public String getOrdertype() {
                return ordertype;
            }

            public void setOrdertype(String ordertype) {
                this.ordertype = ordertype;
            }

            public List<GoodsdataBean> getGoodsdata() {
                return goodsdata;
            }

            public void setGoodsdata(List<GoodsdataBean> goodsdata) {
                this.goodsdata = goodsdata;
            }

            public static class GoodsdataBean  implements Serializable{
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
}
