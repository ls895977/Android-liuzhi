package com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean;

import java.util.List;

public class WatchHistoryBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"browses_id":1,"user_id":1,"browses_creattime":1546595123,"softtext_id":1,"imagetext_id":0,"softtextdata":{"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}},{"browses_id":2,"user_id":1,"browses_creattime":1546595123,"softtext_id":1,"imagetext_id":0,"softtextdata":{"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}},{"browses_id":3,"user_id":1,"browses_creattime":1546595123,"softtext_id":1,"imagetext_id":0,"softtextdata":{"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}}],"total":3,"amount":1,"page":"1","number":"10"}
     */

    private int code;
    private String msg;
    private DataBean data;
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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
         * array : [{"browses_id":1,"user_id":1,"browses_creattime":1546595123,"softtext_id":1,"imagetext_id":0,"softtextdata":{"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}},{"browses_id":2,"user_id":1,"browses_creattime":1546595123,"softtext_id":1,"imagetext_id":0,"softtextdata":{"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}},{"browses_id":3,"user_id":1,"browses_creattime":1546595123,"softtext_id":1,"imagetext_id":0,"softtextdata":{"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}}]
         * total : 3
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
             * browses_id : 1
             * user_id : 1
             * browses_creattime : 1546595123
             * softtext_id : 1
             * imagetext_id : 0
             * softtextdata : {"softtext_id":1,"user_id":1,"softtextimages":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}}
             */

            private int browses_id;
            private int user_id;
            private int browses_creattime;
            private int softtext_id;
            private int imagetext_id;
            private SofttextdataBean softtextdata;

            public int getBrowses_id() {
                return browses_id;
            }

            public void setBrowses_id(int browses_id) {
                this.browses_id = browses_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getBrowses_creattime() {
                return browses_creattime;
            }

            public void setBrowses_creattime(int browses_creattime) {
                this.browses_creattime = browses_creattime;
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

            public SofttextdataBean getSofttextdata() {
                return softtextdata;
            }

            public void setSofttextdata(SofttextdataBean softtextdata) {
                this.softtextdata = softtextdata;
            }

            public static class SofttextdataBean {
                /**
                 * softtext_id : 1
                 * user_id : 1
                 * softtextimages : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}
                 * userdata : {"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg","user_autograph":"hahaha"}
                 */

                private int softtext_id;
                private int user_id;
                private SofttextimagesBean softtextimages;
                private UserdataBean userdata;

                public int getSofttext_id() {
                    return softtext_id;
                }

                public void setSofttext_id(int softtext_id) {
                    this.softtext_id = softtext_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public SofttextimagesBean getSofttextimages() {
                    return softtextimages;
                }

                public void setSofttextimages(SofttextimagesBean softtextimages) {
                    this.softtextimages = softtextimages;
                }

                public UserdataBean getUserdata() {
                    return userdata;
                }

                public void setUserdata(UserdataBean userdata) {
                    this.userdata = userdata;
                }

                public static class SofttextimagesBean {
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

                public static class UserdataBean {
                    /**
                     * user_nickname : 闹闹
                     * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/5ca4e64b5dfcd9e15b1e7c4655c002bf.jpeg
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
}
