package com.example.C_11_3;

import android.app.Application;

import com.example.C_11_3.utils.DataConvertUtil;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nonnull;

/**
 * Created by yangpan
 * on 2020-02-25
 */
public class LargeListViewManager extends ViewGroupManager<LargeListView> {

    @Nonnull
    @Override
    public String getName() {
        return "LargeList";
    }

    @Nonnull
    @Override
    protected LargeListView createViewInstance(@Nonnull ThemedReactContext reactContext) {
        Application app = reactContext.getCurrentActivity().getApplication();
        ReactInstanceManager reactInstanceManager = null;
        if (app instanceof ReactApplication) {
             reactInstanceManager = ((ReactApplication) app).getReactNativeHost().getReactInstanceManager();
        }
        if (reactInstanceManager == null) {
            throw new RuntimeException("ReactInstanceManager 尚未实例化！");
        }
        return new LargeListView(reactContext, reactInstanceManager);
    }

    @Override
    public void onDropViewInstance(@Nonnull LargeListView view) {
        super.onDropViewInstance(view);
        view.destroyAllReactRootViews();
    }

    @ReactProp(name = "itemHeight")
    public void setItemHeight(LargeListView listView, float itemHeight) {
        int height = (int) PixelUtil.toPixelFromDIP(itemHeight);
        listView.getAdapterBuilder().setItemHeight(height);
    }

    @ReactProp(name = "itemModuleName")
    public void setItemModuleName(LargeListView listView, String itemModuleName) {
        listView.getAdapterBuilder().setItemModuleName(itemModuleName);
    }

    @ReactProp(name = "data")
    public void setData(LargeListView listView, ReadableArray data) {
        if (listView.getAdapter() == null) { // 初次赋值
            listView.getAdapterBuilder().setData(DataConvertUtil.getDataFrom(data));
        } else { // 数据源改变
            listView.getAdapter().setData(DataConvertUtil.getDataFrom(data));
        }
    }

}
