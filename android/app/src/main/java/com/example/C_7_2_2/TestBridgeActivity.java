package com.example.C_7_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.MainApplication;
import com.example.R;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactContext;

public class TestBridgeActivity extends AppCompatActivity {
    private static final String TAG = "TestBridgeActivity";

    // 继承 JavaScriptModule 接口，名称与 JS 端注册的 JS 模块名称保持一致
    private interface CustomJSModule extends JavaScriptModule {
        // 定义抽象方法，具体实现在 JS 端
        void echo(String strFromJava);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bridge);
        // 如果 RN 还没有初始化，那么先初始化一下 RN
        if (!getReactNativeHost().getReactInstanceManager().hasStartedCreatingInitialContext()) {
            getReactNativeHost().getReactInstanceManager().createReactContextInBackground();
        }
        // 定义按钮的点击事件
        findViewById(R.id.btn_java_call_js_method).setOnClickListener(v -> {
            ReactContext currentReactContext = getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
            if (currentReactContext == null) {
                Log.e(TAG, "onCreate: currentReactContext 上下文对象为空!");
                return;
            }
            // 通过CatalystInstance 的 getJSModule 方法获取 JS 模块实例，完成 JS 方法的调用
            currentReactContext.getCatalystInstance().getJSModule(CustomJSModule.class).echo("Msg from Java");
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getReactNativeHost() != null) {
            getReactNativeHost().getReactInstanceManager().onHostResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getReactNativeHost() != null) {
            getReactNativeHost().getReactInstanceManager().onHostPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getReactNativeHost() != null) {
            getReactNativeHost().getReactInstanceManager().onHostDestroy(this);
        }
    }

    private ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getApplication()).getReactNativeHost();
    }


}
