package com.example.C_10_1.utils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public class MCHttpUtils {
    private static final String TAG = MCHttpUtils.class.getSimpleName();
    private static volatile MCHttpUtils sInstance;

    public static MCHttpUtils getInstance() {
        if (sInstance == null) {
            synchronized (MCHttpUtils.class) {
                if (sInstance == null) {
                    sInstance = new MCHttpUtils();
                }
            }

        }
        return sInstance;
    }

    /**
     * 下载
     */
    public boolean downloadUpdateFile(String downloadUrl, File saveFile) throws Exception {
        long totalSize = 0;
        int updateTotalSize = 0;
        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(downloadUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestProperty("Accept-Encoding", "identity");
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(20000);
            httpConnection.connect();
            updateTotalSize = httpConnection.getContentLength();
            MCLogUtils.e("updateTotalSize=====>" + updateTotalSize);
            if (httpConnection.getResponseCode() == 404) {
                throw new Exception("fail!");
            }
            is = httpConnection.getInputStream();
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[1024];
            int readsize = 0;
            while ((readsize = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readsize);
                totalSize += readsize;
            }
        } catch (Exception e) {
            Log.e(TAG, "下载失败" + e);
            totalSize = 0;
            // 下载失败
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
            if (is != null) {
                is.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
        return totalSize != 0 && (totalSize == updateTotalSize);
    }
}
