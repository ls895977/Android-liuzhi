package com.hykj.liuzhi.androidcomponents.bean;

public class SignInBean {

    /**
     * code : 0
     * msg : 签到成功
     * error : 0
     * continuity : 1
     */

    private int code;
    private String msg;
    private int error;
    private int continuity;

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

    public int getContinuity() {
        return continuity;
    }

    public void setContinuity(int continuity) {
        this.continuity = continuity;
    }
}
