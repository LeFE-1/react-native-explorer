package com.example.C_7_1;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Copyright (c) 2019年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.
 * Version:
 * Author:  付晓龙
 * Date:    2019-11-08
 * Desc:
 * Edit History:
 */

public class NativeReactPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> list = new ArrayList<>(1);
        list.add(new DemoNativeModule(reactContext));
        return list;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.EMPTY_LIST;
    }

}
