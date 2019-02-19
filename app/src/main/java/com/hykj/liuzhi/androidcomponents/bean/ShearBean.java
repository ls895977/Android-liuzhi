package com.hykj.liuzhi.androidcomponents.bean;

public class ShearBean {

    /**
     * code : 0
     * msg : 访问成功
     * url : http://liuzhi.365hy.com/index/index/softtextdetail.html?softtext_id=2
     */

    private int code;
    private String msg;
    private String url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
