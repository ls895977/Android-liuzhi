package com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean;

import java.util.List;

public class ShopHomeBean {

    /**
     * code : 0
     * msg : 访问成功
     * data : {"array":[{"goods_id":1,"goods_name":"商品4","goods_labels":"1,2,3","goods_price":"10","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg","goods_stock":34,"goods_volume":0,"labelname":["90后","拖延症","最帅的"]},{"goods_id":2,"goods_name":"商品5","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/firstpic/35225e583b28d2a01825f540b064d738.jpg","goods_stock":99,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":3,"goods_name":"商品6","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods3/firstpic/51d893f2eec96f0dd4545a6564ee33f1.jpg","goods_stock":99,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":4,"goods_name":"商品7","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods4/firstpic/c5fed4d8313fe17df9645f68b3a914a3.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":5,"goods_name":"商品41","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods5/firstpic/a48f17a140768e94b403c0b7ae17eb13.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":6,"goods_name":"商品42","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods6/firstpic/e55470102748bad05413a753be2edc26.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":7,"goods_name":"商品43","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods7/firstpic/ad6f7606213090fca39642fde2bbd341.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":8,"goods_name":"商品444","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods8/firstpic/dbb969d47bc3d490dbfef970c3ca4821.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":9,"goods_name":"商品4221q","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods9/firstpic/9e4e197bc011d4b7ae9dc35d348391b1.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":10,"goods_name":"商品411","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods10/firstpic/7e7cf1b886faf29aa8f2df1d341db034.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]}],"total":14,"amount":2,"page":"1","number":"10"}
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
         * array : [{"goods_id":1,"goods_name":"商品4","goods_labels":"1,2,3","goods_price":"10","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg","goods_stock":34,"goods_volume":0,"labelname":["90后","拖延症","最帅的"]},{"goods_id":2,"goods_name":"商品5","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods2/firstpic/35225e583b28d2a01825f540b064d738.jpg","goods_stock":99,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":3,"goods_name":"商品6","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods3/firstpic/51d893f2eec96f0dd4545a6564ee33f1.jpg","goods_stock":99,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":4,"goods_name":"商品7","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods4/firstpic/c5fed4d8313fe17df9645f68b3a914a3.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":5,"goods_name":"商品41","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods5/firstpic/a48f17a140768e94b403c0b7ae17eb13.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":6,"goods_name":"商品42","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods6/firstpic/e55470102748bad05413a753be2edc26.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":7,"goods_name":"商品43","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods7/firstpic/ad6f7606213090fca39642fde2bbd341.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":8,"goods_name":"商品444","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods8/firstpic/dbb969d47bc3d490dbfef970c3ca4821.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":9,"goods_name":"商品4221q","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods9/firstpic/9e4e197bc011d4b7ae9dc35d348391b1.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]},{"goods_id":10,"goods_name":"商品411","goods_labels":"1,2,3","goods_price":"1","goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods10/firstpic/7e7cf1b886faf29aa8f2df1d341db034.jpg","goods_stock":100,"goods_volume":0,"labelname":["90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的","90后","拖延症","最帅的"]}]
         * total : 14
         * amount : 2
         * page : 1
         * number : 10
         */

        private int total;
        private int amount;
        private String page;
        private String number;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
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
             * goods_id : 1
             * goods_name : 商品4
             * goods_labels : 1,2,3
             * goods_price : 10
             * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg
             * goods_stock : 34
             * goods_volume : 0
             * labelname : ["90后","拖延症","最帅的"]
             */

            private int goods_id;
            private String goods_name;
            private String goods_labels;
            private String goods_price;
            private String goods_pic;
            private int goods_stock;
            private int goods_volume;
            private List<String> labelname;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_labels() {
                return goods_labels;
            }

            public void setGoods_labels(String goods_labels) {
                this.goods_labels = goods_labels;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_pic() {
                return goods_pic;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public int getGoods_stock() {
                return goods_stock;
            }

            public void setGoods_stock(int goods_stock) {
                this.goods_stock = goods_stock;
            }

            public int getGoods_volume() {
                return goods_volume;
            }

            public void setGoods_volume(int goods_volume) {
                this.goods_volume = goods_volume;
            }

            public List<String> getLabelname() {
                return labelname;
            }

            public void setLabelname(List<String> labelname) {
                this.labelname = labelname;
            }
        }
    }
}
