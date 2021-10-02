package com.example.C_9_3_1;

import androidx.annotation.Nullable;

import com.example.BuildConfig;
import com.example.C_10_1.module.MCUpdatePackage;
import com.example.C_11_3.LargeListPackage;
import com.example.C_2_4_2.RNViewOverflowPackage;
import com.example.C_7_1.MCRNStoragePackage;
import com.example.C_7_1.MCReactJavaEventEmitterPaclage;
import com.example.C_7_2_2.CustomJavaPackage;
import com.example.C_8.NativeListPackage;
import com.example.RouterManager;
import com.facebook.react.PackageList;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.reactnativenavigation.NavigationApplication;
import com.reactnativenavigation.react.NavigationReactNativeHost;
import com.reactnativenavigation.react.ReactGateway;

import java.util.Arrays;
import java.util.List;

public class ReactNativeNavigationApplication extends NavigationApplication {
    @Override
    protected ReactGateway createReactGateway() {
        ReactNativeHost host = new NavigationReactNativeHost(this, isDebug(), createAdditionalReactPackages()) {
            @Override
            protected String getJSMainModuleName() {
                return "index";
            }
        };
        return new ReactGateway(this, isDebug(), host);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    protected List<ReactPackage> getPackages() {
        // Add additional packages you require here
        // No need to add RnnPackage and MainReactPackage
        List<ReactPackage> packageList = new PackageList(this).getPackages();
        packageList.add(new NativeListPackage());
        packageList.add(new MCRNStoragePackage());
        packageList.add(new MCReactJavaEventEmitterPaclage());
        packageList.add(new RNViewOverflowPackage());
        packageList.add(new MCUpdatePackage());
        packageList.add(new LargeListPackage());
        packageList.add(new CustomJavaPackage());
        return packageList;
//        return Arrays.<ReactPackage>asList(
//                // eg. new VectorIconsPackage()
//        );
    }

    @Nullable
    @Override
    public List<ReactPackage> createAdditionalReactPackages() {
        return getPackages();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RouterManager.getInstance().init(this);
    }
}
