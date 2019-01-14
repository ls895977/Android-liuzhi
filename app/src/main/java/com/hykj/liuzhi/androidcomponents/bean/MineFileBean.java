package com.hykj.liuzhi.androidcomponents.bean;

public class MineFileBean {

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    /**
     * code : 0
     * msg : 访问成功
     * file : {"error":1,"msg":"文件上传格式错误！！"}
     */

    private int code;
    private String msg;
    private FileBean file;
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

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public static class FileBean {
        /**
         * error : 1
         * msg : 文件上传格式错误！！
         */

        private int error;
        private String msg;

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
