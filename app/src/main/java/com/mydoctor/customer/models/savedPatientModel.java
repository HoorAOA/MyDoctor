package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

import java.util.List;

public class savedPatientModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("patientName")
    private String patientName;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("patientPhoneNumber")
    private String patientPhoneNumber;

    @SerializedName("__v")
    private String __v;

    @SerializedName("medicalExaminations")
    private List<medicalExaminationsModel> medicalExaminations;

}
