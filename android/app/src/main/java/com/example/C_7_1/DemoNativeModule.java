package com.example.C_7_1;

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
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright (c) 2019年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.
 * Version:
 * Author:  付晓龙
 * Date:    2019-11-08
 * Desc:
 * Edit History:
 */
public class DemoNativeModule extends ReactContextBaseJavaModule {
    private static final String RCTDeviceEventEmitter = "RCTDeviceEventEmitter";
    private ReactApplicationContext reactApplicationContext;

    public DemoNativeModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        reactApplicationContext = reactContext;
    }

    @Nonnull
    @Override
    public String getName() {
        return "DemoNativeModule";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> map = new HashMap<>();
        map.put("RCTDeviceEventEmitter", RCTDeviceEventEmitter);
        return map;
    }

    /**
     * 无参无返回值方法
     */
    @ReactMethod
    public void exampleMethod() {
        Toast.makeText(getCurrentActivity(), "Call From JS", Toast.LENGTH_SHORT).show();
        System.out.println("Call From JS");
    }

    /**
     * 有参数无返回值方法
     *
     * @param foo 参数一
     * @param bar 参数二
     */
    @ReactMethod
    public void exampleMethod(String foo, int bar) {
        Toast.makeText(getCurrentActivity(), String.format("Argument from JS :%s%s", foo, bar), Toast.LENGTH_SHORT).show();
        System.out.println(String.format("Argument from JS :%s%s", foo, bar));
    }

    /**
     * Callback回调
     *
     * @param callback Callback
     */
    @ReactMethod
    public void exampleCallBackMethod(Callback callback) {
        WritableArray writableArray = Arguments.createArray();
        writableArray.pushString("error msg");
        callback.invoke(writableArray, "result");
    }

    /**
     * Promise回调
     *
     * @param msg     ReactNative传递过来的数据
     * @param promise Promise
     */
    @ReactMethod
    public void exampleMethod(String msg, Promise promise) {
        //业务处理逻辑
        String result = "Android处理结果" + msg;
        Toast.makeText(getCurrentActivity(), result, Toast.LENGTH_SHORT).show();
        //回调ReactNative,将处理结果返回ReactNative
        promise.resolve(result);
    }

    /**
     * @param readableArray 对应React Native的Array
     * @param readableMap   对应React Native的Object
     * @param callback      对应React Native的function
     */
    @ReactMethod
    public void exampleMethod(ReadableArray readableArray, ReadableMap readableMap, Callback callback) {
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

    /**
     * 发送事件到ReactNative
     */
    @ReactMethod
    public void exampleSendMsgToRn() {
        sendMsgToRN();
    }

    /**
     * 发送事件到ReactNative
     */
    public void sendMsgToRN() {
        reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(RCTDeviceEventEmitter, "receive msg from native");
    }
}
