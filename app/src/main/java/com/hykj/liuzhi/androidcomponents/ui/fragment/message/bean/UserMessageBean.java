package com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean;

import java.util.List;

public class UserMessageBean {

    /**
     * code : 0
     * msg : 访问成功
     * error : 0
     * data : {"array":[{"softtext_id":1,"message_id":101,"user_id":1,"message_creattime":1547029303,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-09 18:21:43"},{"softtext_id":1,"message_id":82,"user_id":16,"message_creattime":1546673132,"message":"评论了你的文章","userdata":{"user_nickname":null,"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user16/userpic/492eee2c41963635429f41a6d90bb8c9.png","user_autograph":null},"date":"2019-01-05 15:25:32"},{"softtext_id":1,"message_id":80,"user_id":16,"message_creattime":1546673088,"message":"评论了你的文章","userdata":{"user_nickname":null,"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user16/userpic/492eee2c41963635429f41a6d90bb8c9.png","user_autograph":null},"date":"2019-01-05 15:24:48"},{"softtext_id":1,"message_id":76,"user_id":1,"message_creattime":1546417283,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 16:21:23"},{"softtext_id":1,"message_id":75,"user_id":1,"message_creattime":1546417246,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 16:20:46"},{"softtext_id":1,"message_id":74,"user_id":1,"message_creattime":1546416172,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 16:02:52"},{"softtext_id":1,"message_id":73,"user_id":1,"message_creattime":1546415929,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 15:58:49"},{"softtext_id":1,"message_id":72,"user_id":1,"message_creattime":1546415877,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 15:57:57"},{"softtext_id":1,"message_id":71,"user_id":1,"message_creattime":1546415858,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 15:57:38"},{"softtext_id":1,"message_id":25,"user_id":4,"message_creattime":1542597957,"message":"评论了你的文章","userdata":{"user_nickname":"萌萌哒醉了^_^","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/1bc5bb31302da9be544373e15985ef8c.png","user_autograph":"有颜值！任性！(^^)"},"date":"2018-11-19 11:25:57"}],"total":31,"amount":4,"page":"1","number":"10"}
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
         * array : [{"softtext_id":1,"message_id":101,"user_id":1,"message_creattime":1547029303,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-09 18:21:43"},{"softtext_id":1,"message_id":82,"user_id":16,"message_creattime":1546673132,"message":"评论了你的文章","userdata":{"user_nickname":null,"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user16/userpic/492eee2c41963635429f41a6d90bb8c9.png","user_autograph":null},"date":"2019-01-05 15:25:32"},{"softtext_id":1,"message_id":80,"user_id":16,"message_creattime":1546673088,"message":"评论了你的文章","userdata":{"user_nickname":null,"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user16/userpic/492eee2c41963635429f41a6d90bb8c9.png","user_autograph":null},"date":"2019-01-05 15:24:48"},{"softtext_id":1,"message_id":76,"user_id":1,"message_creattime":1546417283,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 16:21:23"},{"softtext_id":1,"message_id":75,"user_id":1,"message_creattime":1546417246,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 16:20:46"},{"softtext_id":1,"message_id":74,"user_id":1,"message_creattime":1546416172,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 16:02:52"},{"softtext_id":1,"message_id":73,"user_id":1,"message_creattime":1546415929,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 15:58:49"},{"softtext_id":1,"message_id":72,"user_id":1,"message_creattime":1546415877,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 15:57:57"},{"softtext_id":1,"message_id":71,"user_id":1,"message_creattime":1546415858,"message":"评论了你的文章","userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"date":"2019-01-02 15:57:38"},{"softtext_id":1,"message_id":25,"user_id":4,"message_creattime":1542597957,"message":"评论了你的文章","userdata":{"user_nickname":"萌萌哒醉了^_^","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/1bc5bb31302da9be544373e15985ef8c.png","user_autograph":"有颜值！任性！(^^)"},"date":"2018-11-19 11:25:57"}]
         * total : 31
         * amount : 4
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
             * softtext_id : 1
             * message_id : 101
             * user_id : 1
             * message_creattime : 1547029303
             * message : 评论了你的文章
             * userdata : {"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"}
             * date : 2019-01-09 18:21:43
             */

            private int softtext_id;
            private int message_id;
            private int user_id;
            private int message_creattime;
            private String message;
            private UserdataBean userdata;
            private String date;

            public int getSofttext_id() {
                return softtext_id;
            }

            public void setSofttext_id(int softtext_id) {
                this.softtext_id = softtext_id;
            }

            public int getMessage_id() {
                return message_id;
            }

            public void setMessage_id(int message_id) {
                this.message_id = message_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getMessage_creattime() {
                return message_creattime;
            }

            public void setMessage_creattime(int message_creattime) {
                this.message_creattime = message_creattime;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public UserdataBean getUserdata() {
                return userdata;
            }

            public void setUserdata(UserdataBean userdata) {
                this.userdata = userdata;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class UserdataBean {
                /**
                 * user_nickname : 闹闹
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg
                 * user_autograph : hahaha
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
        }
    }
}
