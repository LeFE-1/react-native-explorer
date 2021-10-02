package com.example.C_7_1;

import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by 付晓龙
 * on 2020-01-06
 */
public class MCRNStorageModule extends ReactContextBaseJavaModule {
    private Map<String, String> map = new HashMap<>();

    public MCRNStorageModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    // JS端可以用的模块名称
    @Nonnull
    @Override
    public String getName() {
        return "MCRNStorage";
    }

    @ReactMethod
    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put("COMPONENT_DID_APPEAR", MCEventEmitter.ComponentDidAppear);
        constants.put("COMPONENT_DID_DISAPPEAR", MCEventEmitter.ComponentDidDisappear);
        return constants;
    }

    @ReactMethod
    public void clearAllData() {
        Toast.makeText(getCurrentActivity(), "数据清理完毕", Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void setItem(String key, String value) {
        map.put(key, value);
    }

    /**
     * Callback回调
     */
    @ReactMethod
    public void getItem(String key, Callback callback) {
        Log.e("mc", "getItem: ---->callback");
        if (map.containsKey(key)) {
            callback.invoke(map.get(key), map.get(key));
        } else {
            callback.invoke(null, "数据不存在1");
        }
    }

    @ReactMethod
    public void getItem(String key, Promise promise) {
        Log.e("mc", "getItem: --------->promise");
        if (map.containsKey(key)) {
            promise.resolve(map.get(key));
        } else {
            promise.resolve("数据不存在");
        }
    }

    @ReactMethod
    public void saveMsg(ReadableArray readableArray, ReadableMap readableMap, Callback callback) {
        ArrayList<Object> arrayList = readableArray.toArrayList();
        HashMap<String, Object> map = readableMap.toHashMap();
        WritableArray writableArray = Arguments.createArray();
        for (Object object : arrayList) {
            writableArray.pushString(object + "");
        }
        WritableMap writableMap = Arguments.createMap();
        for (String key : map.keySet()) {
            writableMap.putString(key, map.get(key) + "");
        }
        callback.invoke(writableArray, writableMap);
    }
}
