package com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean;

import java.io.Serializable;
import java.util.List;

public class CircleFragmentBean implements Serializable{

    /**
     * code : 0
     * msg : 访问成功
     * data : {"showing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/44c7a34751bf79f2ec6dc76995e77158.jpg","userdata":[{"user_id":1,"imagetextnumber":17,"userdetail":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_nickname":"好么"}},{"user_id":4,"imagetextnumber":9,"userdetail":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_nickname":"萌萌哒醉了"}}],"array":[{"imagetext_id":1,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"}},{"imagetext_id":2,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"}},{"imagetext_id":3,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext3/image/e4155f5356c2cebd1223a3dc2601b4e6.jpg"}},{"imagetext_id":4,"imagetextimagedata":null},{"imagetext_id":5,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext5/image/bc1edce72d168c602e77c29f69fb97d2.png"}},{"imagetext_id":6,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext6/image/a96c59f7419f2ef71c74a1775827ed4f.png"}},{"imagetext_id":7,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext7/image/b8cb44cf7860a0ca0f92dd8418abd236.png"}},{"imagetext_id":8,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext8/image/6b5b05516b2ad02984de51468f534a7e.png"}},{"imagetext_id":9,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext9/image/ec6e6c4641fb0d10fd1bcc90e4d933a3.png"}},{"imagetext_id":10,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext10/image/dd587dcb87b60fce9d8f5ad956f8184a.png"}},{"imagetext_id":11,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext11/image/bfb9b206c0470de41f3d9a153f22803f.png"}},{"imagetext_id":12,"imagetextimagedata":null},{"imagetext_id":13,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext13/image/f2fedaf60ba634ce9caf91bd33b758c4.jpeg"}},{"imagetext_id":16,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext16/image/fe67ce9c86ee1f6bf56f93e2b1360611.jpeg"}},{"imagetext_id":17,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext17/image/bc7854eeac8eb7f410ac2242aa3fcc9a.jpeg"}},{"imagetext_id":23,"imagetextimagedata":null},{"imagetext_id":24,"imagetextimagedata":null},{"imagetext_id":25,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext25/image/4915bb69f139e7ba58390e611c683bff.png"}},{"imagetext_id":26,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext26/image/521a93e14ac4e86b31def2427d51dd8e.png"}},{"imagetext_id":29,"imagetextimagedata":null}],"total":26,"amount":2,"page":1,"number":20}
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

    public static class DataBean implements Serializable{
        /**
         * showing_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/44c7a34751bf79f2ec6dc76995e77158.jpg
         * userdata : [{"user_id":1,"imagetextnumber":17,"userdetail":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_nickname":"好么"}},{"user_id":4,"imagetextnumber":9,"userdetail":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_nickname":"萌萌哒醉了"}}]
         * array : [{"imagetext_id":1,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"}},{"imagetext_id":2,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext2/image/7be2cb04b20f9e70bbebf68783b4e52f.jpg"}},{"imagetext_id":3,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext3/image/e4155f5356c2cebd1223a3dc2601b4e6.jpg"}},{"imagetext_id":4,"imagetextimagedata":null},{"imagetext_id":5,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext5/image/bc1edce72d168c602e77c29f69fb97d2.png"}},{"imagetext_id":6,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext6/image/a96c59f7419f2ef71c74a1775827ed4f.png"}},{"imagetext_id":7,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext7/image/b8cb44cf7860a0ca0f92dd8418abd236.png"}},{"imagetext_id":8,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext8/image/6b5b05516b2ad02984de51468f534a7e.png"}},{"imagetext_id":9,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext9/image/ec6e6c4641fb0d10fd1bcc90e4d933a3.png"}},{"imagetext_id":10,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext10/image/dd587dcb87b60fce9d8f5ad956f8184a.png"}},{"imagetext_id":11,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext11/image/bfb9b206c0470de41f3d9a153f22803f.png"}},{"imagetext_id":12,"imagetextimagedata":null},{"imagetext_id":13,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext13/image/f2fedaf60ba634ce9caf91bd33b758c4.jpeg"}},{"imagetext_id":16,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext16/image/fe67ce9c86ee1f6bf56f93e2b1360611.jpeg"}},{"imagetext_id":17,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext17/image/bc7854eeac8eb7f410ac2242aa3fcc9a.jpeg"}},{"imagetext_id":23,"imagetextimagedata":null},{"imagetext_id":24,"imagetextimagedata":null},{"imagetext_id":25,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext25/image/4915bb69f139e7ba58390e611c683bff.png"}},{"imagetext_id":26,"imagetextimagedata":{"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext26/image/521a93e14ac4e86b31def2427d51dd8e.png"}},{"imagetext_id":29,"imagetextimagedata":null}]
         * total : 26
         * amount : 2
         * page : 1
         * number : 20
         */

        private String showing_url;
        private int total;
        private int amount;
        private int page;
        private int number;
        private List<UserdataBean> userdata;
        private List<ArrayBean> array;

        public String getShowing_url() {
            return showing_url;
        }

        public void setShowing_url(String showing_url) {
            this.showing_url = showing_url;
        }

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

        public List<UserdataBean> getUserdata() {
            return userdata;
        }

        public void setUserdata(List<UserdataBean> userdata) {
            this.userdata = userdata;
        }

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class UserdataBean implements Serializable {
            /**
             * user_id : 1
             * imagetextnumber : 17
             * userdetail : {"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png","user_nickname":"好么"}
             */

            private int user_id;
            private int imagetextnumber;
            private UserdetailBean userdetail;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getImagetextnumber() {
                return imagetextnumber;
            }

            public void setImagetextnumber(int imagetextnumber) {
                this.imagetextnumber = imagetextnumber;
            }

            public UserdetailBean getUserdetail() {
                return userdetail;
            }

            public void setUserdetail(UserdetailBean userdetail) {
                this.userdetail = userdetail;
            }

            public static class UserdetailBean implements Serializable{
                /**
                 * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/e24f2ea067d52df1bd2d045c2c722437.png
                 * user_nickname : 好么
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

        public static class ArrayBean implements Serializable{
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

            public static class ImagetextimagedataBean implements Serializable{
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
