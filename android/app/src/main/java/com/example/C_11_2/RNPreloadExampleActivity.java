package com.example.C_11_2;

import android.content.Context;
import android.content.Intent;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

/**
 * Created by yangpan
 * on 2020-02-03
 */
public class RNPreloadExampleActivity extends ReactActivity {
    private static String mMainComponentName;

    public static void startInstance(Context from, String mainComponentName) {
        Intent intent = new Intent(from, RNPreloadExampleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mMainComponentName = mainComponentName;
        from.startActivity(intent);
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new PreloadDelegate(this, getMainComponentName());
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
