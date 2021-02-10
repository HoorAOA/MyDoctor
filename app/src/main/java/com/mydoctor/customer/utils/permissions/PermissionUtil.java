package com.mydoctor.customer.utils.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    public static final int PERMISSION_REQUEST_CAMERA = 102;
    public static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 109;
    public static final int GENERAL_PERMISSION_REQUEST = 131;
    private static final int PERMISSION_REQUEST_CALENDAR = 101;
    private static final int PERMISSION_REQUEST_CONTACTS = 103;
    private static final int PERMISSION_REQUEST_PHONE_STATE = 106;
    private static final int PERMISSION_REQUEST_SENSORS = 107;
    private static final int PERMISSION_REQUEST_CALL = 108;
    private static final int PERMISSION_REQUEST_ACCESS_CHECKIN_PROPERTIES = 111;
    private static final int PERMISSION_REQUEST_ACCESS_COARSE_LOCATION = 112;
    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 113;
    private static final int PERMISSION_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS = 114;
    private static final int PERMISSION_REQUEST_ACCESS_NETWORK_STATE = 115;
    private static final int PERMISSION_REQUEST_ACCESS_NOTIFICATION_POLICY = 116;
    private static final int PERMISSION_REQUEST_ACCESS_WIFI_STATE = 117;
    private static final int PERMISSION_REQUEST_ACCOUNT_MANAGER = 118;
    private static final int PERMISSION_REQUEST_ADD_VOICEMAIL = 119;
    private static final int PERMISSION_REQUEST_BATTERY_STATS = 120;
    private static final int PERMISSION_REQUEST_BIND_ACCESSIBILITY_SERVICE = 121;
    private static final int PERMISSION_REQUEST_BIND_APPWIDGET = 122;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 123;
    private static final int PERMISSION_REQUEST_RECORD_AUDIO = 132;

    public static String getPermissionName(int permission) {

        String permissionName = "";
        switch (permission) {

            case PERMISSION_REQUEST_CALENDAR:
                permissionName = Manifest.permission.WRITE_CALENDAR;
                break;
            case PERMISSION_REQUEST_CAMERA:
                permissionName = Manifest.permission.CAMERA;
                break;
            case PERMISSION_REQUEST_CONTACTS:
                permissionName = Manifest.permission.WRITE_CONTACTS;
                break;
            case PERMISSION_REQUEST_PHONE_STATE:
                permissionName = Manifest.permission.READ_PHONE_STATE;
                break;
            case PERMISSION_REQUEST_SENSORS:
                permissionName = Manifest.permission.BODY_SENSORS;
                break;
            case PERMISSION_REQUEST_CALL:
                permissionName = Manifest.permission.CALL_PHONE;
                break;
            case PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE:
                permissionName = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                break;
            case PERMISSION_REQUEST_ACCESS_CHECKIN_PROPERTIES:
                permissionName = Manifest.permission.ACCESS_CHECKIN_PROPERTIES;
                break;
            case PERMISSION_REQUEST_ACCESS_COARSE_LOCATION:
                permissionName = Manifest.permission.ACCESS_COARSE_LOCATION;
                break;
            case PERMISSION_REQUEST_ACCESS_FINE_LOCATION:
                permissionName = Manifest.permission.ACCESS_FINE_LOCATION;
                break;
            case PERMISSION_REQUEST_ACCESS_LOCATION_EXTRA_COMMANDS:
                permissionName = Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS;
                break;
            case PERMISSION_REQUEST_ACCESS_NETWORK_STATE:
                permissionName = Manifest.permission.ACCESS_NETWORK_STATE;
                break;
            case PERMISSION_REQUEST_ACCESS_NOTIFICATION_POLICY:
                permissionName = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
                break;
            case PERMISSION_REQUEST_ACCESS_WIFI_STATE:
                permissionName = Manifest.permission.ACCESS_WIFI_STATE;
                break;
            case PERMISSION_REQUEST_ACCOUNT_MANAGER:
                permissionName = Manifest.permission.ACCOUNT_MANAGER;
                break;
            case PERMISSION_REQUEST_ADD_VOICEMAIL:
                permissionName = Manifest.permission.ADD_VOICEMAIL;
                break;
            case PERMISSION_REQUEST_BATTERY_STATS:
                permissionName = Manifest.permission.BATTERY_STATS;
                break;
            case PERMISSION_REQUEST_BIND_ACCESSIBILITY_SERVICE:
                permissionName = Manifest.permission.BIND_ACCESSIBILITY_SERVICE;
                break;
            case PERMISSION_REQUEST_BIND_APPWIDGET:
                permissionName = Manifest.permission.BIND_APPWIDGET;
                break;
            case PERMISSION_REQUEST_READ_CONTACTS:
                permissionName = Manifest.permission.READ_CONTACTS;
                break;
            case PERMISSION_REQUEST_RECORD_AUDIO:
                permissionName = Manifest.permission.RECORD_AUDIO;
                break;

            default:
                break;
        }

        return permissionName;

    }

    public void checkSelfPermission(Activity activity, String permissionName, int requestCode) {
        // Should we show an explanation?
        if (ContextCompat.checkSelfPermission(activity, permissionName) != PackageManager.PERMISSION_GRANTED && ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionName)) {

            //This is called if user has denied the permission before
            //In this case I am just asking the permission again
            ActivityCompat.requestPermissions(activity, new String[]{permissionName}, requestCode);

        }

    }


}
