package com.mydoctor.customer.utils.permissions;

import android.app.Activity;


public interface PermissionCommunicationInterface {

    void setCommunication(Activity activity, String[] permissionNames, String actionType);
}
