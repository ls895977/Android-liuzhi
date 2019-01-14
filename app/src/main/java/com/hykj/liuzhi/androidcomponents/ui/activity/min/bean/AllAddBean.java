package com.hykj.liuzhi.androidcomponents.ui.activity.min.bean;

import java.io.Serializable;
import java.util.List;

public class AllAddBean implements Serializable {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"address_id":1,"address_user":"都恒辰","address_phone":"18840518095","address_address":"东港市 五四小区 2-206","address_longitude":"123.876870274","address_latitude":"39.981217334184","full_name":"辽宁省 丹东市 东港市","user_id":1,"region_id":210681,"address_status":0},{"address_id":2,"address_user":"王小贱","address_phone":"13581522527","address_address":"和平小区 2栋408","address_longitude":"123.41433166046","address_latitude":"41.786474395792","full_name":"辽宁省 沈阳市 和平区","user_id":1,"region_id":210102,"address_status":1}],"total":2,"amount":1,"page":1,"number":10}
     */
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

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

    public static class DataBean implements Serializable{
        /**
         * array : [{"address_id":1,"address_user":"都恒辰","address_phone":"18840518095","address_address":"东港市 五四小区 2-206","address_longitude":"123.876870274","address_latitude":"39.981217334184","full_name":"辽宁省 丹东市 东港市","user_id":1,"region_id":210681,"address_status":0},{"address_id":2,"address_user":"王小贱","address_phone":"13581522527","address_address":"和平小区 2栋408","address_longitude":"123.41433166046","address_latitude":"41.786474395792","full_name":"辽宁省 沈阳市 和平区","user_id":1,"region_id":210102,"address_status":1}]
         * total : 2
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

        public static class ArrayBean implements Serializable {
            /**
             * address_id : 1
             * address_user : 都恒辰
             * address_phone : 18840518095
             * address_address : 东港市 五四小区 2-206
             * address_longitude : 123.876870274
             * address_latitude : 39.981217334184
             * full_name : 辽宁省 丹东市 东港市
             * user_id : 1
             * region_id : 210681
             * address_status : 0
             */

            private int address_id;
            private String address_user;
            private String address_phone;
            private String address_address;
            private String address_longitude;
            private String address_latitude;
            private String full_name;
            private int user_id;
            private int region_id;
            private int address_status;

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getAddress_user() {
                return address_user;
            }

            public void setAddress_user(String address_user) {
                this.address_user = address_user;
            }

            public String getAddress_phone() {
                return address_phone;
            }

            public void setAddress_phone(String address_phone) {
                this.address_phone = address_phone;
            }

            public String getAddress_address() {
                return address_address;
            }

            public void setAddress_address(String address_address) {
                this.address_address = address_address;
            }

            public String getAddress_longitude() {
                return address_longitude;
            }

            public void setAddress_longitude(String address_longitude) {
                this.address_longitude = address_longitude;
            }

            public String getAddress_latitude() {
                return address_latitude;
            }

            public void setAddress_latitude(String address_latitude) {
                this.address_latitude = address_latitude;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getRegion_id() {
                return region_id;
            }

            public void setRegion_id(int region_id) {
                this.region_id = region_id;
            }

            public int getAddress_status() {
                return address_status;
            }

            public void setAddress_status(int address_status) {
                this.address_status = address_status;
            }
        }
    }
}
