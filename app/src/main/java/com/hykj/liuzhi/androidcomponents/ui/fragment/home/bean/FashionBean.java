package com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean;

import java.util.List;

public class FashionBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"softtext_id":1,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":2,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":3,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext3/image/427d8a89e6e86012d38b82f58ad93a63.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":4,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext4/image/d656979e04b918b3610bf9681210318c.jpeg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":5,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext5/image/ebefe87a74c86e59df1ff6f1cd763a36.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":6,"softtext_title":null,"user_id":4,"softtextimage":null,"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_nickname":"萌萌哒醉了"}}],"advdata":[{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/ed455cfb85485888486ce6843f4b24e3.jpg"},{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/de2437b9e25731389923d2bbf32e3d54.jpg"}],"total":6,"amount":1,"page":1,"number":10}
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
         * array : [{"softtext_id":1,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":2,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext2/image/a1c8c4795b1ee70941169a6d07212062.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":3,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext3/image/427d8a89e6e86012d38b82f58ad93a63.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":4,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext4/image/d656979e04b918b3610bf9681210318c.jpeg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":5,"softtext_title":"测试标题","user_id":1,"softtextimage":{"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext5/image/ebefe87a74c86e59df1ff6f1cd763a36.jpg"},"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}},{"softtext_id":6,"softtext_title":null,"user_id":4,"softtextimage":null,"userdata":{"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user4/userpic/66fd01a5ebeef2a1627d47d254c6f4f5.png","user_nickname":"萌萌哒醉了"}}]
         * advdata : [{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/ed455cfb85485888486ce6843f4b24e3.jpg"},{"adv_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/de2437b9e25731389923d2bbf32e3d54.jpg"}]
         * total : 6
         * amount : 1
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private int page;
        private int number;
        private List<ArrayBean> array;
        private List<AdvdataBean> advdata;

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

        public List<AdvdataBean> getAdvdata() {
            return advdata;
        }

        public void setAdvdata(List<AdvdataBean> advdata) {
            this.advdata = advdata;
        }

        public static class ArrayBean {
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
