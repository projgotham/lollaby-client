package com.teamlollaby.lollaby.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.teamlollaby.lollaby.LollabyController;
import com.teamlollaby.lollaby.R;

/**
 * Created by MinGu on 2015-10-26.
 */
public class PressureActivity extends AppCompatActivity {

    private static final String TAG = "PressureActivity";
    private static final String LOLLABY_PATH = "/lollaby-path-wear";
    private static final String LOLLABY_NOISE_PATH = "/lollaby-path-wear-noise";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LollabyController.sendMessage(LOLLABY_PATH);
    }

}
