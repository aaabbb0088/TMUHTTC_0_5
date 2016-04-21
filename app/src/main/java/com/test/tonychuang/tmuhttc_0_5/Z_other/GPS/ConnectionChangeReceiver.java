package com.test.tonychuang.tmuhttc_0_5.Z_other.GPS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by TonyChuang on 2016/4/20.
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    private String TAG = "ConnectionChangeReceiver";
    private String provider;

    public ConnectionChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "網絡狀態改變");

        boolean success = false;

        ConnectivityManager CM = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (CM == null) {
            success = false;
        } else {
            NetworkInfo info = CM.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (!info.isAvailable()) {
                    success = false;
                } else {
                    success = true;
                }
            }
        }

        if (success) {
//            Toast.makeText(context, "your_network_has_connected", Toast.LENGTH_LONG).show();
            MyGPSRecordService.provider = LocationManager.NETWORK_PROVIDER;
        } else {
//            Toast.makeText(context, "your_network_has_disconnected", Toast.LENGTH_LONG).show();
            MyGPSRecordService.provider = LocationManager.GPS_PROVIDER;
        }
    }
}
