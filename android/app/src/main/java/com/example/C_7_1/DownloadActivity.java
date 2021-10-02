package com.example.C_7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.R;

/**
 * Created by 付晓龙
 * on 2020-02-04
 * 多线程
 */
public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDownload;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
    }

    private void initView() {
        btnDownload = findViewById(R.id.btnDownload);
        progressBar = findViewById(R.id.progressBar);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDownload:
                DownloadTask d1 = new DownloadTask();
                d1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1);
                btnDownload.setText("正在下载");
                btnDownload.setEnabled(false);
                break;
            default:
                break;
        }
    }

    class DownloadTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            btnDownload.setText("下载中" + values[0] + "%");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            btnDownload.setText("下载完成");
            btnDownload.setEnabled(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            btnDownload.setText("下载失败");
            btnDownload.setEnabled(true);
        }
    }
}
