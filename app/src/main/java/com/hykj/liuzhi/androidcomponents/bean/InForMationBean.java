package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class InForMationBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"orders_id":26,"orders_number":"2019012314145310051101","orders_deliverytime":1548224118,"showmessage":"您的订单2019012314145310051101已经发货，请查看","title":"您的订单已经发货"},{"orders_id":27,"orders_number":"2019012314184149504848","orders_deliverytime":1548224118,"showmessage":"您的订单2019012314184149504848已经发货，请查看","title":"您的订单已经发货"},{"orders_id":28,"orders_number":"2019012314275598101101","orders_deliverytime":1548224118,"showmessage":"您的订单2019012314275598101101已经发货，请查看","title":"您的订单已经发货"},{"orders_id":36,"orders_number":"2019012516270798485450","orders_deliverytime":1548571677,"showmessage":"您的订单2019012516270798485450已经发货，请查看","title":"您的订单已经发货"}],"total":4,"amount":1,"page":1,"number":10}
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
         * array : [{"orders_id":26,"orders_number":"2019012314145310051101","orders_deliverytime":1548224118,"showmessage":"您的订单2019012314145310051101已经发货，请查看","title":"您的订单已经发货"},{"orders_id":27,"orders_number":"2019012314184149504848","orders_deliverytime":1548224118,"showmessage":"您的订单2019012314184149504848已经发货，请查看","title":"您的订单已经发货"},{"orders_id":28,"orders_number":"2019012314275598101101","orders_deliverytime":1548224118,"showmessage":"您的订单2019012314275598101101已经发货，请查看","title":"您的订单已经发货"},{"orders_id":36,"orders_number":"2019012516270798485450","orders_deliverytime":1548571677,"showmessage":"您的订单2019012516270798485450已经发货，请查看","title":"您的订单已经发货"}]
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

        public static class ArrayBean {
            /**
             * orders_id : 26
             * orders_number : 2019012314145310051101
             * orders_deliverytime : 1548224118
             * showmessage : 您的订单2019012314145310051101已经发货，请查看
             * title : 您的订单已经发货
             */

            private int orders_id;
            private String orders_number;
            private int orders_deliverytime;
            private String showmessage;
            private String title;

            public int getOrders_id() {
                return orders_id;
            }

            public void setOrders_id(int orders_id) {
                this.orders_id = orders_id;
            }

            public String getOrders_number() {
                return orders_number;
            }

            public void setOrders_number(String orders_number) {
                this.orders_number = orders_number;
            }

            public int getOrders_deliverytime() {
                return orders_deliverytime;
            }

            public void setOrders_deliverytime(int orders_deliverytime) {
                this.orders_deliverytime = orders_deliverytime;
            }

            public String getShowmessage() {
                return showmessage;
            }

            public void setShowmessage(String showmessage) {
                this.showmessage = showmessage;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
