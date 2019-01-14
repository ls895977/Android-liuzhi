package com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean;

import java.util.List;

public class FirstpagedataBean {
    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    /**
     * code : 0
     * msg : 访问成功
     * data : {"imagedata":[{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/44c7a34751bf79f2ec6dc76995e77158.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/a2d2bc4e0c9dea41ed82b442ca65e49d.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/0be0b5b450ac142b88762f29d86c9a11.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/151a112b2edff2959b0437e14c071e7a.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/566b10397cac314ebcb2029a39ca1c9c.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/170a2c2314ff7a3e07c978d41a0a1f69.jpg"}],"softtextdata":[{"softtext_id":1,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":2,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":3,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext3/image/427d8a89e6e86012d38b82f58ad93a63.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}}],"videodata":[{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg"},{"video_id":3,"video_name":"测试视频2","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video3/image/2c1f454e391f86ab91c4471d6a5ea99f.jpg"},{"video_id":4,"video_name":"测试视频3","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video4/image/9f243b92edb9ad1eef4fa16b559b8cc6.jpg"}],"advdata":[{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/ed455cfb85485888486ce6843f4b24e3.jpg"},{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/de2437b9e25731389923d2bbf32e3d54.jpg"},{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/48d1d02756cabd281685b7ab9f370f28.jpg"},{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/f270aa6fce6f1313c70bbf97c692b611.jpg"},{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/8670acc237660c7f792222b68b73c0d5.jpg"}]}
     */

    private int error;
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
        private List<ImagedataBean> imagedata;
        private List<SofttextdataBean> softtextdata;
        private List<VideodataBean> videodata;
        private List<AdvdataBean> advdata;

        public List<ImagedataBean> getImagedata() {
            return imagedata;
        }

        public void setImagedata(List<ImagedataBean> imagedata) {
            this.imagedata = imagedata;
        }

        public List<SofttextdataBean> getSofttextdata() {
            return softtextdata;
        }

        public void setSofttextdata(List<SofttextdataBean> softtextdata) {
            this.softtextdata = softtextdata;
        }

        public List<VideodataBean> getVideodata() {
            return videodata;
        }

        public void setVideodata(List<VideodataBean> videodata) {
            this.videodata = videodata;
        }

        public List<AdvdataBean> getAdvdata() {
            return advdata;
        }

        public void setAdvdata(List<AdvdataBean> advdata) {
            this.advdata = advdata;
        }

        public static class ImagedataBean {
            /**
             * sowing_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/44c7a34751bf79f2ec6dc76995e77158.jpg
             */

            private String sowing_url;

            public String getSowing_url() {
                return sowing_url;
            }

            public void setSowing_url(String sowing_url) {
                this.sowing_url = sowing_url;
            }
        }

        public static class SofttextdataBean {
            /**
             * softtext_id : 1
             * softtext_title : 测试标题
             * user_id : 1
             * softtextimage : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}
             * userdata : {"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}
             */

            private int softtext_id;
            private String softtext_title;
            private int user_id;
            private SofttextimageBean softtextimage;
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
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg
                 * user_nickname : 姓王名小贱
                 */

                private String user_pic;
                private String user_nickname;

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
            }
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

        public static class AdvdataBean {
            /**
             * adv_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/ed455cfb85485888486ce6843f4b24e3.jpg
             */

            private String adv_url;

            public String getAdv_url() {
                return adv_url;
            }

            public void setAdv_url(String adv_url) {
                this.adv_url = adv_url;
            }
        }
    }
}
