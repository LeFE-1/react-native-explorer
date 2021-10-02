package com.example.C_8.manager;

import android.text.TextUtils;
import android.widget.VideoView;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nonnull;

public class NativeVideoViewManager extends SimpleViewManager<VideoView> {
    @Nonnull
    @Override
    public String getName() {
        return "VideoView";
    }

    @Nonnull
    @Override
    protected VideoView createViewInstance(@Nonnull ThemedReactContext reactContext) {
        VideoView videoView = new VideoView(reactContext);
        return videoView;
    }

    @Override
    public void onDropViewInstance(@Nonnull VideoView view) {
        super.onDropViewInstance(view);
        //对象销毁时停止播放
        view.stopPlayback();
    }

    /**
     * 设置播放地址
     */
    @ReactProp(name = "url")
    public void setVideoUrl(VideoView videoView, String url) {
        if (!TextUtils.isEmpty(url)) {
            videoView.setVideoPath(url);
            videoView.start();
        }
    }
}
