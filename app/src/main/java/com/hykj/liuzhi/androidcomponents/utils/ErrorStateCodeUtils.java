package com.hykj.liuzhi.androidcomponents.utils;

import android.text.TextUtils;

/**
 * 错误验证码信息工具类
 *
 * @author YuChen
 * @date 2016-2-24 上午11:55:12
 */
public class ErrorStateCodeUtils {

    /**
     * 获取错误信息
     *
     * @param errorCode 错误代码
     * @return
     */
    public static String getLoginErrorMessage(String errorCode) {
        if (TextUtils.isEmpty(errorCode)) {
            return "出现未知异常，请重试";
        }
        switch (errorCode) {
            case "1":
                return "验证码错误";
            case "2":
                return "该手机号码尚未注册";
            default:
                return "出现未知异常"+errorCode+"，请重试";
        }
    }

    public static String getRegisterErrorMessage(String errorCode) {
        if (TextUtils.isEmpty(errorCode)) {
            return " ";
        }
        switch (errorCode) {
            case "1":
                return "验证码错误";
            case "2":
                return "该手机号码已被注册";
                case "3":
                return "注册失败";
            default:
                return " "+errorCode+"，请重试";
        }
    }
}
