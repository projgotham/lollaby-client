package com.teamlollaby.lollaby.listener;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.method.KeyListener;
import android.util.Log;

import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.teamlollaby.lollaby.LollabyController;
import com.teamlollaby.lollaby.activity.MainActivity;
import com.teamlollaby.lollaby.activity.NoiseActivity;
import com.teamlollaby.lollaby.activity.PressureActivity;
import com.teamlollaby.lollaby.data.BabyListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class alarmService extends Service {

    private static final String TAG = "Service Running";
    private static final int THRESHOLD = 500;
    private static final int NOISE_THRESHOLD = 1;

    // URL to get contacts JSON
    private static String url = "http://ec2-52-69-0-71.ap-northeast-1.compute.amazonaws.com/rest/selectStatus/testmachine";

    // JSON Node names
    private static final String TAG_OBJECT = "object";
    private static final String TAG_TEMPERATURE = "temperature";
    private static final String TAG_HUMIDITY = "humidity";
    private static final String TAG_PRESSURE = "pressure";
    private static final String TAG_NOISE = "noise";

    // contacts JSONArray
    JSONArray object = null;

    public alarmService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "서비스 시작");
        LollabyController.serviceIndicator = true;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(LollabyController.serviceIndicator){
                    synchronized(this){
                        // Creating service handler class instance
                        ServiceHandler sh = new ServiceHandler();

                        // Making a request to url and getting response
                        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
                        // Log.e("jsonStr", jsonStr);
                        // Log.d("Response: ", "> " + jsonStr);

                        if (jsonStr != null) {

                            try {
                                Log.i(TAG, "서비스 진입");
                                LollabyController.babyListSource.clear();
                                LollabyController.babyListSourceToWear.clear();

                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                object = jsonObj.getJSONArray(TAG_OBJECT);

                                JSONObject c = object.getJSONObject(0);
                                // LollabyController.retrievedStatus = c.getString(TAG_STATUS);
                                // LollabyController.retrievedStatusNm = c.getString(TAG_STATUSNM);
                                // LollabyController.retrievedTemperature = c.getString(TAG_TEMPERATURE);
                                // LollabyController.retrievedHumidity = c.getString(TAG_HUMIDITY);
                                // LollabyController.retrievedPressure = c.getString(TAG_PRESSURE);
                                // LollabyController.retrievedNoise= c.getString(TAG_NOISE);

                                LollabyController.currentBabyData = new BabyListData();
                                LollabyController.currentBabyData.babyName = "희동이";
                                LollabyController.currentBabyData.temperature = Float.parseFloat(c.getString(TAG_TEMPERATURE));
                                LollabyController.currentBabyData.humidity = Float.parseFloat(c.getString(TAG_HUMIDITY));
                                LollabyController.currentBabyData.pressure = Integer.parseInt(c.getString(TAG_PRESSURE));
                                LollabyController.currentBabyData.noise = Integer.parseInt(c.getString(TAG_NOISE));

                                LollabyController.babyListSource.add(LollabyController.currentBabyData);
                                LollabyController.babyListSource.add(new BabyListData());
                                LollabyController.babyListSource.add(new BabyListData());

                                // Log.i(TAG, String.valueOf(pressureValue));
                                if(LollabyController.currentBabyData.pressure > THRESHOLD){
                                    if(!LollabyController.alarmIndicator){
                                        LollabyController.alarmIndicator = true;
                                        Log.i(TAG, String.valueOf(LollabyController.currentBabyData.pressure));
                                        Intent pressureAlert = new Intent(alarmService.this, PressureActivity.class);
                                        pressureAlert.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(pressureAlert);
                                        // LollabyController.sendMessage("LOLLABY-PATH");
                                    }
                                }
                                else{
                                    if(LollabyController.currentBabyData.noise > NOISE_THRESHOLD){
                                        if(!LollabyController.alarmIndicator) {
                                            LollabyController.alarmIndicator = true;
                                            Intent noiseAlert = new Intent(alarmService.this, NoiseActivity.class);
                                            noiseAlert.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(noiseAlert);
                                        }
                                    }
                                    else{
                                        LollabyController.alarmIndicator = false;
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e(TAG, "Couldn't get any data from the url");
                        }

                        try{
                            wait(5000);
                        }catch (Exception e){}
                    }
                }
            }
        };

        Thread lollabyServiceThread = new Thread(r);
        lollabyServiceThread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "서비스가 종료되었습니다");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
