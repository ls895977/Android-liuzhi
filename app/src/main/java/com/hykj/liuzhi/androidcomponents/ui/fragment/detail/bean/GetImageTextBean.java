package com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean;

import java.util.List;

public class GetImageTextBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"imagetext_id":1,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"}},{"imagetext_id":2,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"}},{"imagetext_id":3,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext3/image/e4155f5356c2cebd1223a3dc2601b4e6.jpg"}},{"imagetext_id":5,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext5/image/bc1edce72d168c602e77c29f69fb97d2.png"}},{"imagetext_id":6,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext6/image/a96c59f7419f2ef71c74a1775827ed4f.png"}},{"imagetext_id":7,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext7/image/b8cb44cf7860a0ca0f92dd8418abd236.png"}},{"imagetext_id":8,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext8/image/6b5b05516b2ad02984de51468f534a7e.png"}},{"imagetext_id":9,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext9/image/ec6e6c4641fb0d10fd1bcc90e4d933a3.png"}},{"imagetext_id":10,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext10/image/dd587dcb87b60fce9d8f5ad956f8184a.png"}},{"imagetext_id":33,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext33/image/c4990278a4d54d80c8f4cb554a5af2fe.png"}}],"total":10,"amount":1,"page":1,"number":10}
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
         * array : [{"imagetext_id":1,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"}},{"imagetext_id":2,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"}},{"imagetext_id":3,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext3/image/e4155f5356c2cebd1223a3dc2601b4e6.jpg"}},{"imagetext_id":5,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext5/image/bc1edce72d168c602e77c29f69fb97d2.png"}},{"imagetext_id":6,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext6/image/a96c59f7419f2ef71c74a1775827ed4f.png"}},{"imagetext_id":7,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext7/image/b8cb44cf7860a0ca0f92dd8418abd236.png"}},{"imagetext_id":8,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext8/image/6b5b05516b2ad02984de51468f534a7e.png"}},{"imagetext_id":9,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext9/image/ec6e6c4641fb0d10fd1bcc90e4d933a3.png"}},{"imagetext_id":10,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext10/image/dd587dcb87b60fce9d8f5ad956f8184a.png"}},{"imagetext_id":33,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext33/image/c4990278a4d54d80c8f4cb554a5af2fe.png"}}]
         * total : 10
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
             * imagetext_id : 1
             * imagetextimagedata : {"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"}
             */

            private int imagetext_id;
            private ImagetextimagedataBean imagetextimagedata;

            public int getImagetext_id() {
                return imagetext_id;
            }

            public void setImagetext_id(int imagetext_id) {
                this.imagetext_id = imagetext_id;
            }

            public ImagetextimagedataBean getImagetextimagedata() {
                return imagetextimagedata;
            }

            public void setImagetextimagedata(ImagetextimagedataBean imagetextimagedata) {
                this.imagetextimagedata = imagetextimagedata;
            }

            public static class ImagetextimagedataBean {
                /**
                 * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg
                 */

                private String imagetextimage_url;

                public String getImagetextimage_url() {
                    return imagetextimage_url;
                }

                public void setImagetextimage_url(String imagetextimage_url) {
                    this.imagetextimage_url = imagetextimage_url;
                }
            }
        }
    }
}
