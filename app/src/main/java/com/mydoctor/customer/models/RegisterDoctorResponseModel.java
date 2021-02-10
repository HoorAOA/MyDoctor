package com.mydoctor.customer.models;

import android.content.Context;

import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;
import com.google.gson.annotations.SerializedName;

public class RegisterDoctorResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("token")
    private String token;

    @SerializedName("doctor")
    private DoctorModel doctor;

    public static void putSessionToken(RegisterDoctorResponseModel registerResponseModel, Context context) {
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

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel user) {
        this.doctor = doctor;
    }
}
