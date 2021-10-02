package com.example.C_11_2;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;

import javax.annotation.Nullable;

/**
 * Created by yangpan
 * on 2020-02-03
 */
public class PreloadDelegate extends ReactActivityDelegate {

    private ReactRootView mReactRootView;

    public PreloadDelegate(ReactActivity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    @Override
    protected void loadApp(String appKey) {
        mReactRootView = PageLoader.getInstance().getReactRootView(getPlainActivity(), getMainComponentName());
        getPlainActivity().setContentView(mReactRootView);
    }

    @Override
    protected void onDestroy() {
        PageLoader.getInstance().recycleOrRelease(mReactRootView, getMainComponentName(), true);
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
            mReactRootView = null;
        }
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onHostDestroy(getPlainActivity());
        }
    }
}
