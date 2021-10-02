package com.example.C_10_1.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * 获取热更新JsBundle文件地址
 */

public class MCJSBundleUtils {
    private static volatile MCJSBundleUtils sInstance;
    private static final String TAG = MCJSBundleUtils.class.getSimpleName();

    public static MCJSBundleUtils getInstance() {
        if (sInstance == null) {
            synchronized (MCJSBundleUtils.class) {
                if (sInstance == null) {
                    sInstance = new MCJSBundleUtils();
                }
            }

        }
        return sInstance;
    }

    /**
     * 获取jsBundle路径地址
     */
    public String getJSBundleFile(Context context) {
        String jsBundleFile = MCSharedPreferencesUtil.getMCHotfixPath(context);
        File file = new File(jsBundleFile);
        MCLogUtils.e(TAG, "getJSBundleFile: " + jsBundleFile + "该文件是否存在======>" + file.exists());
        return file.exists() ? jsBundleFile : null;
    }

    /**
     * 支持根据自定义参数获取jsbundle路径
     */
    public String getJSBundleFile(Context context, String params) {
        String jsBundleFile = MCSharedPreferencesUtil.getMCHotfixPath(context, params);
        File file = new File(jsBundleFile);
        MCLogUtils.e(TAG, "getJSBundleFile: " + jsBundleFile + "该文件是否存在======>" + file.exists());
        return file.exists() ? jsBundleFile : null;
    }
}
