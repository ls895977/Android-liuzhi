package com.hykj.liuzhi.androidcomponents.bean;

public class LoginEntity {
    /**
     * userdata : {"user_id":13,"user_name":null,"user_barth":null,"user_sex":1,"user_label":null,"user_mail":null,"user_phone":"123456","user_autograph":null,"user_nickname":null,"user_idcard":null,"user_idcardpos":null,"user_idcardback":null,"user_creattime":null,"user_updatetime":null,"user_integral":null,"user_pic":null,"user_follow":null,"user_fans":null,"user_collection":null,"user_password":"e10adc3949ba59abbe56e057f20f883e","user_idcardpeople":null}
     */
    private int code = 0;

    @Override
    public String toString() {
        return "LoginEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", error=" + error +
                '}';
    }

    private String msg;
    private int error;
    private UserdataBean userdata;

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


    public UserdataBean getUserdata() {
        return userdata;
    }

    public void setUserdata(UserdataBean userdata) {
        this.userdata = userdata;
    }

    public static class UserdataBean {
        /**
         * user_id : 13
         * user_name : null
         * user_barth : null
         * user_sex : 1
         * user_label : null
         * user_mail : null
         * user_phone : 123456
         * user_autograph : null
         * user_nickname : null
         * user_idcard : null
         * user_idcardpos : null
         * user_idcardback : null
         * user_creattime : null
         * user_updatetime : null
         * user_integral : null
         * user_pic : null
         * user_follow : null
         * user_fans : null
         * user_collection : null
         * user_password : e10adc3949ba59abbe56e057f20f883e
         * user_idcardpeople : null
         */

        private int user_id;
        private Object user_name;
        private Object user_barth;
        private int user_sex;
        private Object user_label;
        private Object user_mail;
        private String user_phone;
        private Object user_autograph;
        private Object user_nickname;
        private Object user_idcard;
        private Object user_idcardpos;
        private Object user_idcardback;
        private Object user_creattime;
        private Object user_updatetime;
        private Object user_integral;
        private Object user_pic;
        private Object user_follow;
        private Object user_fans;
        private Object user_collection;
        private String user_password;
        private Object user_idcardpeople;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public Object getUser_name() {
            return user_name;
        }

        public void setUser_name(Object user_name) {
            this.user_name = user_name;
        }

        public Object getUser_barth() {
            return user_barth;
        }

        public void setUser_barth(Object user_barth) {
            this.user_barth = user_barth;
        }

        public int getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(int user_sex) {
            this.user_sex = user_sex;
        }

        public Object getUser_label() {
            return user_label;
        }

        public void setUser_label(Object user_label) {
            this.user_label = user_label;
        }

        public Object getUser_mail() {
            return user_mail;
        }

        public void setUser_mail(Object user_mail) {
            this.user_mail = user_mail;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public Object getUser_autograph() {
            return user_autograph;
        }

        public void setUser_autograph(Object user_autograph) {
            this.user_autograph = user_autograph;
        }

        public Object getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(Object user_nickname) {
            this.user_nickname = user_nickname;
        }

        public Object getUser_idcard() {
            return user_idcard;
        }

        public void setUser_idcard(Object user_idcard) {
            this.user_idcard = user_idcard;
        }

        public Object getUser_idcardpos() {
            return user_idcardpos;
        }

        public void setUser_idcardpos(Object user_idcardpos) {
            this.user_idcardpos = user_idcardpos;
        }

        public Object getUser_idcardback() {
            return user_idcardback;
        }

        public void setUser_idcardback(Object user_idcardback) {
            this.user_idcardback = user_idcardback;
        }

        public Object getUser_creattime() {
            return user_creattime;
        }

        public void setUser_creattime(Object user_creattime) {
            this.user_creattime = user_creattime;
        }

        public Object getUser_updatetime() {
            return user_updatetime;
        }

        public void setUser_updatetime(Object user_updatetime) {
            this.user_updatetime = user_updatetime;
        }

        public Object getUser_integral() {
            return user_integral;
        }

        public void setUser_integral(Object user_integral) {
            this.user_integral = user_integral;
        }

        public Object getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(Object user_pic) {
            this.user_pic = user_pic;
        }

        public Object getUser_follow() {
            return user_follow;
        }

        public void setUser_follow(Object user_follow) {
            this.user_follow = user_follow;
        }

        public Object getUser_fans() {
            return user_fans;
        }

        public void setUser_fans(Object user_fans) {
            this.user_fans = user_fans;
        }

        public Object getUser_collection() {
            return user_collection;
        }

        public void setUser_collection(Object user_collection) {
            this.user_collection = user_collection;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public Object getUser_idcardpeople() {
            return user_idcardpeople;
        }

        public void setUser_idcardpeople(Object user_idcardpeople) {
            this.user_idcardpeople = user_idcardpeople;
        }

        @Override
        public String toString() {
            return "UserdataBean{" +
                    "user_id=" + user_id +
                    ", user_name=" + user_name +
                    ", user_barth=" + user_barth +
                    ", user_sex=" + user_sex +
                    ", user_label=" + user_label +
                    ", user_mail=" + user_mail +
                    ", user_phone='" + user_phone + '\'' +
                    ", user_autograph=" + user_autograph +
                    ", user_nickname=" + user_nickname +
                    ", user_idcard=" + user_idcard +
                    ", user_idcardpos=" + user_idcardpos +
                    ", user_idcardback=" + user_idcardback +
                    ", user_creattime=" + user_creattime +
                    ", user_updatetime=" + user_updatetime +
                    ", user_integral=" + user_integral +
                    ", user_pic=" + user_pic +
                    ", user_follow=" + user_follow +
                    ", user_fans=" + user_fans +
                    ", user_collection=" + user_collection +
                    ", user_password='" + user_password + '\'' +
                    ", user_idcardpeople=" + user_idcardpeople +
                    '}';
        }
    }

}
