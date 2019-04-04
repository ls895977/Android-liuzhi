package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

/**
 * name qyl
 * created $date$ $time$
 * use:用户已关注的人
 */
public class UserAttentionBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"user_collid":4,"userdata":{"user_nickname":"萌萌哒醉了","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_autograph":"有颜值！任性！"}},{"user_collid":1,"userdata":{"user_nickname":"姓王名小贱","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_autograph":"我是你爸爸啊"}},{"user_collid":12,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":11,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":10,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":9,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":8,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}}],"total":7,"amount":1,"page":"1","number":"10"}
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
         * array : [{"user_collid":4,"userdata":{"user_nickname":"萌萌哒醉了","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_autograph":"有颜值！任性！"}},{"user_collid":1,"userdata":{"user_nickname":"姓王名小贱","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_autograph":"我是你爸爸啊"}},{"user_collid":12,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":11,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":10,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":9,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}},{"user_collid":8,"userdata":{"user_nickname":null,"user_pic":null,"user_autograph":null}}]
         * total : 7
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
             * user_collid : 4
             * userdata : {"user_nickname":"萌萌哒醉了","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_autograph":"有颜值！任性！"}
             */
            public int user_id;
            private int user_collid;

            public String getUserfans() {
                return Userfans;
            }

            public void setUserfans(String userfans) {
                Userfans = userfans;
            }

            private String Userfans;
            private UserdataBean userdata;

            public int getUser_collid() {
                return user_collid;
            }

            public void setUser_collid(int user_collid) {
                this.user_collid = user_collid;
            }

            public UserdataBean getUserdata() {
                return userdata;
            }

            public void setUserdata(UserdataBean userdata) {
                this.userdata = userdata;
            }

            public static class UserdataBean {
                /**
                 * user_nickname : 萌萌哒醉了
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png
                 * user_autograph : 有颜值！任性！
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
