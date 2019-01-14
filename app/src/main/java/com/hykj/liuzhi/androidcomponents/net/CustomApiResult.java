package com.hykj.liuzhi.androidcomponents.net;

import com.zhouyou.http.model.ApiResult;

/**
 * @author: lujialei
 * @date: 2018/9/29
 * @describe:
 */


public class CustomApiResult<T> extends ApiResult<T> {

   @Override
    public boolean isOk() {
        return getCode()==0;//如果不是0表示成功，请重写isOk()方法。
    }

    @Override
    public T getData() {
        return super.getData();
    }
}


