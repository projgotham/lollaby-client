package com.teamlollaby.lollaby.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import com.teamlollaby.lollaby.activity.LaunchActivity;
import com.teamlollaby.lollaby.activity.NoiseAlertActivity;
import com.teamlollaby.lollaby.activity.PressureAlertActivity;
import com.teamlollaby.lollaby.data.BabyListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MinGu on 2015-10-28.
 */
public class ListenerServiceFromMobile extends WearableListenerService {

    private static final String LOLLABY_PATH = "/lollaby-path-wear";
    private static final String LOLLABY_NOISE_PATH = "/lollaby-path-wear-noise";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        if (messageEvent.getPath().equals(LOLLABY_PATH)) {
            Intent startIntent = new Intent(this, PressureAlertActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startIntent);
        }

        if (messageEvent.getPath().equals(LOLLABY_NOISE_PATH)){
            Intent startIntent = new Intent(this, NoiseAlertActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startIntent);
        }
    }

}
