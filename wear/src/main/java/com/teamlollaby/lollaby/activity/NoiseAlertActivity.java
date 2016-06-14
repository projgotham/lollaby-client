package com.teamlollaby.lollaby.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.activity.WearableActivity;

import com.teamlollaby.lollaby.R;

/**
 * Created by MinGu on 2015-11-01.
 */
public class NoiseAlertActivity extends WearableActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_noise);

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] vibratorPattern = {5000, 5000, 5000, 5000};
        final int indexInPatternToRepeat = -1;
        vibrator.vibrate(vibratorPattern, indexInPatternToRepeat);
    }
}
