package com.example;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.example.C_10_1.module.MCUpdatePackage;
import com.example.C_11_2.PageLoader;
import com.example.C_11_3.LargeListPackage;
import com.example.C_2_4_2.RNViewOverflowPackage;
import com.example.C_7_1.MCRNStoragePackage;
import com.example.C_7_1.MCReactJavaEventEmitterPaclage;
import com.example.C_7_2_2.CustomJavaPackage;
import com.example.C_8.NativeListPackage;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;

import java.io.File;
import java.util.List;

import javax.annotation.Nullable;

public class MainApplication extends Application implements ReactApplication {
    private static MainApplication sInstance;

    public static MainApplication getInstance() {
        return sInstance;
    }

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Nullable
        @Override
        protected String getJSBundleFile() {
            String jsBundleFile = getFilesDir().getAbsolutePath() + "/index.android.bundle";
            File file = new File(jsBundleFile);
            return file.exists() ? jsBundleFile : null;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            List<ReactPackage> packageList = new PackageList(this).getPackages();
            packageList.add(new NativeListPackage());
            packageList.add(new MCRNStoragePackage());
            packageList.add(new MCReactJavaEventEmitterPaclage());
            packageList.add(new RNViewOverflowPackage());
            packageList.add(new MCUpdatePackage());
            packageList.add(new LargeListPackage());
            packageList.add(new CustomJavaPackage());
            return packageList;
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        MultiDex.install(this);
        SoLoader.init(this, /* native exopackage */ false);
        RouterManager.getInstance().init(this);
        PageLoader.getInstance().init(this);
    }
}
