package com.hykj.liuzhi.androidcomponents.bean;

public class PersonDetailBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"user_id":1,"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha","user_follow":16,"user_fans":23,"userfans":1}
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
         * user_nickname : 闹闹
         * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg
         * user_autograph : hahaha
         * user_follow : 16
         * user_fans : 23
         * userfans : 1
         */

        private int user_id;
        private String user_nickname;
        private String user_pic;
        private String user_autograph;
        private int user_follow;
        private int user_fans;
        private int userfans;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public String getUser_autograph() {
            return user_autograph;
        }

        public void setUser_autograph(String user_autograph) {
            this.user_autograph = user_autograph;
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

        public int getUserfans() {
            return userfans;
        }

        public void setUserfans(int userfans) {
            this.userfans = userfans;
        }
    }
}
