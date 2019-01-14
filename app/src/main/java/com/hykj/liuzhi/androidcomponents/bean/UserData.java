package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class UserData {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"user_id":1,"user_barth":"1995-11-12 00:00:00","user_sex":"男","user_label":["拖延症","来自星星的自己","牛逼"],"user_mail":"1516501750@qq.com\n","user_phone":"18840518095","user_autograph":"我是你爸爸啊","user_nickname":"姓王名小贱","user_integral":"2719.8","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_follow":15,"user_fans":23,"user_collection":3}
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
         * user_id : 1
         * user_barth : 1995-11-12 00:00:00
         * user_sex : 男
         * user_label : ["拖延症","来自星星的自己","牛逼"]
         * user_mail : 1516501750@qq.com

         * user_phone : 18840518095
         * user_autograph : 我是你爸爸啊
         * user_nickname : 姓王名小贱
         * user_integral : 2719.8
         * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg
         * user_follow : 15
         * user_fans : 23
         * user_collection : 3
         */

        private int user_id;
        private String user_barth;
        private String user_sex;
        private String user_mail;
        private String user_phone;
        private String user_autograph;
        private String user_nickname;
        private String user_integral;
        private String user_pic;
        private int user_follow;
        private int user_fans;
        private int user_collection;
        private List<String> user_label;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_barth() {
            return user_barth;
        }

        public void setUser_barth(String user_barth) {
            this.user_barth = user_barth;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_mail() {
            return user_mail;
        }

        public void setUser_mail(String user_mail) {
            this.user_mail = user_mail;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_autograph() {
            return user_autograph;
        }

        public void setUser_autograph(String user_autograph) {
            this.user_autograph = user_autograph;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getUser_integral() {
            return user_integral;
        }

        public void setUser_integral(String user_integral) {
            this.user_integral = user_integral;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public int getUser_follow() {
            return user_follow;
        }

        public void setUser_follow(int user_follow) {
            this.user_follow = user_follow;
        }

        public int getUser_fans() {
            return user_fans;
        }

        public void setUser_fans(int user_fans) {
            this.user_fans = user_fans;
        }

        public int getUser_collection() {
            return user_collection;
        }

        public void setUser_collection(int user_collection) {
            this.user_collection = user_collection;
        }

        public List<String> getUser_label() {
            return user_label;
        }

        public void setUser_label(List<String> user_label) {
            this.user_label = user_label;
        }
    }
}
