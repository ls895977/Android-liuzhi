package com.hykj.liuzhi.androidcomponents.ui.activity.softtext.ben;

import java.util.List;

public class GetimagetextlabelsBean {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"imagetextlabel_id":1,"imagetextlabel_name":"标签1","imagetextlabel_creattime":1539854634},{"imagetextlabel_id":2,"imagetextlabel_name":"标签2","imagetextlabel_creattime":1539854653},{"imagetextlabel_id":3,"imagetextlabel_name":"标签3","imagetextlabel_creattime":1539854684}],"total":3,"amount":1,"page":"1","number":10}
     */
    private int code;
    private String msg;
    private DataBean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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

    public static class DataBean {
        /**
         * array : [{"imagetextlabel_id":1,"imagetextlabel_name":"标签1","imagetextlabel_creattime":1539854634},{"imagetextlabel_id":2,"imagetextlabel_name":"标签2","imagetextlabel_creattime":1539854653},{"imagetextlabel_id":3,"imagetextlabel_name":"标签3","imagetextlabel_creattime":1539854684}]
         * total : 3
         * amount : 1
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private String page;
        private int number;
        private List<ArrayBean> array;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean {
            /**
             * imagetextlabel_id : 1
             * imagetextlabel_name : 标签1
             * imagetextlabel_creattime : 1539854634
             */

            private int imagetextlabel_id;
            private String imagetextlabel_name;
            private int imagetextlabel_creattime;

            public int getImagetextlabel_id() {
                return imagetextlabel_id;
            }

            public void setImagetextlabel_id(int imagetextlabel_id) {
                this.imagetextlabel_id = imagetextlabel_id;
            }

            public String getImagetextlabel_name() {
                return imagetextlabel_name;
            }

            public void setImagetextlabel_name(String imagetextlabel_name) {
                this.imagetextlabel_name = imagetextlabel_name;
            }

            public int getImagetextlabel_creattime() {
                return imagetextlabel_creattime;
            }

            public void setImagetextlabel_creattime(int imagetextlabel_creattime) {
                this.imagetextlabel_creattime = imagetextlabel_creattime;
            }
        }
    }
}
