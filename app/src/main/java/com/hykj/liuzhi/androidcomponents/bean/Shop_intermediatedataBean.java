package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class Shop_intermediatedataBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : [{"name":"定制真丝绡挂帘手工蓝染艺术空间装置植物染草木染山峦渐变窗帘子","image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/405bf41a7b33f5dffb658ba4c701f574.jpg","money":"220","number":10},{"name":"全金属拼图安妮女王海盗船3D立体模型创意礼物手工DIY模型拼装","image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/c3a9d9905e6eb4584666d95f42623ce3.jpg","money":"300","number":2}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 定制真丝绡挂帘手工蓝染艺术空间装置植物染草木染山峦渐变窗帘子
         * image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext/image/405bf41a7b33f5dffb658ba4c701f574.jpg
         * money : 220
         * number : 10
         */

        private String name;
        private String image;
        private String money;
        private int number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
