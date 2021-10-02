package com.example;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by yangpan
 * on 2019-12-30
 * <p>
 * 路由管理类，注册 RN 示例页面和原生示例页面，提供统一的接口用于展示
 */
public class RouterManager {
    private static final String TAG = "RouterManager";

    private static RouterManager sRouterManager;
    private Application mApp;
    private LinkedHashMap<String, String> mRoutes;

    private RouterManager() {
    }

    public static RouterManager getInstance() {
        if (sRouterManager == null) {
            synchronized (RouterManager.class) {
                if (sRouterManager == null) {
                    sRouterManager = new RouterManager();
                }
            }
        }
        return sRouterManager;
    }

    public void init(Application application) {
        mApp = application;
    }

    /**
     * 此处维护一个路由表
     * key 值为要显示在 MainActivity 的页面名称
     * value 值为要跳转的页面，RN 页面与 AppRegistry 注册的名称一致，原生示例页面则需要注册类名
     *
     * @return 路由表
     */
    private LinkedHashMap getRoutes() {
        if (mRoutes != null) return mRoutes;
        mRoutes = new LinkedHashMap<>();
        mRoutes.put("第二章 局部渲染1", "2_2_1");
        mRoutes.put("第二章 局部渲染2", "com.example.C_2_2_2.Activity");
        mRoutes.put("第二章 ReactRootView", "2_3_1");
        mRoutes.put("第二章 视图长度单位", "2_3_3");
        mRoutes.put("第二章 Flex布局", "2_4_1");
        mRoutes.put("第二章 绝对定位", "com.example.C_2_4_2.Activity");
        mRoutes.put("第三章 Text解析", "3_1");
        mRoutes.put("第三章 行间距", "3_1_2");
        mRoutes.put("第三章 Text的嵌套", "3_2_1");
        mRoutes.put("第三章 同行多字号的对齐方式", "3_2_2");
        mRoutes.put("第三章 文本输入--TextInput", "3_3");
        mRoutes.put("第三章 软键盘", "3_4_1");
        mRoutes.put("第四章 触摸事件", "4_1");
        mRoutes.put("第四章 触摸组件", "4_2");
        mRoutes.put("第四章 响应者生命周期", "4_3_1");
        mRoutes.put("第四章 PanResponder", "4_3_2");
        mRoutes.put("第五章 Image解析", "5_1");
        mRoutes.put("第五章 Image resizeMode属性", "5_1_1");
        mRoutes.put("第五章 react-native-fast-image", "5_1_3");
        mRoutes.put("第五章 Android音频操作", "com.example.C_5_2_1.AudioRecordActivity");
        mRoutes.put("第五章 react-native-audio-toolkit", "5_2_1");
        mRoutes.put("第五章 Android视频操作", "com.example.C_5_2_2.VideoRecordActivity");
        mRoutes.put("第五章 react-native-video", "5_2_2");
        mRoutes.put("第五章 react-native-fs", "5_3_3");
        mRoutes.put("第五章 Android本地存储方式", "com.example.C_5_4_2.Activity");
        mRoutes.put("第五章 AsyncStorage", "5_5_1");
        mRoutes.put("第六章 布局动画——LayoutAnimation", "6_1_1");
        mRoutes.put("第六章 自定义布局动画", "6_1_1_custom");
        mRoutes.put("第六章 交互动画——Animated", "6_2_1");
        mRoutes.put("第六章 动画中断及重置", "6_2_2");
        mRoutes.put("第六章 顺序动画", "6_2_2_sequence");
        mRoutes.put("第六章 动画值的运算与变化", "6_2_2_operation");
        mRoutes.put("第六章 常见优化手段", "6_3_2");
        mRoutes.put("第七章 JS调用原生方法", "7_1");
        mRoutes.put("第七章 多线程", "com.example.C_7_1.DownloadActivity");
        mRoutes.put("第七章 广播", "com.example.C_7_1.MCBroadcastActivity");
        mRoutes.put("第七章 Java 与 JS 通信", "com.example.C_7_2_2.TestBridgeActivity");
        mRoutes.put("第八章 自定义原生UI组件", "8_2");
        mRoutes.put("第九章 DrawerLayout", "com.example.C_9_1.DrawerLayoutActivity");
        mRoutes.put("第九章 React Navigation","com.example.C_9_2_1.ReactNavigationActivity");
        mRoutes.put("第九章 React Native Navigation","com.example.C_9_3_1.ReactNativeNavigationActivity");
        mRoutes.put("第十一章 视图平铺", "11_1");
        mRoutes.put("第十一章 视图嵌套", "11_2");
        mRoutes.put("第十一章 视图预加载", "com.example.C_11_2.PreloadActivity");
        mRoutes.put("第十一章 长列表优化", "11_3");
        return mRoutes;
    }

    ArrayList<String> getRoutesList() {
        return new ArrayList<String>(getRoutes().keySet());
    }

    public void startPage(String key) {
        String value = (String) getRoutes().get(key);
        if (value == null || mApp == null) {
            Log.e(TAG, "startPage: 路由表中尚未注册此页面或未初始化！！！");
            return;
        }
        if (value.contains("com")) { // 原生示例 Activity
            if ("com.example.C_9_3_1.ReactNativeNavigationActivity".equals(value)) {
                if (mApp instanceof MainApplication) {
                    Toast.makeText(mApp, "请将 Manifest 文件中的 application 标签切换为 ReactNativeNavigationApplication 后运行", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            try {
                Class activityClass = Class.forName(value);
                Intent intent = new Intent(mApp, activityClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mApp.startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else { // RN 示例页面
            if ("11_1".equals(value) || "11_2".equals(value)) {
                Bundle initProps = new Bundle();
                initProps.putLong("startTime", System.currentTimeMillis());
                RNExampleActivity.startInstance(mApp, value, initProps);
                return;
            }
            RNExampleActivity.startInstance(mApp, value);
        }
    }

}
