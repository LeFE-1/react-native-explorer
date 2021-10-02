package com.example.C_7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.R;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * 广播
 */
public class MCBroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MCBroadcastActivity.class.getSimpleName();
    private final String ACTION = "student";
    private TextView tvMsg;
    private Button btnSend;
    private BroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcbroadcast);
        initView();
        myBroadcastReceiver = new MyBroadcastReceiver();
    }

    private void initView() {
        btnSend = findViewById(R.id.btnSendBroadcast);
        tvMsg = findViewById(R.id.tvMsg);
        btnSend.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            tvMsg.setText(intent.getStringExtra("name"));
            Log.e(TAG, "接收到广播，接收到的数据为: " + intent.getStringExtra("name"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendBroadcast:
                Intent intent = new Intent();
                intent.setAction(ACTION);
                intent.putExtra("name", "张三" + System.currentTimeMillis());
                sendBroadcast(intent);
                break;
            default:
                break;
        }
    }
}
