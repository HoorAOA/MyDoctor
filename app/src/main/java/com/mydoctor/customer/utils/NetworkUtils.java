package com.mydoctor.customer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    private static com.mydoctor.customer.utils.NetworkUtils instance = null;

    private NetworkUtils() {
        // Exists only to defeat instantiation.
    }

    public static com.mydoctor.customer.utils.NetworkUtils getInstance() {
        if (instance == null) {
            instance = new com.mydoctor.customer.utils.NetworkUtils();
        }
        return instance;
    }

    public static boolean isNetworkAvailable(Context context) {

        if(context != null) {

            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {

                NetworkInfo[] info = connectivity.getAllNetworkInfo();

                if (info != null) {

                    for (NetworkInfo anInfo : info) {

                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {

                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }

}
