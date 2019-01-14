package com.hykj.liuzhi.androidcomponents.net;

import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.CallClazzProxy;
import com.zhouyou.http.request.PostRequest;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author: lujialei
 * @date: 2018/9/29
 * @describe:
 */


public class CustomPostRequest extends PostRequest {
    public CustomPostRequest(String url) {
        super(url);
    }

    @Override
    public <T> Observable<T> execute(Type type) {
        return super.execute(new CallClazzProxy<CustomApiResult<T>, T>(type) {
        });
    }

    @Override
    public <T> Observable<T> execute(Class<T> clazz) {
        return super.execute(new CallClazzProxy<CustomApiResult<T>, T>(clazz) {
        });
    }

    @Override
    public <T> Disposable execute(CallBack<T> callBack) {
        return super.execute(new CallBackProxy<CustomApiResult<T>, T>(callBack) {
        });
    }
}
