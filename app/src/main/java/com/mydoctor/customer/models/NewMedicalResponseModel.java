package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

import java.util.List;

public class NewMedicalResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("savedPatient")
    private savedPatientModel savedPatient;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public savedPatientModel getSavedPatient() {
        return savedPatient;
    }

    public void setSavedPatient(savedPatientModel savedPatient) {
        this.savedPatient = savedPatient;
    }

}
