package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class GetreporTreasonBean {


    /**
     * code : 0
     * msg : 访问成功
     * data : [{"id":1,"name":"色情低俗"},{"id":2,"name":"政治敏感"},{"id":3,"name":"违法犯罪"},{"id":4,"name":"盗用TA人作品"},{"id":5,"name":"疑似自我伤害"},{"id":6,"name":"发布垃圾广告、售卖假药等"},{"id":7,"name":"侵犯隐私"},{"id":8,"name":"造谣传谣、涉嫌欺诈"},{"id":9,"name":"侮辱谩骂"},{"id":10,"name":"头像、昵称、签名违规"}]
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
         * id : 1
         * name : 色情低俗
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
