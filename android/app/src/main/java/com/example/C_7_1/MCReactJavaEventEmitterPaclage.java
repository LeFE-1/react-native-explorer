package com.example.C_7_1;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public class MCReactJavaEventEmitterPaclage implements ReactPackage {
    @Nonnull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        List<NativeModule> list = new ArrayList<>(1);
        // 将原生模块实例化，放置到数组中
        list.add(new MCReactJavaEventEmitterModule(reactContext));
        return list;

    }

    // 可声明自定义UI组件，同样放置到数组中，本章暂不详解，故设置为空
    @Nonnull
    @Override
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
        return Collections.EMPTY_LIST;
    }
}
