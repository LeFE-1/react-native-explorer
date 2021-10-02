package com.example.C_9_2_1;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import javax.annotation.Nullable;

/**
 * Created by 付晓龙
 * on 2020-03-03
 */
public class ReactNavigationActivity extends ReactActivity {
    @Nullable
    @Override
    protected String getMainComponentName() {
        return "9.2.1";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                return new RNGestureHandlerEnabledRootView(ReactNavigationActivity.this);
            }
        };
    }
}
