package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class DetailVideoBean {

    /**
     * code : 0
     * msg : 访问成功
     * error : 0
     * data : {"video_id":2,"admin_id":1,"video_name":"测试视频","video_point":2,"video_creattime":1541553464,"video_static":1,"video_collection":2,"video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg","video_detail":"测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频","video_labels":"1,2,3,4","video_videonum":1,"date":"2018-11-07","videodefinitiondata":[{"videodefinition_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/Standardclear/video/e87f3c11eb175498b6a237a0c838808f.mp4","videodefinition_definition":1,"videodefinition_download":"uploads/video2/Standardclear/video/e87f3c11eb175498b6a237a0c838808f.mp4","definition":"标清"}],"usercollection":1,"userpoint":1}
     */

    private int code;
    private String msg;
    private int error;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * video_id : 2
         * admin_id : 1
         * video_name : 测试视频
         * video_point : 2
         * video_creattime : 1541553464
         * video_static : 1
         * video_collection : 2
         * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg
         * video_detail : 测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频
         * video_labels : 1,2,3,4
         * video_videonum : 1
         * date : 2018-11-07
         * videodefinitiondata : [{"videodefinition_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/Standardclear/video/e87f3c11eb175498b6a237a0c838808f.mp4","videodefinition_definition":1,"videodefinition_download":"uploads/video2/Standardclear/video/e87f3c11eb175498b6a237a0c838808f.mp4","definition":"标清"}]
         * usercollection : 1
         * userpoint : 1
         */

        private int video_id;
        private int admin_id;
        private String video_name;
        private int video_point;
        private int video_creattime;
        private int video_static;
        private int video_collection;
        private String video_image;
        private String video_detail;
        private String video_labels;
        private int video_videonum;
        private String date;
        private int usercollection;
        private int userpoint;
        private List<VideodefinitiondataBean> videodefinitiondata;

        public int getVideo_id() {
            return video_id;
        }

        public void setVideo_id(int video_id) {
            this.video_id = video_id;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public String getVideo_name() {
            return video_name;
        }

        public void setVideo_name(String video_name) {
            this.video_name = video_name;
        }

        public int getVideo_point() {
            return video_point;
        }

        public void setVideo_point(int video_point) {
            this.video_point = video_point;
        }

        public int getVideo_creattime() {
            return video_creattime;
        }

        public void setVideo_creattime(int video_creattime) {
            this.video_creattime = video_creattime;
        }

        public int getVideo_static() {
            return video_static;
        }

        public void setVideo_static(int video_static) {
            this.video_static = video_static;
        }

        public int getVideo_collection() {
            return video_collection;
        }

        public void setVideo_collection(int video_collection) {
            this.video_collection = video_collection;
        }

        public String getVideo_image() {
            return video_image;
        }

        public void setVideo_image(String video_image) {
            this.video_image = video_image;
        }

        public String getVideo_detail() {
            return video_detail;
        }

        public void setVideo_detail(String video_detail) {
            this.video_detail = video_detail;
        }

        public String getVideo_labels() {
            return video_labels;
        }

        public void setVideo_labels(String video_labels) {
            this.video_labels = video_labels;
        }

        public int getVideo_videonum() {
            return video_videonum;
        }

        public void setVideo_videonum(int video_videonum) {
            this.video_videonum = video_videonum;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getUsercollection() {
            return usercollection;
        }

        public void setUsercollection(int usercollection) {
            this.usercollection = usercollection;
        }

        public int getUserpoint() {
            return userpoint;
        }

        public void setUserpoint(int userpoint) {
            this.userpoint = userpoint;
        }

        public List<VideodefinitiondataBean> getVideodefinitiondata() {
            return videodefinitiondata;
        }

        public void setVideodefinitiondata(List<VideodefinitiondataBean> videodefinitiondata) {
            this.videodefinitiondata = videodefinitiondata;
        }

        public static class VideodefinitiondataBean {
            /**
             * videodefinition_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/Standardclear/video/e87f3c11eb175498b6a237a0c838808f.mp4
             * videodefinition_definition : 1
             * videodefinition_download : uploads/video2/Standardclear/video/e87f3c11eb175498b6a237a0c838808f.mp4
             * definition : 标清
             */

            private String videodefinition_url;
            private int videodefinition_definition;
            private String videodefinition_download;
            private String definition;

            public String getVideodefinition_url() {
                return videodefinition_url;
            }

            public void setVideodefinition_url(String videodefinition_url) {
                this.videodefinition_url = videodefinition_url;
            }

            public int getVideodefinition_definition() {
                return videodefinition_definition;
            }

            public void setVideodefinition_definition(int videodefinition_definition) {
                this.videodefinition_definition = videodefinition_definition;
            }

            public String getVideodefinition_download() {
                return videodefinition_download;
            }

            public void setVideodefinition_download(String videodefinition_download) {
                this.videodefinition_download = videodefinition_download;
            }

            public String getDefinition() {
                return definition;
            }

            public void setDefinition(String definition) {
                this.definition = definition;
            }
        }
    }
}
