package com.example.C_5_2_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;

import java.io.File;
import java.io.IOException;

public class VideoRecordActivity extends AppCompatActivity {

    private static final String VIDEO_FILE_NAME = "video_test.mp4"; // 录制的视频文件名称
    private static String DEST_DIR; // 录制视频保存的目录
    private SurfaceView sv_record; // 用于显示相机拍摄到的画面
    private TextView tv_status; // 用于显示当前录制的状态
    private Camera camera = null; // 用于操控相机硬件
    private MediaRecorder recorder; // 实现视频录制的逻辑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_2_2_video_record);

        DEST_DIR = getFilesDir().getAbsolutePath(); // 视频输出目录为 内部存储/Package目录/files
        checkPermissions(); // 检查权限
    }

    private void checkPermissions() {
        //验证权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA // 注意获取相机权限
            }, 1);
        } else {
            initViews(); // 初始化 UI 组件
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initViews(); // 初始化 UI 组件
            } else {
                Toast.makeText(this, "被拒绝啦", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void initViews() {
        sv_record = findViewById(R.id.sv_record); // SurfaceView 显示相机拍摄到的画面
        Button btn_start_record = findViewById(R.id.btn_start_record); // 开始录制按钮
        Button btn_stop_record = findViewById(R.id.btn_stop_record); // 停止录制按钮
        tv_status = findViewById(R.id.tv_status); // 显示录制状态的文字
        btn_start_record.setOnClickListener(v -> { // 点击开始录制按钮
            recorder = new MediaRecorder(); // 实例化 MediaRecorder
            if (camera == null) return;
            camera.unlock(); // 解锁相机
            recorder.setCamera(camera); // 将相机实例传入到 MediaRecorder 中
            // 设置录制的角度，如果与摄像头不符，会出现视频角度不对的问题
            recorder.setOrientationHint(90);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 音频来源为主麦克风
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 视频来源为相机
            // 还可以设置其他的信息
            // 输出的视频格式
//            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            // 设置编码的格式
//            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            // 设置视频的大小，必须要在设置视频格式之后设置，否则会报错
//            setVideoSize()
            //视频的帧率
//            setVideoFrameRate(25)
            // 设置录制的质量
            recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
            // 设置文件的输出路径
            recorder.setOutputFile(new File(DEST_DIR, VIDEO_FILE_NAME).getAbsolutePath());
            recorder.setPreviewDisplay(sv_record.getHolder().getSurface()); // 使用 SurfaceView 预览相机拍摄到的画面
            try {
                recorder.prepare(); // 加载资源
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 开始录制
            recorder.start();
            tv_status.setText("正在录制"); // 修改录制状态文字
        });
        btn_stop_record.setOnClickListener(v -> {
            if (recorder == null) return;
            recorder.stop(); // 停止录制
            recorder.release(); // 释放资源
            recorder = null;
            if (camera == null) return;
            camera.lock(); // 锁定相机
            tv_status.setText("已停止"); // 修改录制状态文字
            Toast.makeText(this, "3 秒后进入视频播放页面，展示刚刚录制的视频", Toast.LENGTH_SHORT).show();
            // 3 秒后跳转至视频播放页面，展示刚刚录制的视频
            new Handler().postDelayed(() -> startActivity(new Intent(this, VideoPlayActivity.class)), 3000);
        });

        sv_record.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) { // 当 surface 创建时
                // 开启摄像头
                if (camera == null) {
                    camera = Camera.open(); // 获取到相机实例
                }
                // 虽然关闭了提示音，但是是没有作用的
                camera.enableShutterSound(false);
                camera.setDisplayOrientation(90);
                try {
                    // 绑定显示的SurfaceHolder
                    camera.setPreviewDisplay(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 开启预览
                camera.startPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { // 当画面变化时
                if (holder.getSurface() == null) return;
                // 刷新相机获取到的画面
                camera.stopPreview();
                try {
                    camera.setPreviewDisplay(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) { }
        });
    }
}
