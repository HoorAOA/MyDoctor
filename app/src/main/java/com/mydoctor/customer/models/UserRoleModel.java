package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

public class UserRoleModel {

    @SerializedName("id")
    private String userRoleId;

    @SerializedName("roleName")
    private String userRoleName;

    @SerializedName("roleCode")
    private String userRoleCode;

    @SerializedName("tenantId")
    private String userTenantId;

    @SerializedName("writePermission")
    private boolean writePermission;

    @SerializedName("activated")
    private boolean activated;

    @SerializedName("ios")
    private boolean ios;

    @SerializedName("web")
    private boolean web;

    @SerializedName("android")
    private boolean android;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleCode() {
        return userRoleCode;
    }

    public void setUserRoleCode(String userRoleCode) {
        this.userRoleCode = userRoleCode;
    }

    public String getUserTenantId() {
        return userTenantId;
    }

    public void setUserTenantId(String userTenantId) {
        this.userTenantId = userTenantId;
    }

    public boolean isWritePermission() {
        return writePermission;
    }

    public void setWritePermission(boolean writePermission) {
        this.writePermission = writePermission;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isIos() {
        return ios;
    }

    public void setIos(boolean ios) {
        this.ios = ios;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        this.android = android;
    }

}
