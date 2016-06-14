package com.teamlollaby.lollaby.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.teamlollaby.lollaby.R;
import com.teamlollaby.lollaby.adapter.BabyListAdapter;
import com.teamlollaby.lollaby.data.BabyListData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LaunchActivity extends WearableActivity{

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private ArrayList<BabyListData> dummyList;
    private BabyListAdapter babyListAdapter;
    private WearableListView lvBabyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        lvBabyList = (WearableListView) findViewById(R.id.baby_list_view);

        dummyList = new ArrayList<BabyListData>();
        BabyListData dummy = new BabyListData();
        dummy.babyName = "희동이";
        dummy.temperature = 26.3f;
        dummy.humidity = 25.3f;
        dummy.pressure = 0;
        dummy.noise = 0;
        dummyList.add(dummy);
        dummyList.add(new BabyListData());
        dummyList.add(new BabyListData());
        /*
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] vibratorPattern = {0, 1000, 1000, 1000};
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibratorPattern, indexInPatternToRepeat);
        */
        babyListAdapter = new BabyListAdapter(getApplicationContext(), dummyList);
        lvBabyList.setAdapter(babyListAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));

        } else {
            mContainerView.setBackground(null);
        }
    }
}
