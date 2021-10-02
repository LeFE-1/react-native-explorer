package com.example.C_2_2_2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.R;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class Activity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    ReactFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_2_2);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            ReactFragment.Builder fragmentBuilder = new ReactFragment.Builder();
            fragment = fragmentBuilder
                .setComponentName("2_2_1") // 设置 JS 端注册的 ComponentName
                .setLaunchOptions(new Bundle()) // 设置传入的初始参数
                .build();

        } else {
            fragment = (ReactFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        }

        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
