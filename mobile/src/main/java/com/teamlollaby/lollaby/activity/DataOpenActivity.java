package com.teamlollaby.lollaby.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.teamlollaby.lollaby.R;
import com.teamlollaby.lollaby.listener.alarmService;

/**
 * Created by MinGu on 2015-10-31.
 */
public class DataOpenActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_open);

        Intent serviceIntent = new Intent(DataOpenActivity.this, alarmService.class);
        startService(serviceIntent);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "데이터를 불러오는 중입니다", "잠시만 기다려주세요", true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Intent intent = new Intent(DataOpenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
