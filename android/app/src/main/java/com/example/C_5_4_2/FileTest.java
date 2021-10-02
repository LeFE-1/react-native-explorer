package com.example.C_5_4_2;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yangpan
 * on 2019-11-08
 */
public class FileTest {

    private static String PACKAGE_FILE_DIR;
    private static String FILE_NAME = "file_test.text";

    public FileTest(Context context) {
        PACKAGE_FILE_DIR = context.getFilesDir().getAbsolutePath();
    }

    public String performWriteFile() {
        String content = "userName: 小明\n" +
            "userAge: 18\n" +
            "isVip: false";

        File file = new File(PACKAGE_FILE_DIR, FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "写入文件成功";
    }

    public String performReadFile() {
        String content = null;

        File file = new File(PACKAGE_FILE_DIR, FILE_NAME);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (inputStream.read(buf) != -1) {
                sb.append(new String(buf).trim());
            }
            content = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content == null ? "读取文件失败" : content;
    }

}
