package com.hykj.liuzhi.androidcomponents.ui.activity.min.bean;

import java.util.List;

public class LoGinRecordBean {

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    /**
     * code : 0
     * msg : 访问成功！！
     * data : {"array":[{"registry_logintime":"2018年10月15日20:05","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月15日21:16","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月16日13:52","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月17日09:59","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月18日19:31","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月19日18:06","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月22日15:22","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月23日16:39","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月26日17:49","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月30日11:57","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"}],"total":411,"amount":42,"page":1,"number":10}
     */

    private int error;
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
         * array : [{"registry_logintime":"2018年10月15日20:05","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月15日21:16","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月16日13:52","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月17日09:59","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月18日19:31","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月19日18:06","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月22日15:22","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月23日16:39","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月26日17:49","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"},{"registry_logintime":"2018年10月30日11:57","registry_loginlong":"13382905.2","registry_loginlat":"3515188.13","registry_address":"浙江省杭州市"}]
         * total : 411
         * amount : 42
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
             * registry_logintime : 2018年10月15日20:05
             * registry_loginlong : 13382905.2
             * registry_loginlat : 3515188.13
             * registry_address : 浙江省杭州市
             */

            private String registry_logintime;
            private String registry_loginlong;
            private String registry_loginlat;
            private String registry_address;

            public String getRegistry_logintime() {
                return registry_logintime;
            }

            public void setRegistry_logintime(String registry_logintime) {
                this.registry_logintime = registry_logintime;
            }

            public String getRegistry_loginlong() {
                return registry_loginlong;
            }

            public void setRegistry_loginlong(String registry_loginlong) {
                this.registry_loginlong = registry_loginlong;
            }

            public String getRegistry_loginlat() {
                return registry_loginlat;
            }

            public void setRegistry_loginlat(String registry_loginlat) {
                this.registry_loginlat = registry_loginlat;
            }

            public String getRegistry_address() {
                return registry_address;
            }

            public void setRegistry_address(String registry_address) {
                this.registry_address = registry_address;
            }
        }
    }
}
