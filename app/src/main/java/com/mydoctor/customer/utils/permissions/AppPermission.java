package com.mydoctor.customer.utils.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.mydoctor.customer.R;
import com.mydoctor.customer.models.GetContextModel;
import com.mydoctor.customer.utils.Config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static com.mydoctor.customer.utils.AppConstants.DOCTOR;
import static com.mydoctor.customer.utils.permissions.PermissionUtil.GENERAL_PERMISSION_REQUEST;
import static com.mydoctor.customer.utils.permissions.PermissionUtil.getPermissionName;

public class AppPermission implements AppPermissionsInterface {

    public static final int GRANTED = 0;
    public static final int DENIED = 1;
    public static final int BLOCKED_OR_NEVER_ASKED = 2;
    private static AppPermission appPermissionObject = new AppPermission();
    private PermissionAlertDialog twoButtonDialog;

    private AppPermission() {

    }

    public static AppPermission getInstance() {
        if (appPermissionObject == null) {
            appPermissionObject = new AppPermission();
        }
        return appPermissionObject;
    }

    @PermissionStatus
    public static int getPermissionStatus(Activity activity, String androidPermissionName) {
        return PermissionChecker.checkCallingOrSelfPermission(activity, androidPermissionName);
    }

    @Override
    public boolean requestPermissionList(Activity activity, int[] permissionCode, int requestCode) {
        boolean permissionFlag = false;
        ArrayList<String> permissionArray = new ArrayList<>();


        for (int aPermissionCode : permissionCode) {
            String permissionName = getPermissionName(aPermissionCode);
            if (ContextCompat.checkSelfPermission(GetContextModel.getContextModel().getApplicationContext(), permissionName) != PackageManager.PERMISSION_GRANTED) {
                permissionArray.add(permissionName);
                permissionFlag = true;
            }
        }
        if (!permissionArray.isEmpty()) {

            addToPermissionArray(activity, permissionArray, requestCode);
        }

        return permissionFlag;
    }

    private void addToPermissionArray(Activity activity, ArrayList<String> permissionArray, int requestCode) {
        String[] tempArray = new String[permissionArray.size()];
        permissionArray.toArray(tempArray);
        permissionArray.clear();
        requestPermissions(activity, tempArray,
                requestCode);
    }

    @Override
    public boolean requestPermissionList(Activity activity, String[] permissionCode, int requestCode) {
        boolean permissionFlag = false;
        ArrayList<String> permissionList = new ArrayList<>();
        for (String selectedPermissionName : permissionCode) {
            if (ContextCompat.checkSelfPermission(GetContextModel.getContextModel().getApplicationContext(), selectedPermissionName) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(selectedPermissionName);
                permissionFlag = true;
            }
        }
        if (!permissionList.isEmpty()) {

            addToPermissionArray(activity, permissionList, requestCode);
        }

        return permissionFlag;
    }

    @Override
    public boolean requestPermission(Activity activity, int permissionCode) {
        boolean permissionFlag = false;


        String permissionName = getPermissionName(permissionCode);

        if (ContextCompat.checkSelfPermission(GetContextModel.getContextModel().getApplicationContext(), permissionName) != PackageManager.PERMISSION_GRANTED) {
            permissionFlag = true;
        }

        if (permissionFlag) {
            String[] tempArray = {permissionName};
            requestPermissions(activity, tempArray,
                    permissionCode);
        }

        return permissionFlag;
    }

    @Override
    public boolean requestPermission(Activity activity, String permissionName) {
        boolean permissionFlag = false;

        if (ContextCompat.checkSelfPermission(GetContextModel.getContextModel().getApplicationContext(), permissionName) != PackageManager.PERMISSION_GRANTED) {
            permissionFlag = true;
        }

        if (permissionFlag) {
            String[] tempArray = {permissionName};
            requestPermissions(activity, tempArray,
                    GENERAL_PERMISSION_REQUEST);
        }

        return permissionFlag;
    }

    @Override
    public void showPermissionDialog(Activity activity, AlertDialogProperties alertDialogProperties, String[] permissionNames) {

        showDialog(activity, alertDialogProperties, permissionNames);
    }

    @Override
    public void closeDialog() {
        if (twoButtonDialog != null && twoButtonDialog.isShowing()) {
            twoButtonDialog.dismiss();
        }
    }

    private void showDialog(Activity activity, AlertDialogProperties alertDialogProperties, String[] permissionNames) {

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < permissionNames.length; i++) {

            switch (permissionNames[i]) {
                case Manifest.permission.WRITE_CALENDAR:
                    message.append(activity.getResources().getString(R.string.calender_permission));
                    break;
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    if (Config.USER_ROLE.equalsIgnoreCase(DOCTOR)) {
                        message.append(activity.getResources().getString(R.string.location_permission_driver));
                    } else {
                        message.append(activity.getResources().getString(R.string.location_permission));
                    }
                    break;
                case Manifest.permission.READ_CONTACTS:
                case Manifest.permission.WRITE_CONTACTS:
                    message.append(activity.getResources().getString(R.string.contacts_permission));
                    break;
                case Manifest.permission.READ_PHONE_STATE:
                    message.append(activity.getResources().getString(R.string.phone_permission));
                    break;
                case Manifest.permission.CAMERA:
                    message.append(activity.getResources().getString(R.string.camera_permission));
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    message.append(activity.getResources().getString(R.string.storage_permission));
                    break;
                case Manifest.permission.CALL_PHONE:
                    message.append(activity.getResources().getString(R.string.call_permission));
                    break;
                case Manifest.permission.BODY_SENSORS:
                    message.append(activity.getResources().getString(R.string.sensor_permission));
                    break;
                case Manifest.permission.ACCESS_NETWORK_STATE:
                case Manifest.permission.CHANGE_NETWORK_STATE:
                    message.append(activity.getResources().getString(R.string.network_permission));
                    break;
                case Manifest.permission.ACCESS_WIFI_STATE:
                case Manifest.permission.CHANGE_WIFI_STATE:
                    message.append(activity.getResources().getString(R.string.wifi_permission));
                    break;
                case Manifest.permission.BATTERY_STATS:
                case Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS:
                    message.append(activity.getResources().getString(R.string.battery_permission));
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    message.append(activity.getResources().getString(R.string.record_audio_permission));
                    break;
                default:
                    break;

            }

            String bottomSpace = i < (permissionNames.length - 1) ? "<br/><br/>" : "";
            message.append(bottomSpace);
        }
        alertDialogProperties.setMessage(message.toString());

        if (activity == null || activity.isFinishing()) {
            return;
        }

        twoButtonDialog = new PermissionAlertDialog(activity, alertDialogProperties);
        twoButtonDialog.show();
        twoButtonDialog.setCancelable(alertDialogProperties.isCancelable());
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GRANTED, DENIED, BLOCKED_OR_NEVER_ASKED})
    public @interface PermissionStatus {
    }
}
