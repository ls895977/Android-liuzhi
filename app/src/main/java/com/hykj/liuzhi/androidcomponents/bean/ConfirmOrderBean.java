package com.hykj.liuzhi.androidcomponents.bean;

public class ConfirmOrderBean {

    /**
     * code : 0
     * msg : 对不起，您的订单金额太少，无法进行抵扣操作。
     * error : 1
     */

    private int code;
    private String msg;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
