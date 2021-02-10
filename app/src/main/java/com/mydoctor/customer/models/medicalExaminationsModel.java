package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

public class medicalExaminationsModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("examName")
    private String examName;

    @SerializedName("examResult")
    private String examResult;

}
