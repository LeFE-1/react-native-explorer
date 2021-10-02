package com.example.C_5_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.R;

import java.io.File;

public class VideoPlayActivity extends AppCompatActivity {
    private static final String VIDEO_FILE_NAME = "video_test.mp4"; // 要播放的视频文件名称
    private static String DEST_DIR; // 视频文件所处的目录
    private VideoView mVideoView; // Android 封装 MediaPlayer 提供的用于视频播放的组件
    private MediaController mediaController; // Android 提供的用于媒体控制的组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_2_2_video_play);

        DEST_DIR = getFilesDir().getAbsolutePath(); // 要播放的视频文件位于 内部存储/Package目录/files 目录下
        mVideoView = findViewById(R.id.video_view);
        mVideoView.setVideoPath(new File(DEST_DIR, VIDEO_FILE_NAME).getAbsolutePath()); // 设定视频路径
        mediaController = new MediaController(this); // 实例化媒体控制组件
        //VideoView与MediaController建立关联
        mVideoView.setMediaController(mediaController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start(); // Activity 展示时自动播放视频
    }
}
