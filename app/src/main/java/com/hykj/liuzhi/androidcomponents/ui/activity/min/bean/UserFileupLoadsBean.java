package com.hykj.liuzhi.androidcomponents.ui.activity.min.bean;

public class UserFileupLoadsBean {

    /**
     * code : 0
     * msg : 修改成功
     * file : {"idcardpos":{"error":0,"msg":"idcardpos文件上传成功！！！"},"idcardback":{"error":0,"msg":"idcardback文件上传成功！！！"},"idcardpeople":{"error":0,"msg":"idcardpeople文件上传成功！！！"}}
     * error : 0
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public static class FileBean {
        /**
         * idcardpos : {"error":0,"msg":"idcardpos文件上传成功！！！"}
         * idcardback : {"error":0,"msg":"idcardback文件上传成功！！！"}
         * idcardpeople : {"error":0,"msg":"idcardpeople文件上传成功！！！"}
         */

        private IdcardposBean idcardpos;
        private IdcardbackBean idcardback;
        private IdcardpeopleBean idcardpeople;

        public IdcardposBean getIdcardpos() {
            return idcardpos;
        }

        public void setIdcardpos(IdcardposBean idcardpos) {
            this.idcardpos = idcardpos;
        }

        public IdcardbackBean getIdcardback() {
            return idcardback;
        }

        public void setIdcardback(IdcardbackBean idcardback) {
            this.idcardback = idcardback;
        }

        public IdcardpeopleBean getIdcardpeople() {
            return idcardpeople;
        }

        public void setIdcardpeople(IdcardpeopleBean idcardpeople) {
            this.idcardpeople = idcardpeople;
        }

        public static class IdcardposBean {
            /**
             * error : 0
             * msg : idcardpos文件上传成功！！！
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

        public static class IdcardbackBean {
            /**
             * error : 0
             * msg : idcardback文件上传成功！！！
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

        public static class IdcardpeopleBean {
            /**
             * error : 0
             * msg : idcardpeople文件上传成功！！！
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
}
