package com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean;

import java.util.List;

public class WatchHistoryBean1 {


    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"browses_id":4,"user_id":1,"browses_creattime":1541507996,"softtext_id":0,"imagetext_id":2,"imagetextimage":{"imagetextimage_id":4,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg","imagetext_id":2}},{"browses_id":5,"user_id":1,"browses_creattime":1541508073,"softtext_id":0,"imagetext_id":1,"imagetextimage":{"imagetextimage_id":1,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg","imagetext_id":1}}],"total":2,"amount":1,"page":"1","number":"10"}
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
         * array : [{"browses_id":4,"user_id":1,"browses_creattime":1541507996,"softtext_id":0,"imagetext_id":2,"imagetextimage":{"imagetextimage_id":4,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg","imagetext_id":2}},{"browses_id":5,"user_id":1,"browses_creattime":1541508073,"softtext_id":0,"imagetext_id":1,"imagetextimage":{"imagetextimage_id":1,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg","imagetext_id":1}}]
         * total : 2
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
             * browses_id : 4
             * user_id : 1
             * browses_creattime : 1541507996
             * softtext_id : 0
             * imagetext_id : 2
             * imagetextimage : {"imagetextimage_id":4,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg","imagetext_id":2}
             */

            private int browses_id;
            private int user_id;
            private int browses_creattime;
            private int softtext_id;
            private int imagetext_id;
            private ImagetextimageBean imagetextimage;

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

            public ImagetextimageBean getImagetextimage() {
                return imagetextimage;
            }

            public void setImagetextimage(ImagetextimageBean imagetextimage) {
                this.imagetextimage = imagetextimage;
            }

            public static class ImagetextimageBean {
                /**
                 * imagetextimage_id : 4
                 * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg
                 * imagetext_id : 2
                 */

                private int imagetextimage_id;
                private String imagetextimage_url;
                private int imagetext_id;

                public int getImagetextimage_id() {
                    return imagetextimage_id;
                }

                public void setImagetextimage_id(int imagetextimage_id) {
                    this.imagetextimage_id = imagetextimage_id;
                }

                public String getImagetextimage_url() {
                    return imagetextimage_url;
                }

                public void setImagetextimage_url(String imagetextimage_url) {
                    this.imagetextimage_url = imagetextimage_url;
                }

                public int getImagetext_id() {
                    return imagetext_id;
                }

                public void setImagetext_id(int imagetext_id) {
                    this.imagetext_id = imagetext_id;
                }
            }
        }
    }
}
