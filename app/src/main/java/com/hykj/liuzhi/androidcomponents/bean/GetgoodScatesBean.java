package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class GetgoodScatesBean {


    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"goodscate_id":1,"goodscate_name":"类别1","goodscate_pid":0,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[{"goodscate_id":3,"goodscate_name":"类别3","goodscate_pid":1,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[]}]},{"goodscate_id":2,"goodscate_name":"类别2","goodscate_pid":0,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[{"goodscate_id":4,"goodscate_name":"类别4","goodscate_pid":2,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[]}]}],"total":4,"amount":1,"page":1,"number":10}
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
         * array : [{"goodscate_id":1,"goodscate_name":"类别1","goodscate_pid":0,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[{"goodscate_id":3,"goodscate_name":"类别3","goodscate_pid":1,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[]}]},{"goodscate_id":2,"goodscate_name":"类别2","goodscate_pid":0,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[{"goodscate_id":4,"goodscate_name":"类别4","goodscate_pid":2,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[]}]}]
         * total : 4
         * amount : 1
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
             * goodscate_id : 1
             * goodscate_name : 类别1
             * goodscate_pid : 0
             * goodscate_creattime : 1540779228
             * date : 2018-10-29 10:13:48
             * children : [{"goodscate_id":3,"goodscate_name":"类别3","goodscate_pid":1,"goodscate_creattime":1540779228,"date":"2018-10-29 10:13:48","children":[]}]
             */
            private boolean onOff;

            public boolean isOnOff() {
                return onOff;
            }

            public void setOnOff(boolean onOff) {
                this.onOff = onOff;
            }

            private int goodscate_id;
            private String goodscate_name;
            private int goodscate_pid;
            private int goodscate_creattime;
            private String date;
            private List<ChildrenBean> children;

            public int getGoodscate_id() {
                return goodscate_id;
            }

            public void setGoodscate_id(int goodscate_id) {
                this.goodscate_id = goodscate_id;
            }

            public String getGoodscate_name() {
                return goodscate_name;
            }

            public void setGoodscate_name(String goodscate_name) {
                this.goodscate_name = goodscate_name;
            }

            public int getGoodscate_pid() {
                return goodscate_pid;
            }

            public void setGoodscate_pid(int goodscate_pid) {
                this.goodscate_pid = goodscate_pid;
            }

            public int getGoodscate_creattime() {
                return goodscate_creattime;
            }

            public void setGoodscate_creattime(int goodscate_creattime) {
                this.goodscate_creattime = goodscate_creattime;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * goodscate_id : 3
                 * goodscate_name : 类别3
                 * goodscate_pid : 1
                 * goodscate_creattime : 1540779228
                 * date : 2018-10-29 10:13:48
                 * children : []
                 */

                private int goodscate_id;
                private String goodscate_name;
                private int goodscate_pid;
                private int goodscate_creattime;
                private String date;
                private List<?> children;

                public int getGoodscate_id() {
                    return goodscate_id;
                }

                public void setGoodscate_id(int goodscate_id) {
                    this.goodscate_id = goodscate_id;
                }

                public String getGoodscate_name() {
                    return goodscate_name;
                }

                public void setGoodscate_name(String goodscate_name) {
                    this.goodscate_name = goodscate_name;
                }

                public int getGoodscate_pid() {
                    return goodscate_pid;
                }

                public void setGoodscate_pid(int goodscate_pid) {
                    this.goodscate_pid = goodscate_pid;
                }

                public int getGoodscate_creattime() {
                    return goodscate_creattime;
                }

                public void setGoodscate_creattime(int goodscate_creattime) {
                    this.goodscate_creattime = goodscate_creattime;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }
            }
        }
    }
}
