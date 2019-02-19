package com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean;

import java.util.List;

public class CollectBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"user_id":1,"imagetext_id":2,"goods_id":2,"video_id":4,"softtext_id":2,"collection_type":1,"authordata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg"},"imagetextimage":{"imagetext_id":2,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"},"videodata":{"video_id":4,"video_name":"少女峰火车沿途","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/7d6f9764ab5027260204c561fbf3bed3.jpg"},"goodsdata":{"goods_id":2,"goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/405bf41a7b33f5dffb658ba4c701f574.jpg","goods_name":"定制真丝绡挂帘手工蓝染艺术空间装置植物染草木染山峦渐变窗帘子","goods_price":"22"}},{"user_id":1,"softtext_id":1,"collection_type":1,"authordata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext12/image/f72a01ed0a3b5248557a416c0d45f936.png"}}],"total":2,"amount":1,"page":1,"number":10}
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
         * array : [{"user_id":1,"imagetext_id":2,"goods_id":2,"video_id":4,"softtext_id":2,"collection_type":1,"authordata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg"},"imagetextimage":{"imagetext_id":2,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"},"videodata":{"video_id":4,"video_name":"少女峰火车沿途","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/7d6f9764ab5027260204c561fbf3bed3.jpg"},"goodsdata":{"goods_id":2,"goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/405bf41a7b33f5dffb658ba4c701f574.jpg","goods_name":"定制真丝绡挂帘手工蓝染艺术空间装置植物染草木染山峦渐变窗帘子","goods_price":"22"}},{"user_id":1,"softtext_id":1,"collection_type":1,"authordata":{"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"},"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext12/image/f72a01ed0a3b5248557a416c0d45f936.png"}}]
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
             * user_id : 1
             * imagetext_id : 2
             * goods_id : 2
             * video_id : 4
             * softtext_id : 2
             * collection_type : 1
             * authordata : {"user_nickname":"闹闹","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/fd3d36b807b5c2e27cfce86573edde34.jpeg","user_autograph":"hahaha"}
             * softtextimage : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg"}
             * imagetextimage : {"imagetext_id":2,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"}
             * videodata : {"video_id":4,"video_name":"少女峰火车沿途","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/7d6f9764ab5027260204c561fbf3bed3.jpg"}
             * goodsdata : {"goods_id":2,"goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/405bf41a7b33f5dffb658ba4c701f574.jpg","goods_name":"定制真丝绡挂帘手工蓝染艺术空间装置植物染草木染山峦渐变窗帘子","goods_price":"22"}
             */

            private int user_id;
            private int imagetext_id;
            private int goods_id;
            private int video_id;
            private int softtext_id;
            private int collection_type;
            private AuthordataBean authordata;
            private SofttextimageBean softtextimage;
            private ImagetextimageBean imagetextimage;
            private VideodataBean videodata;
            private GoodsdataBean goodsdata;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getImagetext_id() {
                return imagetext_id;
            }

            public void setImagetext_id(int imagetext_id) {
                this.imagetext_id = imagetext_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public int getCollection_type() {
                return collection_type;
            }

            public void setCollection_type(int collection_type) {
                this.collection_type = collection_type;
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

            public ImagetextimageBean getImagetextimage() {
                return imagetextimage;
            }

            public void setImagetextimage(ImagetextimageBean imagetextimage) {
                this.imagetextimage = imagetextimage;
            }

            public VideodataBean getVideodata() {
                return videodata;
            }

            public void setVideodata(VideodataBean videodata) {
                this.videodata = videodata;
            }

            public GoodsdataBean getGoodsdata() {
                return goodsdata;
            }

            public void setGoodsdata(GoodsdataBean goodsdata) {
                this.goodsdata = goodsdata;
            }

            public static class AuthordataBean {
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

            public static class SofttextimageBean {
                /**
                 * softtextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg
                 */

                private String softtextimage_url;

                public String getSofttextimage_url() {
                    return softtextimage_url;
                }

                public void setSofttextimage_url(String softtextimage_url) {
                    this.softtextimage_url = softtextimage_url;
                }
            }

            public static class ImagetextimageBean {
                /**
                 * imagetext_id : 2
                 * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg
                 */

                private int imagetext_id;
                private String imagetextimage_url;

                public int getImagetext_id() {
                    return imagetext_id;
                }

                public void setImagetext_id(int imagetext_id) {
                    this.imagetext_id = imagetext_id;
                }

                public String getImagetextimage_url() {
                    return imagetextimage_url;
                }

                public void setImagetextimage_url(String imagetextimage_url) {
                    this.imagetextimage_url = imagetextimage_url;
                }
            }

            public static class VideodataBean {
                /**
                 * video_id : 4
                 * video_name : 少女峰火车沿途
                 * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/7d6f9764ab5027260204c561fbf3bed3.jpg
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

            public static class GoodsdataBean {
                /**
                 * goods_id : 2
                 * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/405bf41a7b33f5dffb658ba4c701f574.jpg
                 * goods_name : 定制真丝绡挂帘手工蓝染艺术空间装置植物染草木染山峦渐变窗帘子
                 * goods_price : 22
                 */

                private int goods_id;
                private String goods_pic;
                private String goods_name;
                private String goods_price;

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_pic() {
                    return goods_pic;
                }

                public void setGoods_pic(String goods_pic) {
                    this.goods_pic = goods_pic;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }
            }
        }
    }
}
