package com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean;

import java.io.Serializable;
import java.util.List;

public class GoodDetailDetailBean implements Serializable {
    /**
     * code : 0
     * msg : 访问成功
     * data : {"gid":2,"name":"商品5","volume":0,"price":"1","integral":20,"detail":"qwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsd","usercollection":0,"image":["http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/detailpic/ffdfbef2147ae4805f21ae5ac545e5f0.jpg","http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/detailpic/21f2a4e9aa6e18ac3a635498cf490dbd.jpg","http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/detailpic/fd1962f25667755236855b56d497163f.jpg"]}
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

    public static class DataBean implements Serializable{
        /**
         * gid : 2
         * name : 商品5
         * volume : 0
         * price : 1
         * integral : 20
         * detail : qwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsdqwe123123123qweqefjasfhikanvjdflasxcvNJADNCXCAsd
         * usercollection : 0
         * image : ["http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/detailpic/ffdfbef2147ae4805f21ae5ac545e5f0.jpg","http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/detailpic/21f2a4e9aa6e18ac3a635498cf490dbd.jpg","http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/detailpic/fd1962f25667755236855b56d497163f.jpg"]
         */
        private int gid;
        private String name;
        private int volume;
        private String price;
        private int integral;
        private String detail;
        private int usercollection;
        private List<String> image;

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getUsercollection() {
            return usercollection;
        }

        public void setUsercollection(int usercollection) {
            this.usercollection = usercollection;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }
}
