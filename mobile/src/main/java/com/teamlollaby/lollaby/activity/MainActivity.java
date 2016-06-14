package com.teamlollaby.lollaby.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.teamlollaby.lollaby.LollabyController;
import com.teamlollaby.lollaby.R;
import com.teamlollaby.lollaby.adapter.BabyListAdapter;
import com.teamlollaby.lollaby.data.BabyListData;
import com.teamlollaby.lollaby.listener.ServiceHandler;
import com.teamlollaby.lollaby.listener.alarmService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView rvBabyList = null;

    private BabyListAdapter babyListAdapter;

    private static final int THRESHOLD = 500;

    // URL to get contacts JSON
    private static String url = "http://ec2-52-69-0-71.ap-northeast-1.compute.amazonaws.com/rest/selectStatus/testmachine";

    // JSON Node names
    private static final String TAG_OBJECT = "object";
    private static final String TAG_TEMPERATURE = "temperature";
    private static final String TAG_HUMIDITY = "humidity";
    private static final String TAG_PRESSURE = "pressure";
    private static final String TAG_NOISE = "noise";

    public Handler mHandler = new Handler();
    // contacts JSONArray
    JSONArray object = null;
    /*
    public TextView status;
    public TextView statusNm;
    public TextView temperature;
    public TextView humidity;
    public TextView pressure;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        status = (TextView) findViewById(R.id.status);
        statusNm = (TextView) findViewById(R.id.statusNm);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);
        */

        // Initialize recycler view
        rvBabyList = (RecyclerView) findViewById(R.id.baby_list_recycler_view);
        rvBabyList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

        initCardView();
        /*
        status.setText(LollabyController.retrievedStatus);
        statusNm.setText(LollabyController.retrievedStatusNm);
        temperature.setText(LollabyController.retrievedTemperature);
        humidity.setText(LollabyController.retrievedHumidity);
        pressure.setText(LollabyController.retrievedPressure);
        */
    }


    public void initCardView(){



        babyListAdapter = new BabyListAdapter(MainActivity.this, LollabyController.babyListSource);
        rvBabyList.setAdapter(babyListAdapter);
    }
}
