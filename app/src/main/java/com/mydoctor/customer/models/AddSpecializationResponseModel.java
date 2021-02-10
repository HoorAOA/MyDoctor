package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

public class AddSpecializationResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("specialization")
    private String specialization;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
