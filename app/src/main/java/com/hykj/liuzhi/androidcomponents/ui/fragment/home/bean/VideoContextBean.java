package com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean;

import java.util.List;

public class VideoContextBean {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg","softtext_id":1,"user_id":1,"softtextlabel_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"姓王名小贱","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_autograph":"我是你爸爸啊"},"softlabeldata":{"softtextlabel_id":1,"softtextlabel_name":"测试软文标签1","softtextlabel_creattime":1543910995}}],"total":5,"amount":1,"page":"1","number":"10"}
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
         * array : [{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg","softtext_id":1,"user_id":1,"softtextlabel_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_nickname":"姓王名小贱","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_autograph":"我是你爸爸啊"},"softlabeldata":{"softtextlabel_id":1,"softtextlabel_name":"测试软文标签1","softtextlabel_creattime":1543910995}}]
         * total : 5
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
             * video_id : 2
             * video_name : 测试视频
             * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg
             * softtext_id : 1
             * user_id : 1
             * softtextlabel_id : 1
             * softtextimage : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}
             * userdata : {"user_nickname":"姓王名小贱","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_autograph":"我是你爸爸啊"}
             * softlabeldata : {"softtextlabel_id":1,"softtextlabel_name":"测试软文标签1","softtextlabel_creattime":1543910995}
             */

            private int video_id;
            private String video_name;
            private String video_image;
            private int softtext_id;
            private int user_id;
            private int softtextlabel_id;
            private SofttextimageBean softtextimage;
            private UserdataBean userdata;
            private SoftlabeldataBean softlabeldata;

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getVideo_image() {
                return video_image;
            }

            public void setVideo_image(String video_image) {
                this.video_image = video_image;
            }

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

            public int getSofttextlabel_id() {
                return softtextlabel_id;
            }

            public void setSofttextlabel_id(int softtextlabel_id) {
                this.softtextlabel_id = softtextlabel_id;
            }

            public SofttextimageBean getSofttextimage() {
                return softtextimage;
            }

            public void setSofttextimage(SofttextimageBean softtextimage) {
                this.softtextimage = softtextimage;
            }

            public UserdataBean getUserdata() {
                return userdata;
            }

            public void setUserdata(UserdataBean userdata) {
                this.userdata = userdata;
            }

            public SoftlabeldataBean getSoftlabeldata() {
                return softlabeldata;
            }

            public void setSoftlabeldata(SoftlabeldataBean softlabeldata) {
                this.softlabeldata = softlabeldata;
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

            public static class UserdataBean {
                /**
                 * user_nickname : 姓王名小贱
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg
                 * user_autograph : 我是你爸爸啊
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

            public static class SoftlabeldataBean {
                /**
                 * softtextlabel_id : 1
                 * softtextlabel_name : 测试软文标签1
                 * softtextlabel_creattime : 1543910995
                 */

                private int softtextlabel_id;
                private String softtextlabel_name;
                private int softtextlabel_creattime;

                public int getSofttextlabel_id() {
                    return softtextlabel_id;
                }

                public void setSofttextlabel_id(int softtextlabel_id) {
                    this.softtextlabel_id = softtextlabel_id;
                }

                public String getSofttextlabel_name() {
                    return softtextlabel_name;
                }

                public void setSofttextlabel_name(String softtextlabel_name) {
                    this.softtextlabel_name = softtextlabel_name;
                }

                public int getSofttextlabel_creattime() {
                    return softtextlabel_creattime;
                }

                public void setSofttextlabel_creattime(int softtextlabel_creattime) {
                    this.softtextlabel_creattime = softtextlabel_creattime;
                }
            }
        }
    }
}
