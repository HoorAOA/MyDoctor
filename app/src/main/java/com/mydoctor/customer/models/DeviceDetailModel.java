package com.mydoctor.customer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceDetailModel implements Parcelable {


    private String appVersion;
    private String deviceId;
    private String deviceType;
    private String notificationToken;
    private String os;
    private String osVersion;
    private String deviceName;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appVersion);
        dest.writeString(this.deviceId);
        dest.writeString(this.deviceType);
        dest.writeString(this.notificationToken);
        dest.writeString(this.os);
        dest.writeString(this.osVersion);
        dest.writeString(this.deviceName);
    }

    public DeviceDetailModel() {
    }

    protected DeviceDetailModel(Parcel in) {
        this.appVersion = in.readString();
        this.deviceId = in.readString();
        this.deviceType = in.readString();
        this.notificationToken = in.readString();
        this.os = in.readString();
        this.osVersion = in.readString();
        this.deviceName = in.readString();
    }

    public static final Creator<DeviceDetailModel> CREATOR = new Creator<DeviceDetailModel>() {
        @Override
        public com.mydoctor.customer.models.DeviceDetailModel createFromParcel(Parcel source) {
            return new com.mydoctor.customer.models.DeviceDetailModel(source);
        }

        @Override
        public com.mydoctor.customer.models.DeviceDetailModel[] newArray(int size) {
            return new com.mydoctor.customer.models.DeviceDetailModel[size];
        }
    };
}
