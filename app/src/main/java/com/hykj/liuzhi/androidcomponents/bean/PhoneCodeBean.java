package com.hykj.liuzhi.androidcomponents.bean;

public class PhoneCodeBean {

    /**
     * code : 0
     * msg : 验证码发送成功
     * data : {"code":"mxan34"}
     * error : 0
     */

    private int code;
    private String msg;
    private DataBean data;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * code : mxan34
         */

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
