package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class AppmodelBean {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"model_id":1,"model_name":"纹理","model_creattime":1551076097,"model_type":1},{"model_id":2,"model_name":"潮流","model_creattime":1551076097,"model_type":1},{"model_id":3,"model_name":"玩品","model_creattime":1551076097,"model_type":1},{"model_id":10,"model_name":"专属","model_creattime":1551076097,"model_type":2}]}
     */
    private int code;
    private String msg;
    private DataBean data;

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
        private List<ArrayBean> array;

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean {
            /**
             * model_id : 1
             * model_name : 纹理
             * model_creattime : 1551076097
             * model_type : 1
             */

            private int model_id;
            private String model_name;
            private int model_creattime;
            private int model_type;

            public int getModel_id() {
                return model_id;
            }

            public void setModel_id(int model_id) {
                this.model_id = model_id;
            }

            public String getModel_name() {
                return model_name;
            }

            public void setModel_name(String model_name) {
                this.model_name = model_name;
            }

            public int getModel_creattime() {
                return model_creattime;
            }

            public void setModel_creattime(int model_creattime) {
                this.model_creattime = model_creattime;
            }

            public int getModel_type() {
                return model_type;
            }

            public void setModel_type(int model_type) {
                this.model_type = model_type;
            }
        }
    }
}
