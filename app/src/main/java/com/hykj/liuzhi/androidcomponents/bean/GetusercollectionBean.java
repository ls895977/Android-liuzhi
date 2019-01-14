package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class GetusercollectionBean {
    /**
     * code : 0
     * msg : 访问成功
     * data : [{"user_collid":2,"userdata":{"user_nickname":"嘻嘻哈哈哈","user_pic":"/uploads/user1/userpic/2dda24a0a36b45e64719b6f49030a2f8.jpg","user_autograph":"中国人"}}]
     * total : 2
     * amount : 1
     * page : 1
     * number : 10
     */

    private int code;
    private String msg;
    private int total;
    private int amount;
    private int page;
    private int number;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_collid : 2
         * userdata : {"user_nickname":"嘻嘻哈哈哈","user_pic":"/uploads/user1/userpic/2dda24a0a36b45e64719b6f49030a2f8.jpg","user_autograph":"中国人"}
         */

        private int user_collid;
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
             * user_nickname : 嘻嘻哈哈哈
             * user_pic : /uploads/user1/userpic/2dda24a0a36b45e64719b6f49030a2f8.jpg
             * user_autograph : 中国人
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
