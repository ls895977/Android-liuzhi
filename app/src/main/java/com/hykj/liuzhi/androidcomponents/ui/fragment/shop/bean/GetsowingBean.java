package com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean;

import java.util.List;

public class GetsowingBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/44c7a34751bf79f2ec6dc76995e77158.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/a2d2bc4e0c9dea41ed82b442ca65e49d.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/0be0b5b450ac142b88762f29d86c9a11.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/151a112b2edff2959b0437e14c071e7a.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/566b10397cac314ebcb2029a39ca1c9c.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/170a2c2314ff7a3e07c978d41a0a1f69.jpg"}],"total":6,"amount":1,"page":1,"number":10}
     */
    private int error;

    public int getError() {
        return error;
    }

    private int code;

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
         * array : [{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/44c7a34751bf79f2ec6dc76995e77158.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/a2d2bc4e0c9dea41ed82b442ca65e49d.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/0be0b5b450ac142b88762f29d86c9a11.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/151a112b2edff2959b0437e14c071e7a.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/566b10397cac314ebcb2029a39ca1c9c.jpg"},{"sowing_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/image/170a2c2314ff7a3e07c978d41a0a1f69.jpg"}]
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
    }
}
