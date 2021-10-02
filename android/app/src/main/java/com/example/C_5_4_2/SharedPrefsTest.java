package com.example.C_5_4_2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangpan
 * on 2019-11-08
 *
 * SharedPreferences 测试类
 *
 */
public class SharedPrefsTest {
    private static String spName = "SPTest"; // SharedPreferences 的名称
    private SharedPreferences sp;

    public SharedPrefsTest(Context context) {
        // 从上下文对象 context 中获取 SharedPreferences 对象
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * 写入数据
     */
    public String performStoreData() {
        sp.edit()
            .putString("userName", "小明")
            .putInt("userAge", 18)
            .putBoolean("isVip", false)
            .apply();
        return "写入数据完毕";
    }

    /**
     * 读取数据
     */
    public String performLoadData() {
        String userName = sp.getString("userName", "默认名称");
        int userAge = sp.getInt("userAge", -1);
        boolean isVip = sp.getBoolean("isVip", false);
        return
            "userName: " + userName + "\n" +
            "userAge: " + userAge + "\n" +
            "isVip: " + isVip;
    }
}
