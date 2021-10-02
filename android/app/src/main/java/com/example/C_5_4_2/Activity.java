package com.example.C_5_4_2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;

public class Activity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_4_2);
        tvResult = findViewById(R.id.tv_result);

        SharedPrefsTest sharedPrefsTest = new SharedPrefsTest(this);
        initView(R.id.btn_sp_write, v -> setResultText(sharedPrefsTest.performStoreData()));
        initView(R.id.btn_sp_read, v -> setResultText(sharedPrefsTest.performLoadData()));

        SQLiteTest sqLiteTest = new SQLiteTest(this);
        initView(R.id.btn_sql_insert, v -> setResultText(sqLiteTest.performInsertData()));
        initView(R.id.btn_sql_query, v -> setResultText(sqLiteTest.performQueryData()));

        FileTest fileTest = new FileTest(this);
        initView(R.id.btn_file_write, v -> setResultText(fileTest.performWriteFile()));
        initView(R.id.btn_file_read, v -> setResultText(fileTest.performReadFile()));
    }

    private void initView(int ResourceId, View.OnClickListener listener) {
        findViewById(ResourceId).setOnClickListener(listener);
    }

    private void setResultText(String resultText) {
        tvResult.setText(resultText);
    }

}
