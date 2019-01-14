package com.hykj.liuzhi.androidcomponents.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.hykj.liuzhi.androidcomponents.MyApplication;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.bean.User;
import com.hykj.liuzhi.androidcomponents.bean.UserData;
import com.hykj.liuzhi.androidcomponents.bean.UserInfo;
import com.orhanobut.logger.Logger;

public class LocalInfoUtils {

    public static void saveUserInfo(String phone, String code, String password) {
        Context context = MyApplication.getAppContext();
        if (context != null) {
            final SharedPreferences preferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("phone", phone);
            editor.putString("code", code);
            editor.putString("password", password);
            editor.apply();
        }
    }


    public static UserInfo getUserInfo() {
        Context context = MyApplication.getAppContext();
        UserInfo userInfo = new UserInfo();
        if (context != null) {
            final SharedPreferences preferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            userInfo.setPhone(preferences.getString("phone", ""));
            userInfo.setCode(preferences.getString("code", ""));
            userInfo.setPassword(preferences.getString("password", ""));
        }
        return userInfo;
    }

    public static void saveUserdata(String user_data) {
        Context context = MyApplication.getAppContext();
        if (context != null) {
            final SharedPreferences preferences = context.getSharedPreferences("Userdata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user_data", user_data);
            editor.apply();
        }
    }

    public static String getUserdata() {
        Context context = MyApplication.getAppContext();
        String str = "";
        if (context != null) {
            final SharedPreferences preferences = context.getSharedPreferences("Userdata", Context.MODE_PRIVATE);
            str = preferences.getString("user_data", "");
        }
        return str;
    }

    public static int getUserId() {
        String userdata = LocalInfoUtils.getUserdata();
        int userid = 0;
        if (!TextUtils.isEmpty(userdata)) {
            LoginEntity person = FastJSONHelper.getPerson(userdata, LoginEntity.class);
            if (person != null) {
                userid = person.getUserdata().getUser_id();
            }
        }

        return userid;
    }

    public static void saveUserself(String user_data) {
        Context context = MyApplication.getAppContext();
        if (context != null) {
            Gson gson = new Gson();
            UserData bean = gson.fromJson(user_data, UserData.class);
            ACache aCache = ACache.get(context);
            aCache.put("user_data", user_data);
            aCache.put("user_id", String.valueOf(bean.getData().getUser_id()));
            aCache.put("user_barth", String.valueOf(bean.getData().getUser_barth()));
            aCache.put("user_sex", String.valueOf(bean.getData().getUser_sex()));
            aCache.put("user_label", String.valueOf(bean.getData().getUser_label()));
            aCache.put("user_mail", String.valueOf(bean.getData().getUser_mail()));
            aCache.put("user_phone", String.valueOf(bean.getData().getUser_phone()));
            aCache.put("user_autograph", String.valueOf(bean.getData().getUser_autograph()));
            aCache.put("user_nickname", String.valueOf(bean.getData().getUser_nickname()));
            aCache.put("user_integral", String.valueOf(bean.getData().getUser_integral()));
            aCache.put("user_pic", String.valueOf(bean.getData().getUser_pic()));
            aCache.put("user_follow", String.valueOf(bean.getData().getUser_follow()));
            aCache.put("user_fans", String.valueOf(bean.getData().getUser_fans()));
            aCache.put("user_collection", String.valueOf(bean.getData().getUser_collection()));
        }
    }

    public static String getUserself(String key) {
        Context context = MyApplication.getAppContext();
        String str = "";
        if (context != null) {
            ACache aCache = ACache.get(context);
            str = aCache.getAsString(key);
        }
        return str;
    }
}
