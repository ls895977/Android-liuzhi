package com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean;

public class SystemNotiFicationdetailBean {

    /**
     * code : 0
     * msg : 查看成功
     * data : {"systemnotification_id":3,"systemnotification_name":"系统通知2","systemnotification_text":"系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2","user_id":0,"admin_id":1,"systemnotification_creattime":1540990255,"systemnotification_date":"2018-10-31 20:50:55"}
     * error : 0
     */

    private int code;
    private String msg;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * systemnotification_id : 3
         * systemnotification_name : 系统通知2
         * systemnotification_text : 系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2系统通知2
         * user_id : 0
         * admin_id : 1
         * systemnotification_creattime : 1540990255
         * systemnotification_date : 2018-10-31 20:50:55
         */

        private int systemnotification_id;
        private String systemnotification_name;
        private String systemnotification_text;
        private int user_id;
        private int admin_id;
        private int systemnotification_creattime;
        private String systemnotification_date;

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

        public String getSystemnotification_date() {
            return systemnotification_date;
        }

        public void setSystemnotification_date(String systemnotification_date) {
            this.systemnotification_date = systemnotification_date;
        }
    }
}
