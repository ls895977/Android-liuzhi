package com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean;

public class CollectionBean {

    /**
     * code : 0
     * msg : 访问成功
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
}
