package com.mydoctor.customer.utils.permissions;

import android.app.Activity;

interface AppPermissionsInterface {

    boolean requestPermissionList(Activity activity, int[] permissionCode, int requestCode);

    boolean requestPermissionList(Activity activity, String[] permissionCode, int requestCode);

    boolean requestPermission(Activity activity, int permissionCode);

    boolean requestPermission(Activity activity, String permissionName);

    void showPermissionDialog(Activity activity, AlertDialogProperties alertDialogProperties, String[] permissionNames);

    void closeDialog();
}
