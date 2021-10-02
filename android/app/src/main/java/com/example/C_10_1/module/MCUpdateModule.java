package com.example.C_10_1.module;

import android.content.Intent;
import android.text.TextUtils;

import com.example.C_10_1.MCUpdate;
import com.example.C_10_1.bean.MCAppHotFixBean;
import com.example.C_10_1.bean.MCUpdateResponseBean;
import com.example.C_10_1.global.MCGlobal;
import com.example.C_10_1.interf.MCDownloadListener;
import com.example.C_10_1.interf.MCUpdateResponseListener;
import com.example.C_10_1.utils.MCAppUtils;
import com.example.C_10_1.utils.MCLogUtils;
import com.example.C_10_1.utils.MCSharedPreferencesUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public class MCUpdateModule extends ReactContextBaseJavaModule {
    private static final String TAG = MCUpdateModule.class.getSimpleName();
    private MCAppHotFixBean hotFixBean = null;

    public MCUpdateModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "MCRNAmp";
    }

    /**
     * 检测版本更新
     */
    @ReactMethod
    public void checkUpgrade(String ampToken, String env, String jsVersion, ReadableMap map, Promise promise) {
        ReadableNativeMap map2 = (ReadableNativeMap) map;
        HashMap<String, Object> map1 = map2.toHashMap();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", ampToken);
            jsonObject.put("build_env", env);
            jsonObject.put("device_id", "123");
            jsonObject.put("build_id", MCAppUtils.getAppVersionCode(getCurrentActivity()));
            jsonObject.put("upgrade_type", 1);
            jsonObject.put("hotfix_build_id", jsVersion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            try {
                jsonObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MCLogUtils.e("========>json" + jsonObject.toString());

        MCUpdateResponseListener listener = new MCUpdateResponseListener() {
            @Override
            public void onSuccess(MCUpdateResponseBean.DataBean bean) {
                checkAppUpdate(bean, promise);
            }

            @Override
            public void onError(String msg) {
                promise.reject("error", msg);
            }
        };
        MCUpdate.getInstance().checkUpdate(getCurrentActivity(), jsonObject, listener);

    }

    /**
     * 检测版本更新
     */
    private void checkAppUpdate(MCUpdateResponseBean.DataBean bean, Promise promise) {
        WritableMap writableMap = Arguments.createMap();
        if (bean != null) {
            hotFixBean = bean.hotfix;
            if (!TextUtils.isEmpty(hotFixBean.getUrl())) {
                writableMap.putBoolean("needUpgrade", true);
                writableMap.putInt("upgradeMode", MCGlobal.MCRNAmpUpgradeModeTypeHotfix);//热修复
                writableMap.putInt("upgradeType", MCGlobal.MCAmpUpgradeTypeNone);
                WritableMap info = Arguments.createMap();
                info.putString("hotfix_md5", hotFixBean.getHotfix_md5());
                info.putString("url", hotFixBean.getUrl());
                writableMap.putMap("upgradeInfo", info);
                MCLogUtils.e(TAG, "checkAppUpdate: ======>热更新");
                promise.resolve(writableMap);
            } else {
                writableMap.putBoolean("needUpgrade", false);
                writableMap.putInt("upgradeMode", MCGlobal.MCRNAmpUpgradeModeTypeNone);
                writableMap.putInt("upgradeType", MCGlobal.MCAmpUpgradeTypeNone);
                MCLogUtils.e(TAG, "checkAppUpdate: ======>其他");

                promise.resolve(writableMap);
            }

        }
    }

    //------------------------------------热更新------------------------------------

    /**
     * 下载热更新zip包
     */
    @ReactMethod
    public void downloadBundle(String url, String md5, final Promise promise) {
        MCLogUtils.e(TAG, "downloadBundle: " + url + "====>md5" + md5);
        if (TextUtils.isEmpty(url)) {
            promise.reject("error", "url is null, please check url");
            return;
        }
        MCDownloadListener listener = new MCDownloadListener() {

            @Override
            public void onSuccess(String path) {
                MCLogUtils.e(TAG, "MCDownloadListener onSuccess: =======>" + path);
                WritableMap map = Arguments.createMap();
                map.putString("hotfixURL", path);
                try {
                    promise.resolve(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                MCLogUtils.e(TAG, "MCDownloadListener onError: ==========>" + error);
                try {
                    promise.reject("error", error);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        MCUpdate.getInstance().downloadFile(getCurrentActivity(), 2, url, md5, listener);

    }

    /**
     * 通知Native热更新路径
     */
    @ReactMethod
    public void beginHotfix(String hotfixURL) {
        MCLogUtils.e(TAG, "beginHotfix: ===>" + hotfixURL);
        MCSharedPreferencesUtil.setMCHotfixPath(getCurrentActivity(), hotfixURL);
        Intent intent = new Intent();
        intent.setAction("McHotFixUpdateSuccess");
        intent.putExtra("url", hotfixURL);
        getCurrentActivity().sendBroadcast(intent);
    }

    //------------------------------------热更新结束------------------------------------

}
