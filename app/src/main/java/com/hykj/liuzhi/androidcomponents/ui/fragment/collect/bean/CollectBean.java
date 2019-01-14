package com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean;

import java.util.List;

public class CollectBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"user_id":1,"softtext_id":1,"video_id":2,"collection_type":1,"videodata":{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg"},"authordata":{"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"小"},"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}}],"total":1,"amount":1,"page":1,"number":"10"}
     */
    private int error;
    private int code;
    private String msg;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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
         * array : [{"user_id":1,"softtext_id":1,"video_id":2,"collection_type":1,"videodata":{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg"},"authordata":{"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"小"},"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}}]
         * total : 1
         * amount : 1
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private int page;
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

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
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
             * user_id : 1
             * softtext_id : 1
             * video_id : 2
             * collection_type : 1
             * videodata : {"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg"}
             * authordata : {"user_nickname":"好么","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_autograph":"小"}
             * softtextimage : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}
             */

            private int user_id;
            private int softtext_id;
            private int video_id;
            private int collection_type;
            private VideodataBean videodata;
            private AuthordataBean authordata;
            private SofttextimageBean softtextimage;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getSofttext_id() {
                return softtext_id;
            }

            public void setSofttext_id(int softtext_id) {
                this.softtext_id = softtext_id;
            }

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
            }

            public int getCollection_type() {
                return collection_type;
            }

            public void setCollection_type(int collection_type) {
                this.collection_type = collection_type;
            }

            public VideodataBean getVideodata() {
                return videodata;
            }

            public void setVideodata(VideodataBean videodata) {
                this.videodata = videodata;
            }

            public AuthordataBean getAuthordata() {
                return authordata;
            }

            public void setAuthordata(AuthordataBean authordata) {
                this.authordata = authordata;
            }

            public SofttextimageBean getSofttextimage() {
                return softtextimage;
            }

            public void setSofttextimage(SofttextimageBean softtextimage) {
                this.softtextimage = softtextimage;
            }

            public static class VideodataBean {
                /**
                 * video_id : 2
                 * video_name : 测试视频
                 * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg
                 */

                private int video_id;
                private String video_name;
                private String video_image;

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
            }

            public static class AuthordataBean {
                /**
                 * user_nickname : 好么
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png
                 * user_autograph : 小
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
}
