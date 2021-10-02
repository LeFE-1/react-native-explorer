package com.example.C_10_1.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public class MCFileUtils {
    private static String TAG = "explorer";

    public static String getWritablePath(Context context, String md5) {
        String path = context.getExternalFilesDir("Hotfix").getPath() + "/" + MCAppUtils.getAppVersionName(context) + "/" + md5;
        File f = new File(path);
        cleanCustomCache(f.getAbsolutePath());
        if (!f.exists() || !f.isDirectory())
            f.mkdirs();
        MCLogUtils.e(TAG, "--->文件路径：" + f.getAbsolutePath());
        return f.getAbsolutePath();
    }

    /**
     * 获取单个文件的MD5值！
     *
     * @param file
     * @return
     */

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
