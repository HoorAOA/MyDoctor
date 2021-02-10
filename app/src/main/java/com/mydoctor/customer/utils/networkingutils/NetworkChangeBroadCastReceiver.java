package com.mydoctor.customer.utils.networkingutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;

public class NetworkChangeBroadCastReceiver extends BroadcastReceiver {


//    private LocalBroadcastManager localBroadcastManager;

    @Override
    public void onReceive(Context context, Intent intent) {

//        localBroadcastManager = LocalBroadcastManager.getInstance(context);

        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                Logger.debug("Network Connected");
//                sendBroadcast();

            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                Logger.debug("Network Disconnected");
            }
        }
    }

//    private void sendBroadcast() {
//        Intent localIntent = new Intent(AppConstants.BROADCAST_MANAGER_FOR_NETWORK_AVAILABLE);
//        localBroadcastManager.sendBroadcast(localIntent);
//    }
}
