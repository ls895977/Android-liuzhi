package com.hykj.liuzhi.androidcomponents.ui.activity.min;

public class ChangePasswordBean {

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    /**
     * code : 0  0为成功 -1为失败
     * msg : 提示信息
     * error : 错误号
     */

    private int  code;
    private String msg;
    private int error;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
