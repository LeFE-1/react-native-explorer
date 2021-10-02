package com.example.C_7_2_2;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

/**
 * Created by yangpan
 * on 2020-03-09
 */
public class CustomJavaModule extends ReactContextBaseJavaModule {

    public CustomJavaModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "CustomJavaModule";
    }

    @ReactMethod
    public void handleJSReturnValue(String returnValue) {
        if (getCurrentActivity() == null) return;
        getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getReactApplicationContext(), returnValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
