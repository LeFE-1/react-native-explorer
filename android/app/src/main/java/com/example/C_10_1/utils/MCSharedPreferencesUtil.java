package com.example.C_10_1.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public class MCSharedPreferencesUtil {
    private static final String MC_HOTFIX_PATH = "MC_AMP_HOTFIX_PATH";
    private static final String MCAMP_SP_TAG = "mc_amp_sp_tag";

    private static SharedPreferences sp;

    private static SharedPreferences getSp(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(MCAMP_SP_TAG, Context.MODE_PRIVATE);
        }
        return sp;
    }

    public static void setMCHotfixPath(Context context, String path) {
        long versionCode = MCAppUtils.getAppVersionCode(context);
        SharedPreferences preferences = getSp(context);
        //存入数据
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MC_HOTFIX_PATH + versionCode, path);
        editor.apply();
    }

    /**
     * 支持业务方根据自定义参数来保存热更新路径，自定义参数可以定义stage,test,product等等参数来实现不同环境加载不同的bundle
     */
    public static void setMCHotfixPath(Context context, String path, String params) {
        long versionCode = MCAppUtils.getAppVersionCode(context);
        SharedPreferences preferences = getSp(context);
        //存入数据
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MC_HOTFIX_PATH + params + versionCode, path);
        editor.apply();
    }

    public static String getMCHotfixPath(Context context) {
        SharedPreferences preferences = getSp(context);
        long versionCode = MCAppUtils.getAppVersionCode(context);
        return preferences.getString(MC_HOTFIX_PATH + versionCode, "");
    }

    /**
     * 根据自定义参数获取jsBundle路径
     */
    public static String getMCHotfixPath(Context context, String params) {
        SharedPreferences preferences = getSp(context);
        long versionCode = MCAppUtils.getAppVersionCode(context);
        return preferences.getString(MC_HOTFIX_PATH + params + versionCode, "");
    }
}
