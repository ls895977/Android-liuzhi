package com.hykj.liuzhi.androidcomponents.bean;

import com.google.gson.annotations.SerializedName;

public class AppPayBean {

    /**
     * code : 0
     * msg : 访问成功
     * wxpay : {"error":0,"msg":"授权成功","data":{"appid":"wx153551c2cce0e6a8","noncestr":"EJU5XZJ162V5Wwet7irfC5xtGI4QLBYK","package":"Sign=WXPay","partnerid":"1521926311","prepayid":"wx23150815925756ea0fde3a3c2393671654","timestamp":1548227295,"sign":"08CBBD03888690FEBD75D94CF8DDBF14"}}
     * alipay : {"error":0,"msg":"授权成功","str":"alipay_sdk=alipay-sdk-php-20180705&app_id=2018121062529203&biz_content=%7B%22body%22%3A%22%E9%83%BD%E6%81%92%E8%BE%B0_18840518095%22%2C%22subject%22%3A+%22%E9%83%BD%E6%81%92%E8%BE%B0_18840518095%22%2C%22out_trade_no%22%3A+%222018110816111248505510%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%224%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fliuzhi.365hy.com%2Fapi%2Findex%2Falipaycallback.html&sign_type=RSA2&timestamp=2019-01-23+15%3A03%3A56&version=1.0&sign=F84Hq%2FpJQbtYbOAnJMmmHcarzJwvI6ExMVZzckw84iH4I%2BA3psAwKjLNN6GbiItENYDI3IrS4x%2F%2Fyiy76w7UHLC2KzpNVgT3TSofwF5TjtQZ4Weok6xlMdeKm5L6K9sXNtUF3Kg3pHIbxrqyF0r1ztuBAnAJNk7Zp2Nc36Q%2B65vp3aj6mrvpKrhHECRsj5wJ1fAwUzeI%2BchOp8XJOqEABCUKSr2rt3pXTxdOgT2KKIORXVjDp9UxJ0vY3iAjSbawuqiB9HNd02cY7vFks67DjkkyXayI9nEJvGzUdbwy8jM8hW1YvjQvyY8FotgyzA29tI1%2BwDIiCREZTGRy6O7Ukw%3D%3D"}
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
    private WxpayBean wxpay;
    private AlipayBean alipay;

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

    public WxpayBean getWxpay() {
        return wxpay;
    }

    public void setWxpay(WxpayBean wxpay) {
        this.wxpay = wxpay;
    }

    public AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayBean alipay) {
        this.alipay = alipay;
    }

    public static class WxpayBean {
        /**
         * error : 0
         * msg : 授权成功
         * data : {"appid":"wx153551c2cce0e6a8","noncestr":"EJU5XZJ162V5Wwet7irfC5xtGI4QLBYK","package":"Sign=WXPay","partnerid":"1521926311","prepayid":"wx23150815925756ea0fde3a3c2393671654","timestamp":1548227295,"sign":"08CBBD03888690FEBD75D94CF8DDBF14"}
         */

        private int error;
        private String msg;
        private DataBean data;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * appid : wx153551c2cce0e6a8
             * noncestr : EJU5XZJ162V5Wwet7irfC5xtGI4QLBYK
             * package : Sign=WXPay
             * partnerid : 1521926311
             * prepayid : wx23150815925756ea0fde3a3c2393671654
             * timestamp : 1548227295
             * sign : 08CBBD03888690FEBD75D94CF8DDBF14
             */

            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private int timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }

    public static class AlipayBean {
        /**
         * error : 0
         * msg : 授权成功
         * str : alipay_sdk=alipay-sdk-php-20180705&app_id=2018121062529203&biz_content=%7B%22body%22%3A%22%E9%83%BD%E6%81%92%E8%BE%B0_18840518095%22%2C%22subject%22%3A+%22%E9%83%BD%E6%81%92%E8%BE%B0_18840518095%22%2C%22out_trade_no%22%3A+%222018110816111248505510%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%224%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fliuzhi.365hy.com%2Fapi%2Findex%2Falipaycallback.html&sign_type=RSA2&timestamp=2019-01-23+15%3A03%3A56&version=1.0&sign=F84Hq%2FpJQbtYbOAnJMmmHcarzJwvI6ExMVZzckw84iH4I%2BA3psAwKjLNN6GbiItENYDI3IrS4x%2F%2Fyiy76w7UHLC2KzpNVgT3TSofwF5TjtQZ4Weok6xlMdeKm5L6K9sXNtUF3Kg3pHIbxrqyF0r1ztuBAnAJNk7Zp2Nc36Q%2B65vp3aj6mrvpKrhHECRsj5wJ1fAwUzeI%2BchOp8XJOqEABCUKSr2rt3pXTxdOgT2KKIORXVjDp9UxJ0vY3iAjSbawuqiB9HNd02cY7vFks67DjkkyXayI9nEJvGzUdbwy8jM8hW1YvjQvyY8FotgyzA29tI1%2BwDIiCREZTGRy6O7Ukw%3D%3D
         */

        private int error;
        private String msg;
        private String str;

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

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
