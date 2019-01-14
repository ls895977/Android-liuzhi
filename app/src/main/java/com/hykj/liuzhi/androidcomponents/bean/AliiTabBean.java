package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class AliiTabBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : [{"label_id":1,"label_name":"90后","isselect":0},{"label_id":2,"label_name":"拖延症","isselect":1},{"label_id":3,"label_name":"最帅的","isselect":0},{"label_id":4,"label_name":"来自星星的自己","isselect":1},{"label_id":5,"label_name":"愤青","isselect":0},{"label_id":6,"label_name":"牛逼","isselect":1}]
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
    private List<DataBean> data;

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
         * label_id : 1
         * label_name : 90后
         * isselect : 0
         */

        private int label_id;
        private String label_name;
        private int isselect;

        public int getLabel_id() {
            return label_id;
        }

        public void setLabel_id(int label_id) {
            this.label_id = label_id;
        }

        public String getLabel_name() {
            return label_name;
        }

        public void setLabel_name(String label_name) {
            this.label_name = label_name;
        }

        public int getIsselect() {
            return isselect;
        }

        public void setIsselect(int isselect) {
            this.isselect = isselect;
        }
    }
}
