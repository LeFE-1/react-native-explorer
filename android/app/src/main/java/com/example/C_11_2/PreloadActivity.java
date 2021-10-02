package com.example.C_11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.R;
import com.example.RNExampleActivity;
import com.example.RouterManager;

public class PreloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        PageLoader.getInstance().cache(this, "11_2");
        TextView tvTips = findViewById(R.id.tv_tips);
        tvTips.setText("说明：\n1. 进入本页面后会自动缓存页面；\n2. 请稍候几毫秒再点击按钮即可对比出打开预缓存页面与打开新页面之间的区别；\n3. 可以明显看出不使用视图预缓存的页面在打开时有短暂的白屏现象。");
        findViewById(R.id.btn_open_cached_page).setOnClickListener(v -> {
            RNPreloadExampleActivity.startInstance(this, "11_2");
        });
        findViewById(R.id.btn_open_new_page).setOnClickListener(v -> {
            RouterManager.getInstance().startPage("第十一章 视图嵌套");
        });
    }
}
