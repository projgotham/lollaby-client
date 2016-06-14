package com.teamlollaby.lollaby;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.teamlollaby.lollaby.adapter.BabyListAdapter;
import com.teamlollaby.lollaby.data.BabyListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinGu on 2015-10-28.
 */
public class LollabyController extends Application implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static Node clientNode;
    public static GoogleApiClient clientGoogleApi;
    private static final String LOLLABY_PATH = "/lollaby-path-wear";
    private static final String LOLLABY_NOISE_PATH = "/lollaby-path-wear-noise";
    private static boolean resolveError = false;
    public static Boolean serviceIndicator = false;
    // public static Boolean startIndicator = true;

    /*
    public static String retrievedStatus = "NaN";
    public static String retrievedStatusNm = "NaN";
    public static String retrievedTemperature = "NaN";
    public static String retrievedHumidity = "NaN";
    public static String retrievedPressure = "NaN";
    public static String retrievedNoise = "NaN";
    */

    public static Boolean alarmIndicator = false;

    @Override
    public void onCreate() {
        super.onCreate();

        clientGoogleApi = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        if (!resolveError) {
            clientGoogleApi.connect();
            Log.e("Connection", "Successful");
        }

    }

    public static void sendMessage(String path) {

        if (clientNode != null && clientGoogleApi != null && clientGoogleApi.isConnected()) {
            Log.e("Successful", "Successful");
            Wearable.MessageApi.sendMessage(
                    clientGoogleApi, clientNode.getId(), path, null).setResultCallback(

                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                            if (!sendMessageResult.getStatus().isSuccess()) {
                                Log.e("TAG", "Failed to send message with status code: " + sendMessageResult.getStatus().getStatusCode());
                            }
                        }
                    }
            );
        } else {
            // Pass
        }
    }

    private void resolveNode() {

        Wearable.NodeApi.getConnectedNodes(clientGoogleApi).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                for (Node node : nodes.getNodes()) {
                    clientNode = node;
                }
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        resolveNode();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    // Initialize BabyListData Array
    public static List<BabyListData> babyListSource = new ArrayList<BabyListData>();
    public static BabyListAdapter babyListAdapter = null;
    public static ArrayList<String> babyListSourceToWear = new ArrayList<String>();

    public static void notifyBabyDataSetChanged() {
        if (babyListAdapter != null) {
            babyListAdapter.notifyDataSetChanged();
        }
    }

    public static BabyListData currentBabyData = null;

}
