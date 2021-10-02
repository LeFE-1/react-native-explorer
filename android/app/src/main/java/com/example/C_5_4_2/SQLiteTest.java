package com.example.C_5_4_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yangpan
 * on 2019-11-08
 */
public class SQLiteTest {

    private static String DATABASE_NAME = "SQLiteTest";
    private static int DATABASE_VERSION = 1;
    private MyDBHelper mDBHelper;

    public SQLiteTest(Context context) {
        // 新建数据库工具类，创建名称为 SQLitTest 的数据库
        mDBHelper = new MyDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 插入数据
     */
    public String performInsertData() {
        SQLiteDatabase writableDatabase = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", "小明");
        values.put("userage", 18);
        values.put("isvip", 0);
        writableDatabase.insert("User", null, values);
        return "插入数据成功";
    }

    /**
     * 查询数据
     */
    public String performQueryData() {
        StringBuilder result = new StringBuilder();
        SQLiteDatabase readableDatabase = mDBHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String userName = cursor.getString(cursor.getColumnIndex("username"));
                int userAge = cursor.getInt(cursor.getColumnIndex("userage"));
                boolean isVip = cursor.getInt(cursor.getColumnIndex("isvip")) == 1;
                result
                    .append("userName: ").append(userName).append("\n")
                    .append("userAge: ").append(userAge).append("\n")
                    .append("isVip: ").append(isVip).append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result.toString();
    }

}
