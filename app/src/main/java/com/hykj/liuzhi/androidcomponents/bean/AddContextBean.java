package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class AddContextBean {

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"id":110000,"parent_id":0,"level":1,"name":"北京市","full_name":"北京市"},{"id":120000,"parent_id":0,"level":1,"name":"天津市","full_name":"天津市"},{"id":130000,"parent_id":0,"level":1,"name":"河北省","full_name":"河北省"},{"id":140000,"parent_id":0,"level":1,"name":"山西省","full_name":"山西省"},{"id":150000,"parent_id":0,"level":1,"name":"内蒙古自治区","full_name":"内蒙古自治区"},{"id":210000,"parent_id":0,"level":1,"name":"辽宁省","full_name":"辽宁省"},{"id":220000,"parent_id":0,"level":1,"name":"吉林省","full_name":"吉林省"},{"id":230000,"parent_id":0,"level":1,"name":"黑龙江省","full_name":"黑龙江省"},{"id":310000,"parent_id":0,"level":1,"name":"上海市","full_name":"上海市"},{"id":320000,"parent_id":0,"level":1,"name":"江苏省","full_name":"江苏省"}],"total":34,"amount":4,"page":1,"number":10}
     */

    private int error;
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
        /**
         * array : [{"id":110000,"parent_id":0,"level":1,"name":"北京市","full_name":"北京市"},{"id":120000,"parent_id":0,"level":1,"name":"天津市","full_name":"天津市"},{"id":130000,"parent_id":0,"level":1,"name":"河北省","full_name":"河北省"},{"id":140000,"parent_id":0,"level":1,"name":"山西省","full_name":"山西省"},{"id":150000,"parent_id":0,"level":1,"name":"内蒙古自治区","full_name":"内蒙古自治区"},{"id":210000,"parent_id":0,"level":1,"name":"辽宁省","full_name":"辽宁省"},{"id":220000,"parent_id":0,"level":1,"name":"吉林省","full_name":"吉林省"},{"id":230000,"parent_id":0,"level":1,"name":"黑龙江省","full_name":"黑龙江省"},{"id":310000,"parent_id":0,"level":1,"name":"上海市","full_name":"上海市"},{"id":320000,"parent_id":0,"level":1,"name":"江苏省","full_name":"江苏省"}]
         * total : 34
         * amount : 4
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private int page;
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

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
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
             * id : 110000
             * parent_id : 0
             * level : 1
             * name : 北京市
             * full_name : 北京市
             */

            private int id;
            private int parent_id;
            private int level;
            private String name;
            private String full_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }
        }
    }
}
