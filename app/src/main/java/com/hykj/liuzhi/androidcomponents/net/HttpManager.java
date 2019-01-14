package com.hykj.liuzhi.androidcomponents.net;

import com.zhouyou.http.request.GetRequest;
import com.zhouyou.http.request.PostRequest;

/**
 * @author: lujialei
 * @date: 2018/9/29
 * @describe:
 */

public class HttpManager {

    /**
     * get请求
     */
    public static GetRequest get(String url) {
        return new CustomGetRequest(url);
    }

    /**
     * post请求
     */
    public static PostRequest post(String url) {
        return new CustomPostRequest(url);
    }


    /**
     * 获取获取轮播图 type 1为首页显示 2为纹理显示 3为商城显示
     */
    public static final String GET_SOWING = "/api/index/getsowing";

}
