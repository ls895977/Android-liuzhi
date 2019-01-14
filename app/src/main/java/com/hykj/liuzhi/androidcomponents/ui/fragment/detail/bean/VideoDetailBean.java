package com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean;

import java.util.List;

public class VideoDetailBean {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg","video_collection":2,"video_videonum":1,"video_labels":"1,2,3,4","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":4,"videolabel_name":"测试标签4","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":3,"video_name":"测试视频2","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video3/image/2c1f454e391f86ab91c4471d6a5ea99f.jpg","video_collection":0,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":4,"video_name":"测试视频3","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video4/image/9f243b92edb9ad1eef4fa16b559b8cc6.jpg","video_collection":1,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":5,"video_name":"测试视频3","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video5/image/4dcf17197614b956bc900a5f18d6f608.jpg","video_collection":0,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":6,"video_name":"测试视频4","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video6/image/f0fcb44f9922bf3abc764f22c34fd34b.jpg","video_collection":0,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]}],"total":5,"amount":1,"page":1,"number":10}
     */
    private int code;
    private String msg;
    private DataBean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    private int error;
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
         * array : [{"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg","video_collection":2,"video_videonum":1,"video_labels":"1,2,3,4","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":4,"videolabel_name":"测试标签4","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":3,"video_name":"测试视频2","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video3/image/2c1f454e391f86ab91c4471d6a5ea99f.jpg","video_collection":0,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":4,"video_name":"测试视频3","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video4/image/9f243b92edb9ad1eef4fa16b559b8cc6.jpg","video_collection":1,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":5,"video_name":"测试视频3","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video5/image/4dcf17197614b956bc900a5f18d6f608.jpg","video_collection":0,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]},{"video_id":6,"video_name":"测试视频4","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video6/image/f0fcb44f9922bf3abc764f22c34fd34b.jpg","video_collection":0,"video_videonum":0,"video_labels":"1,2,3","videolabels":[{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1}]}]
         * total : 5
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
             * video_id : 2
             * video_name : 测试视频
             * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg
             * video_collection : 2
             * video_videonum : 1
             * video_labels : 1,2,3,4
             * videolabels : [{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":4,"videolabel_name":"测试标签4","videolabel_creattime":1542080807,"videolabel_status":1}]
             */

            private int video_id;
            private String video_name;
            private String video_image;
            private int video_collection;
            private int video_videonum;
            private String video_labels;
            private List<VideolabelsBean> videolabels;

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

            public int getVideo_collection() {
                return video_collection;
            }

            public void setVideo_collection(int video_collection) {
                this.video_collection = video_collection;
            }

            public int getVideo_videonum() {
                return video_videonum;
            }

            public void setVideo_videonum(int video_videonum) {
                this.video_videonum = video_videonum;
            }

            public String getVideo_labels() {
                return video_labels;
            }

            public void setVideo_labels(String video_labels) {
                this.video_labels = video_labels;
            }

            public List<VideolabelsBean> getVideolabels() {
                return videolabels;
            }

            public void setVideolabels(List<VideolabelsBean> videolabels) {
                this.videolabels = videolabels;
            }

            public static class VideolabelsBean {
                /**
                 * videolabel_id : 1
                 * videolabel_name : 测试标签1
                 * videolabel_creattime : 1542080807
                 * videolabel_status : 1
                 */

                private int videolabel_id;
                private String videolabel_name;
                private int videolabel_creattime;
                private int videolabel_status;

                public int getVideolabel_id() {
                    return videolabel_id;
                }

                public void setVideolabel_id(int videolabel_id) {
                    this.videolabel_id = videolabel_id;
                }

                public String getVideolabel_name() {
                    return videolabel_name;
                }

                public void setVideolabel_name(String videolabel_name) {
                    this.videolabel_name = videolabel_name;
                }

                public int getVideolabel_creattime() {
                    return videolabel_creattime;
                }

                public void setVideolabel_creattime(int videolabel_creattime) {
                    this.videolabel_creattime = videolabel_creattime;
                }

                public int getVideolabel_status() {
                    return videolabel_status;
                }

                public void setVideolabel_status(int videolabel_status) {
                    this.videolabel_status = videolabel_status;
                }
            }
        }
    }
}
