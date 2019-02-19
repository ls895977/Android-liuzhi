package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

public class ViewLogisticsBean {

    /**
     * code : 0
     * msg : 查询成功
     * error : 0
     * data : {"message":"ok","nu":"73108995922259","ischeck":"1","condition":"F00","com":"zhongtong","logo":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1022514261,1855787563&fm=58","name":"中通速递","status":"200","state":"3","data":[{"time":"2019-01-21 15:46:37","ftime":"2019-01-21 15:46:37","context":"【杭州市】 已签收, 签收人凭取货码签收, 如有疑问请电联: 13362896346 / 4000633333,13362896346, 您的快递已经妥投, 如果您对我们的服务感到满意, 请给个五星好评, 鼓励一下我们【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2019-01-21 10:48:45","ftime":"2019-01-21 10:48:45","context":"【杭州市】 快件已送达【丰巢的UDC华联时代大厦(丰巢智能快递柜)】, 如有问题请电联（13362896346 / 4000633333,13362896346）, 感谢使用中通快递, 期待再次为您服务!"},{"time":"2019-01-20 07:39:58","ftime":"2019-01-20 07:39:58","context":"【杭州市】 快件已到达 【杭州景芳路】（18143448221、15381191820、18058777535）,业务员 郑梦明（13362896346） 正在第1次派件, 请保持电话畅通,并耐心等待"},{"time":"2019-01-19 23:30:25","ftime":"2019-01-19 23:30:25","context":"【嘉兴市】 快件离开 【杭州中转部】 已发往 【杭州景芳路】"},{"time":"2019-01-19 22:41:22","ftime":"2019-01-19 22:41:22","context":"【嘉兴市】 快件已经到达 【杭州中转部】"},{"time":"2019-01-19 17:39:42","ftime":"2019-01-19 17:39:42","context":"【杭州市】 快件离开 【杭州仓前】 已发往 【杭州中转部】"},{"time":"2019-01-18 19:57:00","ftime":"2019-01-18 19:57:00","context":"【杭州市】 【杭州仓前】（0571-86106326、0571-86260205、0571-86163605） 的 承包区-4 （18042452013） 已揽收"}]}
     */

    private int code;
    private String msg;
    private int error;
    private DataBeanX data;

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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * message : ok
         * nu : 73108995922259
         * ischeck : 1
         * condition : F00
         * com : zhongtong
         * logo : https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1022514261,1855787563&fm=58
         * name : 中通速递
         * status : 200
         * state : 3
         * data : [{"time":"2019-01-21 15:46:37","ftime":"2019-01-21 15:46:37","context":"【杭州市】 已签收, 签收人凭取货码签收, 如有疑问请电联: 13362896346 / 4000633333,13362896346, 您的快递已经妥投, 如果您对我们的服务感到满意, 请给个五星好评, 鼓励一下我们【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2019-01-21 10:48:45","ftime":"2019-01-21 10:48:45","context":"【杭州市】 快件已送达【丰巢的UDC华联时代大厦(丰巢智能快递柜)】, 如有问题请电联（13362896346 / 4000633333,13362896346）, 感谢使用中通快递, 期待再次为您服务!"},{"time":"2019-01-20 07:39:58","ftime":"2019-01-20 07:39:58","context":"【杭州市】 快件已到达 【杭州景芳路】（18143448221、15381191820、18058777535）,业务员 郑梦明（13362896346） 正在第1次派件, 请保持电话畅通,并耐心等待"},{"time":"2019-01-19 23:30:25","ftime":"2019-01-19 23:30:25","context":"【嘉兴市】 快件离开 【杭州中转部】 已发往 【杭州景芳路】"},{"time":"2019-01-19 22:41:22","ftime":"2019-01-19 22:41:22","context":"【嘉兴市】 快件已经到达 【杭州中转部】"},{"time":"2019-01-19 17:39:42","ftime":"2019-01-19 17:39:42","context":"【杭州市】 快件离开 【杭州仓前】 已发往 【杭州中转部】"},{"time":"2019-01-18 19:57:00","ftime":"2019-01-18 19:57:00","context":"【杭州市】 【杭州仓前】（0571-86106326、0571-86260205、0571-86163605） 的 承包区-4 （18042452013） 已揽收"}]
         */

        private String message;
        private String nu;
        private String ischeck;
        private String condition;
        private String com;
        private String logo;
        private String name;
        private String status;
        private String state;
        private List<DataBean> data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * time : 2019-01-21 15:46:37
             * ftime : 2019-01-21 15:46:37
             * context : 【杭州市】 已签收, 签收人凭取货码签收, 如有疑问请电联: 13362896346 / 4000633333,13362896346, 您的快递已经妥投, 如果您对我们的服务感到满意, 请给个五星好评, 鼓励一下我们【请在评价快递员处帮忙点亮五颗星星哦~】
             */

            private String time;
            private String ftime;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
