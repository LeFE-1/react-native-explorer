package com.example.C_7_1;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class MCReactJavaEventEmitterModule
        extends ReactContextBaseJavaModule {
    private ReactApplicationContext reactApplicationContext;

    public MCReactJavaEventEmitterModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        reactApplicationContext = reactContext;
    }

    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put("COMPONENT_DID_APPEAR", MCEventEmitter.ComponentDidAppear);
        constants.put("COMPONENT_DID_DISAPPEAR", MCEventEmitter.ComponentDidDisappear);
        return constants;

    }

    @Nonnull
    @Override
    public String getName() {
        return "MCRNEventEmitter";
    }

    @ReactMethod
    public void sendEventWithName() {
        reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(MCEventEmitter.ComponentDidAppear, "msg from Android");
    }
}
