package com.example.C_10_1.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * GSON工具类
 */
public class GsonUtil {
    private static Gson gson_instance;
    private static String TAG = "GsonUtil";

    private static void checkGson() {
        if (gson_instance == null) {
            gson_instance = new Gson();
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        T ret = null;
        checkGson();

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            ret = gson_instance.fromJson(json, clazz);
        } catch (Throwable e) {
            //   Timber.e(e);
            MCLogUtils.e(TAG, e.toString());
        }

        return ret;
    }

    /**
     * 将对象转为Json字符串
     *
     * @param obj 目标对象
     * @return Json字符串
     */
    public static <T> String toJson(T obj) {
        String ret = "";
        checkGson();

        if (obj == null) {
            return null;
        }

        try {
            ret = gson_instance.toJson(obj);
        } catch (Throwable e) {
            MCLogUtils.e(TAG, e.toString());
        }

        return ret;
    }

}
