package com.hykj.liuzhi.androidcomponents.ui.activity.video.bean;

import com.google.gson.annotations.SerializedName;

public class VideoPointBean {

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    /**
     * code : 0
     * msg : 访问成功
     * “error” : 0
     */

   private int error;
    private int code;
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
