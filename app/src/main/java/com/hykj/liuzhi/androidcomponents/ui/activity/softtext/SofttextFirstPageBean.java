package com.hykj.liuzhi.androidcomponents.ui.activity.softtext;

import java.util.List;

public class SofttextFirstPageBean {


    /**
     * code : 0
     * msg : 访问成功
     * data : {"softtext_title":"测试标题","softtext_creattime":1541498265,"user_id":1,"softtext_text":"测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题","softtext_id":1,"softtext_point":5,"softtext_collection":2,"userfans":0,"userpoint":0,"usercollection":0,"date":"2018-11-06","userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_nickname":"好么","user_autograph":"哈哈"},"softtextimage":[{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/438c10d1ce32feb5db49197407904057.jpg"},{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/84d74fdb3eb803430dc4f0120052c9a4.jpg"}]}
     */
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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
         * softtext_title : 测试标题
         * softtext_creattime : 1541498265
         * user_id : 1
         * softtext_text : 测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题
         * softtext_id : 1
         * softtext_point : 5
         * softtext_collection : 2
         * userfans : 0
         * userpoint : 0
         * usercollection : 0
         * date : 2018-11-06
         * userdata : {"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_nickname":"好么","user_autograph":"哈哈"}
         * softtextimage : [{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/438c10d1ce32feb5db49197407904057.jpg"},{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/84d74fdb3eb803430dc4f0120052c9a4.jpg"}]
         */

        private String softtext_title;
        private int softtext_creattime;
        private int user_id;
        private String softtext_text;
        private int softtext_id;
        private int softtext_point;
        private int softtext_collection;
        private int userfans;
        private int userpoint;
        private int usercollection;
        private String date;
        private UserdataBean userdata;
        private List<SofttextimageBean> softtextimage;

        public String getSofttext_title() {
            return softtext_title;
        }

        public void setSofttext_title(String softtext_title) {
            this.softtext_title = softtext_title;
        }

        public int getSofttext_creattime() {
            return softtext_creattime;
        }

        public void setSofttext_creattime(int softtext_creattime) {
            this.softtext_creattime = softtext_creattime;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getSofttext_text() {
            return softtext_text;
        }

        public void setSofttext_text(String softtext_text) {
            this.softtext_text = softtext_text;
        }

        public int getSofttext_id() {
            return softtext_id;
        }

        public void setSofttext_id(int softtext_id) {
            this.softtext_id = softtext_id;
        }

        public int getSofttext_point() {
            return softtext_point;
        }

        public void setSofttext_point(int softtext_point) {
            this.softtext_point = softtext_point;
        }

        public int getSofttext_collection() {
            return softtext_collection;
        }

        public void setSofttext_collection(int softtext_collection) {
            this.softtext_collection = softtext_collection;
        }

        public int getUserfans() {
            return userfans;
        }

        public void setUserfans(int userfans) {
            this.userfans = userfans;
        }

        public int getUserpoint() {
            return userpoint;
        }

        public void setUserpoint(int userpoint) {
            this.userpoint = userpoint;
        }

        public int getUsercollection() {
            return usercollection;
        }

        public void setUsercollection(int usercollection) {
            this.usercollection = usercollection;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public UserdataBean getUserdata() {
            return userdata;
        }

        public void setUserdata(UserdataBean userdata) {
            this.userdata = userdata;
        }

        public List<SofttextimageBean> getSofttextimage() {
            return softtextimage;
        }

        public void setSofttextimage(List<SofttextimageBean> softtextimage) {
            this.softtextimage = softtextimage;
        }

        public static class UserdataBean {
            /**
             * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png
             * user_nickname : 好么
             * user_autograph : 哈哈
             */

            private String user_pic;
            private String user_nickname;
            private String user_autograph;

            public String getUser_pic() {
                return user_pic;
            }

            public void setUser_pic(String user_pic) {
                this.user_pic = user_pic;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public String getUser_autograph() {
                return user_autograph;
            }

            public void setUser_autograph(String user_autograph) {
                this.user_autograph = user_autograph;
            }
        }

        public static class SofttextimageBean {
            /**
             * softtextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg
             */

            private String softtextimage_url;

            public String getSofttextimage_url() {
                return softtextimage_url;
            }

            public void setSofttextimage_url(String softtextimage_url) {
                this.softtextimage_url = softtextimage_url;
            }
        }
    }
}
