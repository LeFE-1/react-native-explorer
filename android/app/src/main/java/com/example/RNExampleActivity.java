package com.example;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

import javax.annotation.Nullable;

/**
 * Created by yangpan
 * on 2019-10-17
 */
public class RNExampleActivity extends ReactActivity {
    private static String mMainComponentName;
    private static Bundle mInitProps;

    /**
     * 跳转至本页面的方法
     *
     * @param from              跳转来源的上下文对象
     * @param mainComponentName 在 JS 端 AppRegistry 中注册的 MainComponent 名称
     */
    public static void startInstance(Context from, String mainComponentName) {
        Intent intent = new Intent(from, RNExampleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mMainComponentName = mainComponentName;
        from.startActivity(intent);
    }

    /**
     * 跳转至本页面的方法
     *
     * @param from              跳转来源的上下文对象
     * @param mainComponentName 在 JS 端 AppRegistry 中注册的 MainComponent 名称
     * @param initProps         跳转至本页面要传递的数据
     */
    public static void startInstance(Context from, String mainComponentName, Bundle initProps) {
        mInitProps = initProps;
        startInstance(from, mainComponentName);
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Nullable
            @Override
            protected Bundle getLaunchOptions() {
                return mInitProps == null ? new Bundle() : mInitProps;
            }
        };
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return mMainComponentName;
    }
}
