package com.mydoctor.customer.application;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.mydoctor.customer.activities.LoginActivity;
import com.mydoctor.customer.models.GetContextModel;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.database.DbPreference;
import com.mydoctor.customer.utils.networkingutils.NetworkChangeBroadCastReceiver;
import com.mydoctor.customer.utils.ui.LocaleUtils;

import java.util.Locale;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.mydoctor.customer.utils.AppConstants.DB_NAME;
import static com.mydoctor.customer.utils.AppConstants.DB_NAME_WITH_VERSION;
import static com.mydoctor.customer.utils.AppConstants.DOCTOR;

public class ApplicationController extends Application {

    private static com.mydoctor.customer.application.ApplicationController applicationController;

    private boolean isLocationServiceStarted = false;

    public ApplicationController() {

        Logger.debug("default constructor");
    }

    public static com.mydoctor.customer.application.ApplicationController getApplicationController() {

        if (applicationController == null) {

            applicationController = new com.mydoctor.customer.application.ApplicationController();
        }

        return applicationController;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        Logger.debug("onConfigurationChanged");

        registerNetworkChangeReceiver();

        changeLanguage();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        GetContextModel.getContextModel().initialize(this);

        changeLanguage();
    }

    private void changeLanguage() {
        Locale locale = Resources.getSystem().getConfiguration().locale;
        Configuration config = new Configuration();
        config.locale = locale;
        LocaleUtils.setLocale(locale);
        LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());
    }

    public void onLogoutFinish(int statusCode) {
        if (statusCode == AppConstants.STATUS_CODE_SESSION_EXPIRED) {

            try {
                // delete user data
                DbPreference.remove(GetContextModel.getContextModel().getApplicationContext(), AppConstants.SESSION_KEY);
                DbPreference.remove(GetContextModel.getContextModel().getApplicationContext(), AppConstants.USER_KEY);

                Intent intent = new Intent(GetContextModel.getContextModel().getApplicationContext(), LoginActivity.class);
                intent.putExtra(AppConstants.IS_SESSION_EXPIRED, true);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                GetContextModel.getContextModel().getApplicationContext().startActivity(intent);

            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
        Logger.debug("on low memory");

    }

    @Override
    public void onTerminate() {

        super.onTerminate();

        Logger.debug("on termination of application");

    }

    private void registerNetworkChangeReceiver() {
        registerReceiver(new NetworkChangeBroadCastReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}