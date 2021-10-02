package com.example.C_5_2_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MUSIC_URL = "http://sc1.111ttt.cn/2017/1/11/11/304112002347.mp3"; // 要播放的音乐资源地址（可以为本地资源地址）
    private LinearLayout ll_controller; // 控制音乐播放的按钮布局
    private TextView tv_media_status; // 当前的状态
    private TextView tv_duration; // 音乐的总时长
    private TextView tv_current; // 当前播放的时长
    private SeekBar sb_progress; // 播放进度条
    private MediaPlayer player = new MediaPlayer();
    private SimpleDateFormat formatter = new SimpleDateFormat("mm:ss"); // 用于格式化播放时长
    private Timer mTimer = new Timer(); // 计时器，用于更新进度条
    private long mDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_2_1_audio_play);

        initViews();
        checkPermissions(); // 检查权限
        mTimer.schedule(new TimerTask() { // 每 1 秒钟更新一次进度条和播放时长
            @Override
            public void run() {
                runOnUiThread(() -> {
                    long currentPosition = player.getCurrentPosition();
                    sb_progress.setProgress((int) currentPosition);
                    tv_current.setText(formatter.format(currentPosition));
                });
            }
        }, 0, 1000);
    }

    private void initViews() {
        Button btn_play = findViewById(R.id.btn_play); // 播放按钮
        Button btn_pause = findViewById(R.id.btn_pause); // 暂停按钮
        Button btn_stop = findViewById(R.id.btn_stop); // 停止按钮
        Button btn_reset = findViewById(R.id.btn_reset); // 重置按钮
        tv_duration = findViewById(R.id.tv_duration);
        tv_current = findViewById(R.id.tv_current);
        tv_media_status = findViewById(R.id.tv_media_status);
        sb_progress = findViewById(R.id.sb_progress);
        ll_controller = findViewById(R.id.ll_controller);
        sb_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    player.seekTo(progress); // 根据用户拖拽的进度进行快进/快退
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
    }

    private void checkPermissions() {
        //验证权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        } else {
            initMedia();
        }
    }

    private void initMedia() {
        try {
            player.setDataSource(MUSIC_URL);//指定文件路径
            player.prepareAsync(); // 异步准备要播放的资源
            // 设置资源准备完毕时的回调，准备完毕时设置播放总时长、进度条长度、显示控制按钮、更新当前播放状态
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mDuration = mp.getDuration();
                    tv_duration.setText(formatter.format(mDuration));
                    sb_progress.setMax((int) mDuration);
                    tv_media_status.setText("未播放");
                    ll_controller.setVisibility(View.VISIBLE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMedia();
            } else {
                Toast.makeText(this, "被拒绝啦", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (!player.isPlaying()) {// 播放
                    player.start();
                    tv_media_status.setText("正在播放");
                }
                break;
            case R.id.btn_pause:
                if (player.isPlaying()) {// 暂停
                    player.pause();
                    tv_media_status.setText("暂停中");
                }
                break;
            case R.id.btn_stop:
                player.stop(); // 停止
                break;
            case R.id.btn_reset:
                player.reset(); // 重置
                tv_media_status.setText("已停止");
                initMedia();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) { // 页面销毁时释放资源
            player.stop();
            player.release();
        }
        mTimer.cancel();
    }
}
