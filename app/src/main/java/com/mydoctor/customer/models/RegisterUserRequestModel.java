package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;


public class RegisterUserRequestModel {

    @SerializedName("firstName")
    private String registerUserRequestFirstName;

    @SerializedName("lastName")
    private String registerUserRequestLastName;

    @SerializedName("numCountryCode")
    private String registerUserRequestNumCountryCode;

    @SerializedName("password")
    private String registerUserRequestPassword;

    @SerializedName("phoneNum")
    private String registerUserRequestPhoneNum;

    @SerializedName("roleCode")
    private String registerUserRequestRoleCode;

    @SerializedName("tenantId")
    private String registerUserRequestTenantId;

    @SerializedName("facebookId")
    private String registerUserRequestFacebookId;

    @SerializedName("googleId")
    private String googleId;

    @SerializedName("email")
    private String registerUserRequestEmail;

    @SerializedName("deviceDetails")
    private DeviceDetailModel registerUserRequestDeviceDetails;

    public String getRegisterUserRequestFirstName() {
        return registerUserRequestFirstName;
    }

    public void setRegisterUserRequestFirstName(String registerUserRequestFirstName) {
        this.registerUserRequestFirstName = registerUserRequestFirstName;
    }

    public String getRegisterUserRequestLastName() {
        return registerUserRequestLastName;
    }

    public void setRegisterUserRequestLastName(String registerUserRequestLastName) {
        this.registerUserRequestLastName = registerUserRequestLastName;
    }

    public String getRegisterUserRequestNumCountryCode() {
        return registerUserRequestNumCountryCode;
    }

    public void setRegisterUserRequestNumCountryCode(String registerUserRequestNumCountryCode) {
        this.registerUserRequestNumCountryCode = registerUserRequestNumCountryCode;
    }

    public String getRegisterUserRequestPassword() {
        return registerUserRequestPassword;
    }

    public void setRegisterUserRequestPassword(String registerUserRequestPassword) {
        this.registerUserRequestPassword = registerUserRequestPassword;
    }

    public String getRegisterUserRequestPhoneNum() {
        return registerUserRequestPhoneNum;
    }

    public void setRegisterUserRequestPhoneNum(String registerUserRequestPhoneNum) {
        this.registerUserRequestPhoneNum = registerUserRequestPhoneNum;
    }

    public String getRegisterUserRequestRoleCode() {
        return registerUserRequestRoleCode;
    }

    public void setRegisterUserRequestRoleCode(String registerUserRequestRoleCode) {
        this.registerUserRequestRoleCode = registerUserRequestRoleCode;
    }

    public String getRegisterUserRequestTenantId() {
        return registerUserRequestTenantId;
    }

    public void setRegisterUserRequestTenantId(String registerUserRequestTenantId) {
        this.registerUserRequestTenantId = registerUserRequestTenantId;
    }

    public String getRegisterUserRequestFacebookId() {
        return registerUserRequestFacebookId;
    }

    public void setRegisterUserRequestFacebookId(String registerUserRequestFacebookId) {
        this.registerUserRequestFacebookId = registerUserRequestFacebookId;
    }

    public String getRegisterUserRequestEmail() {
        return registerUserRequestEmail;
    }

    public void setRegisterUserRequestEmail(String registerUserRequestEmail) {
        this.registerUserRequestEmail = registerUserRequestEmail;
    }

    public DeviceDetailModel getRegisterUserRequestDeviceDetails() {
        return registerUserRequestDeviceDetails;
    }

    public void setRegisterUserRequestDeviceDetails(DeviceDetailModel registerUserRequestDeviceDetails) {
        this.registerUserRequestDeviceDetails = registerUserRequestDeviceDetails;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
