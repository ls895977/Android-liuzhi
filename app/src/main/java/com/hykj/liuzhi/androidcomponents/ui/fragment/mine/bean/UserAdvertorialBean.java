package com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean;

import java.util.List;

public class UserAdvertorialBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"softtext_id":12,"softtext_title":"熊猫要钱","user_id":4,"softtextimagedata":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext12/image/f72a01ed0a3b5248557a416c0d45f936.png"},"userdata":{"user_nickname":"萌萌哒醉了^_^","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/1bc5bb31302da9be544373e15985ef8c.png","user_autograph":"有颜值！任性！(^^)"}}],"total":2,"amount":1,"page":1,"number":10}
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
         * array : [{"softtext_id":12,"softtext_title":"熊猫要钱","user_id":4,"softtextimagedata":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext12/image/f72a01ed0a3b5248557a416c0d45f936.png"},"userdata":{"user_nickname":"萌萌哒醉了^_^","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/1bc5bb31302da9be544373e15985ef8c.png","user_autograph":"有颜值！任性！(^^)"}}]
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
             * softtext_id : 12
             * softtext_title : 熊猫要钱
             * user_id : 4
             * softtextimagedata : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext12/image/f72a01ed0a3b5248557a416c0d45f936.png"}
             * userdata : {"user_nickname":"萌萌哒醉了^_^","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/1bc5bb31302da9be544373e15985ef8c.png","user_autograph":"有颜值！任性！(^^)"}
             */

            private int softtext_id;
            private String softtext_title;
            private int user_id;
            private SofttextimagedataBean softtextimagedata;
            private UserdataBean userdata;

            public int getSofttext_id() {
                return softtext_id;
            }

            public void setSofttext_id(int softtext_id) {
                this.softtext_id = softtext_id;
            }

            public String getSofttext_title() {
                return softtext_title;
            }

            public void setSofttext_title(String softtext_title) {
                this.softtext_title = softtext_title;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public SofttextimagedataBean getSofttextimagedata() {
                return softtextimagedata;
            }

            public void setSofttextimagedata(SofttextimagedataBean softtextimagedata) {
                this.softtextimagedata = softtextimagedata;
            }

            public UserdataBean getUserdata() {
                return userdata;
            }

            public void setUserdata(UserdataBean userdata) {
                this.userdata = userdata;
            }

            public static class SofttextimagedataBean {
                /**
                 * softtextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext12/image/f72a01ed0a3b5248557a416c0d45f936.png
                 */

                private String softtextimage_url;

                public String getSofttextimage_url() {
                    return softtextimage_url;
                }

                public void setSofttextimage_url(String softtextimage_url) {
                    this.softtextimage_url = softtextimage_url;
                }
            }

            public static class UserdataBean {
                /**
                 * user_nickname : 萌萌哒醉了^_^
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/1bc5bb31302da9be544373e15985ef8c.png
                 * user_autograph : 有颜值！任性！(^^)
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
