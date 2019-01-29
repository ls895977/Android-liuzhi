package com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean;

import java.util.List;

public class NotifyFragmentBean  {

    /**
     * code : 0
     * msg : 访问成功
     * error : 0
     * data : {"array":[{"systemnotification_id":1,"systemnotification_name":"系统通知","systemnotification_text":"系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知","user_id":0,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":2,"systemnotification_name":"软文审核通过","systemnotification_text":"软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过","user_id":1,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":3,"systemnotification_name":"系统通知2","systemnotification_text":"系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2","user_id":0,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":4,"systemnotification_name":"软文审核通过2","systemnotification_text":"软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过","user_id":1,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":8,"systemnotification_name":"实名认证通过","systemnotification_text":"恭喜您，您的实名认证已通过！","user_id":1,"admin_id":1,"systemnotification_creattime":1546754236},{"systemnotification_id":9,"systemnotification_name":"软文审核通过","systemnotification_text":"您发布的软文已通过审核，快去看看吧！","user_id":1,"admin_id":1,"systemnotification_creattime":1546756777}],"total":6,"amount":1,"page":"1","number":"10"}
     */

    private int code;
    private String msg;
    private int error;
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
         * array : [{"systemnotification_id":1,"systemnotification_name":"系统通知","systemnotification_text":"系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知","user_id":0,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":2,"systemnotification_name":"软文审核通过","systemnotification_text":"软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过","user_id":1,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":3,"systemnotification_name":"系统通知2","systemnotification_text":"系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2","user_id":0,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":4,"systemnotification_name":"软文审核通过2","systemnotification_text":"软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过软文审核通过","user_id":1,"admin_id":1,"systemnotification_creattime":1540990255},{"systemnotification_id":8,"systemnotification_name":"实名认证通过","systemnotification_text":"恭喜您，您的实名认证已通过！","user_id":1,"admin_id":1,"systemnotification_creattime":1546754236},{"systemnotification_id":9,"systemnotification_name":"软文审核通过","systemnotification_text":"您发布的软文已通过审核，快去看看吧！","user_id":1,"admin_id":1,"systemnotification_creattime":1546756777}]
         * total : 6
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

        public static class ArrayBean {
            /**
             * systemnotification_id : 1
             * systemnotification_name : 系统通知
             * systemnotification_text : 系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知
             * user_id : 0
             * admin_id : 1
             * systemnotification_creattime : 1540990255
             */

            private int systemnotification_id;
            private String systemnotification_name;
            private String systemnotification_text;
            private int user_id;
            private int admin_id;
            private int systemnotification_creattime;

            public int getSystemnotification_id() {
                return systemnotification_id;
            }

            public void setSystemnotification_id(int systemnotification_id) {
                this.systemnotification_id = systemnotification_id;
            }

            public String getSystemnotification_name() {
                return systemnotification_name;
            }

            public void setSystemnotification_name(String systemnotification_name) {
                this.systemnotification_name = systemnotification_name;
            }

            public String getSystemnotification_text() {
                return systemnotification_text;
            }

            public void setSystemnotification_text(String systemnotification_text) {
                this.systemnotification_text = systemnotification_text;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
                this.admin_id = admin_id;
            }

            public int getSystemnotification_creattime() {
                return systemnotification_creattime;
            }

            public void setSystemnotification_creattime(int systemnotification_creattime) {
                this.systemnotification_creattime = systemnotification_creattime;
            }
        }
    }
}
