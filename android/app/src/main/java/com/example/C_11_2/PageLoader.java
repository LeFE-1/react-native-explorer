package com.example.C_11_2;

/**
 * Created by yangpan
 * on 2020-02-03
 */

import android.content.Context;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;

import java.util.Hashtable;

/**
 * Created by yangpan
 * on 2020-01-14
 */
public class PageLoader {
    private static PageLoader sPageloader;
    private Hashtable<String, ReactRootView> mCachedViewPool = new Hashtable<>();
    private ReactApplication mReactApplication;

    private PageLoader() {
    }

    public static PageLoader getInstance() {
        if (sPageloader == null) {
            synchronized (PageLoader.class) {
                if (sPageloader == null) {
                    sPageloader = new PageLoader();
                }
            }
        }
        return sPageloader;
    }

    public void init(ReactApplication application) {
        mReactApplication = application;
    }

    /**
     * 缓存一个待显示的视图
     *
     * @param context       上下文对象
     * @param componentName JS 端 AppRegistry 注册的 mainComponentName
     */
    public void cache(Context context, String componentName) {
        if (contains(componentName)) {
            Log.e("PageLoader", componentName + "已在缓存池中，跳过本次缓存步骤");
            return;
        }
        ReactRootView reactRootView = createReactRootView(context, componentName);
        mCachedViewPool.put(componentName, reactRootView);
    }

    /**
     * 获取一个要显示的 ReactRootView 实例
     * 如果缓存池中存在匹配的 ReactRootView 实例则优先使用，如果不存在则新建一个 ReactRootView 实例并返回
     *
     * @param context       上下文对象
     * @param componentName JS 端 AppRegistry 注册的 mainComponentName
     * @return 要显示的 ReactRootView 实例
     */
    public ReactRootView getReactRootView(Context context, String componentName) {
        if (!contains(componentName)) {
            Log.e("PageLoader", "getReactRootView: " + componentName + "未缓存，开始创建新的页面");
            return createReactRootView(context, componentName);
        }
        return mCachedViewPool.get(componentName);
    }

    /**
     * 在 Activity 销毁时调用，传入即将销毁的 ReactRootView 实例
     * 若当前视图需要循环使用，则在被消费后重新创建 ReactRootView 实例并放置在缓存池中
     * 若不需要则在缓存池中将它移除
     *
     * @param view 即将销毁的 ReactRootView 实例
     */
    public void recycleOrRelease(ReactRootView view, String componentName, boolean recycle) {
        if (recycle) {
            mCachedViewPool.put(
                componentName,
                createReactRootView(
                    view.getContext(),
                    componentName
                )
            );
        } else {
            mCachedViewPool.remove(componentName);
        }

    }

    /**
     * 判断缓存池中是否已经具有对应的视图
     * 注意：暂不支持缓存可复用的页面，如果有需要请修改此方法的实现
     *
     * @param componentName JS 端 AppRegistry 注册的 mainComponentName
     * @return 是否已经具有对应的视图的结果
     */
    public boolean contains(String componentName) {
        return mCachedViewPool.containsKey(componentName) && mCachedViewPool.get(componentName) != null;
    }

    /**
     * 创建 ReactRootView 实例
     *
     * @param context       上下文对象
     * @param componentName JS 端 AppRegistry 注册的 mainComponentName
     * @return 创建的 ReactRootView 实例
     */
    private ReactRootView createReactRootView(Context context, String componentName) {
        if (mReactApplication == null) {
            Log.e("PageLoader", "createReactRootView: 尚未初始化！");
        }
        ReactRootView reactRootView = new ReactRootView(context);
        reactRootView.startReactApplication(
            mReactApplication.getReactNativeHost().getReactInstanceManager(),
            componentName
        );
        return reactRootView;
    }

}
