package com.hykj.liuzhi.androidcomponents.bean;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/11/11
 * @describe:
 */


public class BannerBean {


    /**
     * array : [{"sowing_url":"123.jpg"}]
     * total : 1
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
         * sowing_url : 123.jpg
         */

        private String sowing_url;

        public String getSowing_url() {
            return sowing_url;
        }

        public void setSowing_url(String sowing_url) {
            this.sowing_url = sowing_url;
        }
    }
}
