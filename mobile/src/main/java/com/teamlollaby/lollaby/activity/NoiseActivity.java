package com.teamlollaby.lollaby.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.teamlollaby.lollaby.LollabyController;
import com.teamlollaby.lollaby.R;

/**
 * Created by MinGu on 2015-11-01.
 */
public class NoiseActivity extends AppCompatActivity{

    private static final String TAG = "PressureActivity";
    private static final String LOLLABY_PATH = "/lollaby-path-wear";
    private static final String LOLLABY_NOISE_PATH = "/lollaby-path-wear-noise";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LollabyController.sendMessage(LOLLABY_NOISE_PATH);
    }
}
