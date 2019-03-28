package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class DetailCommetListBean {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"message_id":8,"user_id":1,"message_message":"啥玩意啊，一点也不好看","video_id":2,"softtext_id":0,"imagetext_id":0,"message_creattime":1541649169,"message_reply":0,"message_replyuser":0,"userdata":{"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"哈哈"},"creattime":"11:52:49"},{"message_id":28,"user_id":4,"message_message":"视频评论1119-1","video_id":2,"softtext_id":0,"imagetext_id":0,"message_creattime":1542613721,"message_reply":0,"message_replyuser":0,"userdata":{"user_nickname":"萌萌哒醉了","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_autograph":"有颜值！任性！"},"creattime":"15:48:41"}],"total":2,"amount":1,"page":1,"number":10}
     */
    private int error;
    private int code;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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
         * array : [{"message_id":8,"user_id":1,"message_message":"啥玩意啊，一点也不好看","video_id":2,"softtext_id":0,"imagetext_id":0,"message_creattime":1541649169,"message_reply":0,"message_replyuser":0,"userdata":{"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"哈哈"},"creattime":"11:52:49"},{"message_id":28,"user_id":4,"message_message":"视频评论1119-1","video_id":2,"softtext_id":0,"imagetext_id":0,"message_creattime":1542613721,"message_reply":0,"message_replyuser":0,"userdata":{"user_nickname":"萌萌哒醉了","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_autograph":"有颜值！任性！"},"creattime":"15:48:41"}]
         * total : 2
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
             * message_id : 8
             * user_id : 1
             * message_message : 啥玩意啊，一点也不好看
             * video_id : 2
             * softtext_id : 0
             * imagetext_id : 0
             * message_creattime : 1541649169
             * message_reply : 0
             * message_replyuser : 0
             * userdata :
             * {"user_nickname":"好么",
             * "user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png",
             * "user_autograph":"哈哈"}
             * creattime : 11:52:49
             */

            private int message_id;
            private int user_id;
            private String message_message;
            private int video_id;
            private int softtext_id;
            private int imagetext_id;
            private int message_creattime;
            private int message_reply;
            private int message_replyuser;
            private UserdataBean userdata;
            private String creattime;
            public List<ArrayBean> reply;

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

            public String getMessage_message() {
                return message_message;
            }

            public void setMessage_message(String message_message) {
                this.message_message = message_message;
            }

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
            }

            public int getSofttext_id() {
                return softtext_id;
            }

            public void setSofttext_id(int softtext_id) {
                this.softtext_id = softtext_id;
            }

            public int getImagetext_id() {
                return imagetext_id;
            }

            public void setImagetext_id(int imagetext_id) {
                this.imagetext_id = imagetext_id;
            }

            public int getMessage_creattime() {
                return message_creattime;
            }

            public void setMessage_creattime(int message_creattime) {
                this.message_creattime = message_creattime;
            }

            public int getMessage_reply() {
                return message_reply;
            }

            public void setMessage_reply(int message_reply) {
                this.message_reply = message_reply;
            }

            public int getMessage_replyuser() {
                return message_replyuser;
            }

            public void setMessage_replyuser(int message_replyuser) {
                this.message_replyuser = message_replyuser;
            }

            public UserdataBean getUserdata() {
                return userdata;
            }

            public void setUserdata(UserdataBean userdata) {
                this.userdata = userdata;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
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
        }
    }
}
