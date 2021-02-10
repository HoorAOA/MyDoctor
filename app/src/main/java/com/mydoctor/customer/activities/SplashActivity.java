package com.mydoctor.customer.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.mydoctor.customer.R;
import com.mydoctor.customer.delegates.CommunicationInterfaceAll;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Config;
import com.mydoctor.customer.utils.database.DbPreference;
import com.mydoctor.customer.utils.ui.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.mydoctor.customer.utils.AppConstants.CUSTOMER;
import static com.mydoctor.customer.utils.AppConstants.PATIENT;

public class SplashActivity extends AbstractActivity {

    private static final int INTIAL_TIME_IN_MILLIS = 3000;
    @BindView(R.id.cl_splash)
    ConstraintLayout clSplash;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        unbinder = ButterKnife.bind(this);
        setUI();

        //Translucent status and navigation bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        new Handler().postDelayed(this::callMetadataApi, INTIAL_TIME_IN_MILLIS);
    }

    private void setUI() {
//        clSplash.setBackgroundResource(R.drawable.sari_customer);
    }

    private void switchToNextActivity() {
        switchToActivity(SplashActivity.this, LoginActivity.class, null);
    }

    protected void callMetadataApi() {
        switchToNextActivity();
    }

    private void showDialog(String message, String action, String strNo, String strYes, String positiveAction, CommunicationInterfaceAll interfaceAll) {
        if (!activity.isFinishing()) {
            showCommonActionBottomSheet(strNo, strYes,
                    action, message, AppConstants.UPDATE_NOW, positiveAction, interfaceAll);
        }
    }

    private void exitFromApp() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * Metadata failure dialog
     */
    private void showMetaDataFailureDialog() {
        showCommonActionBottomSheet(getString(R.string.retry), getString(R.string.exit), AppConstants.META_DATA_API_FAILURE, getString(R.string.msg_app_init_failed),
                AppConstants.RETRY, AppConstants.EXIT, new MetaDataFailureDelegate());
    }

    /**
     * Network failure dialog
     */
    private void showMetadataNetworkFailureDialog(String message) {
        showCommonActionBottomSheet(getString(R.string.retry), getString(R.string.exit), AppConstants.FAILURE, message,
                AppConstants.RETRY, AppConstants.EXIT, new MetaDataFailureDelegate());
    }

    private void showVersionApiFailureDialog(String message) {
        showCommonActionBottomSheet(getString(R.string.retry), getString(R.string.exit), AppConstants.FAILURE, message,
                AppConstants.RETRY, AppConstants.EXIT, new VersionApiFailureDelegate());
    }

    private String getSessionKey() {
        return DbPreference.getString(activity, AppConstants.SESSION_KEY, "");
    }

    private boolean isTutorialSeen() {
        return DbPreference.getBoolean(activity, AppConstants.IS_TUTORIAL_SHOWN, false);
    }

    private class MetaDataFailureDelegate implements CommunicationInterfaceAll {
        private static final long serialVersionUID = 2013323586243908131L;

        @Override
        public void setCommunication(String message, String actionType, String response) {

            if (TextUtils.equals(response, AppConstants.RETRY)) {
//                callMetadataApi();
            } else {
                dismissCommonActionBottomSheet();
                exitFromApp();
            }
        }
    }

    private class VersionApiFailureDelegate implements CommunicationInterfaceAll {
        private static final long serialVersionUID = 5023333289558973820L;

        @Override
        public void setCommunication(String message, String actionType, String response) {

            if (TextUtils.equals(response, AppConstants.RETRY)) {
//                callVersionApi();
            } else {
                dismissCommonActionBottomSheet();
                exitFromApp();
            }
        }
    }
}
