package com.example.C_5_2_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.R;

import java.io.File;
import java.io.IOException;

public class AudioRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String AUDIO_FILE_NAME = "test_audio.mp4"; // 要保存的文件名
    private static String RECORD_DIR; // 保存的目录地址
    private MediaRecorder recorder = new MediaRecorder(); // 用于录制音频
    private MediaPlayer player = new MediaPlayer(); // 用于播放音频

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_2_1_audio_record);

        RECORD_DIR = getFilesDir().getAbsolutePath(); // 录制/播放的音频位于 内部存储/Package目录/files 目录下
        initViews();
        checkPermissions();
    }

    private void initViews() {
        Button btn_start_record = findViewById(R.id.btn_start_record); // 开始录制按钮
        Button btn_pause_record = findViewById(R.id.btn_pause_record); // 暂停录制按钮
        Button btn_resume_record = findViewById(R.id.btn_resume_record); // 继续录制按钮
        Button btn_stop_record = findViewById(R.id.btn_stop_record); // 停止录制按钮
        Button btn_play = findViewById(R.id.btn_play); // 播放按钮
        Button btn_online_music = findViewById(R.id.btn_online_music); // 跳转至在线音频播放
        btn_start_record.setOnClickListener(this);
        btn_pause_record.setOnClickListener(this);
        btn_resume_record.setOnClickListener(this);
        btn_stop_record.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_online_music.setOnClickListener(this);
    }

    // 准备播放音频
    private void initMediaPlayer() {
        try {
            player.setDataSource(new File(RECORD_DIR, AUDIO_FILE_NAME).getAbsolutePath());//指定文件路径
            player.prepareAsync(); // 异步准备要播放的资源
            // 设置资源准备完毕时的回调
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start(); // 准备完毕后直接播放
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 准备录制音频
    private void initMediaRecorder() {
        try {
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 设置录制来源为主麦克风
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); // 设置输出格式为 mpeg-4 格式
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC); // 设置编码格式为 AAC
            recorder.setOutputFile(new File(RECORD_DIR, AUDIO_FILE_NAME).getAbsolutePath()); // 设置录音文件输出路径
            recorder.prepare(); // 准备资源
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    private void checkPermissions() {
        //验证权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO // 注意获取录音权限
            }, 1);
        } else {
            initMediaRecorder();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMediaRecorder();
            } else {
                Toast.makeText(this, "被拒绝啦", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_record: // 点击开始录制
                try {
                    recorder.start(); // 开始录制
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    showToast("开始失败");
                }
                break;
            case R.id.btn_pause_record: // 点击暂停录制
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    try {
                        recorder.pause(); // 暂停录制
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        showToast("暂停失败");
                    }
                }
                break;
            case R.id.btn_resume_record: // 点击继续录制
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    try {
                        recorder.resume(); // 继续录制
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        showToast("继续失败");
                    }
                }
                break;
            case R.id.btn_stop_record: // 点击停止录制
                try {
                    recorder.stop(); // 停止录制并保存录音文件
                    recorder.release();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    showToast("停止失败");
                }
                break;
            case R.id.btn_play: // 点击播放
                initMediaPlayer();
                break;
            case R.id.btn_online_music: // 跳转至在线音频播放
                startActivity(new Intent(this, AudioPlayActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() { // Activity 销毁时释放资源
        super.onDestroy();
        try {
            recorder.stop();
            recorder.release();
            player.stop();
            player.release();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
