package com.mydoctor.customer.utils.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

public class DarkStatusBar {
    public static void setDarkStatusBar(View view, Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            view.setSystemUiVisibility(0);
            activity.getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    public static void setLightStatusBar(View view, Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }
}
