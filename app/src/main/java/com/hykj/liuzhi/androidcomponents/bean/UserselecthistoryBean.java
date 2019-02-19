package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class UserselecthistoryBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"selecthistory_name":"萌"},{"selecthistory_name":"测试"},{"selecthistory_name":"测"},{"selecthistory_name":"巴黎"},{"selecthistory_name":"熊猫要钱"},{"selecthistory_name":"视频"},{"selecthistory_name":"测试视"},{"selecthistory_name":"测试视频"},{"selecthistory_name":"萌妹子"},{"selecthistory_name":"视"}],"total":10,"amount":1,"page":1,"number":10}
     */

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
         * array : [{"selecthistory_name":"萌"},{"selecthistory_name":"测试"},{"selecthistory_name":"测"},{"selecthistory_name":"巴黎"},{"selecthistory_name":"熊猫要钱"},{"selecthistory_name":"视频"},{"selecthistory_name":"测试视"},{"selecthistory_name":"测试视频"},{"selecthistory_name":"萌妹子"},{"selecthistory_name":"视"}]
         * total : 10
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
             * selecthistory_name : 萌
             */

            private String selecthistory_name;

            public String getSelecthistory_name() {
                return selecthistory_name;
            }

            public void setSelecthistory_name(String selecthistory_name) {
                this.selecthistory_name = selecthistory_name;
            }
        }
    }
}
