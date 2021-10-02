package com.example.C_10_1;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


import com.example.C_10_1.bean.MCUpdateResponseBean;
import com.example.C_10_1.global.MCGlobal;
import com.example.C_10_1.interf.MCDownloadListener;
import com.example.C_10_1.interf.MCUpdateResponseListener;
import com.example.C_10_1.utils.GsonUtil;
import com.example.C_10_1.utils.MCFileUtils;
import com.example.C_10_1.utils.MCHttpUtils;
import com.example.C_10_1.utils.MCLogUtils;
import com.example.C_10_1.utils.MCZipUtils;
import com.example.MainApplication;
import com.example.R;
import com.facebook.react.ReactInstanceManager;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MCUpdate {
    private static final String TAG = MCUpdate.class.getSimpleName();
    private static volatile MCUpdate sInstance;
    private boolean isDownloading;

    public static MCUpdate getInstance() {
        if (sInstance == null) {
            synchronized (MCUpdate.class) {
                if (sInstance == null) {
                    sInstance = new MCUpdate();
                }
            }

        }
        return sInstance;
    }

    public void checkUpdate(final Context context, JSONObject req, MCUpdateResponseListener listener) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, req.toString());
        Request request = new Request.Builder()
                .url(MCGlobal.UPDATE_URL)
                .post(requestBody)
                .build();

        new OkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        MCUpdateResponseBean appUpdateResponse = GsonUtil.fromJson(result, MCUpdateResponseBean.class);
                        if (appUpdateResponse == null) {
                            listener.onError("update err1");
                            return;
                        }
                        if (appUpdateResponse.ret == 1) {
                            MCLogUtils.e(TAG, "onResponse: 2");
                            if (appUpdateResponse.data != null) {
                                listener.onSuccess(appUpdateResponse.data);
                            }
                        } else {
                            listener.onError("update err2");
                        }

                    }
                });
    }

    public void downloadFile(final Context context, int type, final String url, final String md5, MCDownloadListener listener) {
        if (!TextUtils.isEmpty(url)) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                String apkDir = context.getExternalFilesDir("Hotfix").getPath();
                String appName = context.getString(R.string.app_name);
                if (TextUtils.isEmpty(appName)) {
                    appName = "com.example.amp";
                }
                File file = new File(apkDir);
                if (!file.exists()) {
                    file.mkdir();
                }
                File apkFile = null;
                if (type == 1) { //App更新
                    apkFile = new File(apkDir + appName + ".apk");
                } else {//热更新
                    apkFile = new File(apkDir + appName + ".zip");
                }
                // 根据md5检测是否已下载该文件
                if (!TextUtils.isEmpty(md5)) {
                    if (md5.equals(MCFileUtils.getFileMD5(apkFile))) {
                        if (type == 1) {//APK
                            listener.onSuccess(apkFile.getPath());
                        } else {//热更新
                            getJSBundle(context, apkFile, md5, listener);
                        }
                    } else {
                        update(context, type, url, md5, apkFile, listener);
                    }
                }

            } else {
                listener.onError("请安装SD卡");
            }

        } else {
            listener.onError("热更新URL为空");
        }
    }

    private void update(final Context context, int type, final String url, final String md5, File apkFile, MCDownloadListener listener) {
        if (isDownloading) {
            return;
        } else {
            isDownloading = true;
        }
        //检测是否有该文件，如果有则删除
        if (new File(apkFile.getPath()).exists()) {
            new File(apkFile.getPath()).delete();
        }
        // 开启一个新的线程下载
        io.reactivex.Observable.just(1).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
//                这个是下载的重点，是下载的过程
                try {
                    boolean isDownload = MCHttpUtils.getInstance().downloadUpdateFile(url, apkFile);
                    isDownloading = false;
                    if (isDownload) {//下载成功
                        if (type == 1) {
                            ReactInstanceManager reactInstanceManager = MainApplication.getInstance().getReactNativeHost().getReactInstanceManager();
                            reactInstanceManager.recreateReactContextInBackground();
                            listener.onSuccess(apkFile.getPath());
                        } else {
                            getJSBundle(context, apkFile, md5, listener);
                        }
                    } else {//下载失败
                        listener.onError("下载失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    isDownloading = false;
                    listener.onError(e.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
                isDownloading = false;
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void getJSBundle(Context context, File apkFile, String md5, MCDownloadListener listener) {
        List<File> list = MCZipUtils.upzipFile(apkFile, MCFileUtils.getWritablePath(context, md5));
        if (list.size() > 0) {
            MCLogUtils.e(TAG, "******update: ====>解压完成");
            boolean hasBundle = false;
            int bundleIndex = 0;
            for (int i = 0; i < list.size(); i++) {
                MCLogUtils.e("========>" + list.get(i).getAbsolutePath());
                if (list.get(i).getAbsolutePath().endsWith(".bundle")) {
                    hasBundle = true;
                    bundleIndex = i;
                    break;
                }
            }
            if (hasBundle) {
                MCLogUtils.e(TAG, "找到bundle文件: " + list.get(bundleIndex).getPath());
                listener.onSuccess(list.get(bundleIndex).getPath());
            } else {
                listener.onError("******压缩文件中未找到jsbundle");
                MCLogUtils.e(TAG, "******update: ========>压缩文件中未找到jsbundle");
            }
        } else {
            listener.onError("******解压后文件为空");
        }
    }
}
