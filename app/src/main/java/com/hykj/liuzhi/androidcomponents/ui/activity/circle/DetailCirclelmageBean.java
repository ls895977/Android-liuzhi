package com.hykj.liuzhi.androidcomponents.ui.activity.circle;

import java.util.List;

public class DetailCirclelmageBean {


    /**
     * code : 0
     * msg : 访问成功
     * error : 0
     * data : {"imagetext_id":1,"imagetext_text":"测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题","user_id":1,"imagetext_creattime":1541507942,"imagetextlabel_id":1,"imagetext_point":0,"imagetext_collection":1,"userfans":0,"imagetextpic":[{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"},{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/af5b155be3a3858d26933a093ae46cf4.jpg"},{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/68ef1a57b591ed272ce65d9de4ebb6be.jpg"}],"usercollection":0,"userpoint":0,"userdata":{"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"哈哈"},"creatdate":"2018-11-06 20:39:02","imagetextlabel":{"imagetextlabel_id":1,"imagetextlabel_name":"标签1","imagetextlabel_creattime":1539854634}}
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
         * imagetext_id : 1
         * imagetext_text : 测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题测试标题
         * user_id : 1
         * imagetext_creattime : 1541507942
         * imagetextlabel_id : 1
         * imagetext_point : 0
         * imagetext_collection : 1
         * userfans : 0
         * imagetextpic : [{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"},{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/af5b155be3a3858d26933a093ae46cf4.jpg"},{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/68ef1a57b591ed272ce65d9de4ebb6be.jpg"}]
         * usercollection : 0
         * userpoint : 0
         * userdata : {"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"哈哈"}
         * creatdate : 2018-11-06 20:39:02
         * imagetextlabel : {"imagetextlabel_id":1,"imagetextlabel_name":"标签1","imagetextlabel_creattime":1539854634}
         */

        private int imagetext_id;
        private String imagetext_text;
        private int user_id;
        private int imagetext_creattime;
        private int imagetextlabel_id;
        private int imagetext_point;
        private int imagetext_collection;
        private int userfans;
        private int usercollection;
        private int userpoint;
        private UserdataBean userdata;
        private String creatdate;
        private ImagetextlabelBean imagetextlabel;
        private List<ImagetextpicBean> imagetextpic;

        public int getImagetext_id() {
            return imagetext_id;
        }

        public void setImagetext_id(int imagetext_id) {
            this.imagetext_id = imagetext_id;
        }

        public String getImagetext_text() {
            return imagetext_text;
        }

        public void setImagetext_text(String imagetext_text) {
            this.imagetext_text = imagetext_text;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getImagetext_creattime() {
            return imagetext_creattime;
        }

        public void setImagetext_creattime(int imagetext_creattime) {
            this.imagetext_creattime = imagetext_creattime;
        }

        public int getImagetextlabel_id() {
            return imagetextlabel_id;
        }

        public void setImagetextlabel_id(int imagetextlabel_id) {
            this.imagetextlabel_id = imagetextlabel_id;
        }

        public int getImagetext_point() {
            return imagetext_point;
        }

        public void setImagetext_point(int imagetext_point) {
            this.imagetext_point = imagetext_point;
        }

        public int getImagetext_collection() {
            return imagetext_collection;
        }

        public void setImagetext_collection(int imagetext_collection) {
            this.imagetext_collection = imagetext_collection;
        }

        public int getUserfans() {
            return userfans;
        }

        public void setUserfans(int userfans) {
            this.userfans = userfans;
        }

        public int getUsercollection() {
            return usercollection;
        }

        public void setUsercollection(int usercollection) {
            this.usercollection = usercollection;
        }

        public int getUserpoint() {
            return userpoint;
        }

        public void setUserpoint(int userpoint) {
            this.userpoint = userpoint;
        }

        public UserdataBean getUserdata() {
            return userdata;
        }

        public void setUserdata(UserdataBean userdata) {
            this.userdata = userdata;
        }

        public String getCreatdate() {
            return creatdate;
        }

        public void setCreatdate(String creatdate) {
            this.creatdate = creatdate;
        }

        public ImagetextlabelBean getImagetextlabel() {
            return imagetextlabel;
        }

        public void setImagetextlabel(ImagetextlabelBean imagetextlabel) {
            this.imagetextlabel = imagetextlabel;
        }

        public List<ImagetextpicBean> getImagetextpic() {
            return imagetextpic;
        }

        public void setImagetextpic(List<ImagetextpicBean> imagetextpic) {
            this.imagetextpic = imagetextpic;
        }

        public static class UserdataBean {
            /**
             * user_nickname : 好么
             * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png
             * user_autograph : 哈哈
             */

            private String user_nickname;
            private String user_pic;
            private String user_autograph;

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
        }

        public static class ImagetextlabelBean {
            /**
             * imagetextlabel_id : 1
             * imagetextlabel_name : 标签1
             * imagetextlabel_creattime : 1539854634
             */

            private int imagetextlabel_id;
            private String imagetextlabel_name;
            private int imagetextlabel_creattime;

            public int getImagetextlabel_id() {
                return imagetextlabel_id;
            }

            public void setImagetextlabel_id(int imagetextlabel_id) {
                this.imagetextlabel_id = imagetextlabel_id;
            }

            public String getImagetextlabel_name() {
                return imagetextlabel_name;
            }

            public void setImagetextlabel_name(String imagetextlabel_name) {
                this.imagetextlabel_name = imagetextlabel_name;
            }

            public int getImagetextlabel_creattime() {
                return imagetextlabel_creattime;
            }

            public void setImagetextlabel_creattime(int imagetextlabel_creattime) {
                this.imagetextlabel_creattime = imagetextlabel_creattime;
            }
        }

        public static class ImagetextpicBean {
            /**
             * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg
             */

            private String imagetextimage_url;

            public String getImagetextimage_url() {
                return imagetextimage_url;
            }

            public void setImagetextimage_url(String imagetextimage_url) {
                this.imagetextimage_url = imagetextimage_url;
            }
        }
    }
}
