package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

public class RegisterPatientResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("token")
    private String token;

    @SerializedName("userInfo")
    private UserModel userInfo;

    public static void putSessionToken(RegisterPatientResponseModel registerResponseModel, Context context) {
        DbPreference.putString(context, AppConstants.SESSION_KEY, registerResponseModel.getToken());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return userInfo;
    }

    public void setUser(UserModel userInfo) {
        this.userInfo = userInfo;
    }
}
