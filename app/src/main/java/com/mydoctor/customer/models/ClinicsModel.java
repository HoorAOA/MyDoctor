package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

import java.util.List;

public class ClinicsModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("clinicName")
    private String clinicName;

    @SerializedName("clinicStreetAddress")
    private String clinicStreetAddress;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("doctors")
    private List<DoctorModel> doctors;

    @SerializedName("appointments")
    private List<AppointmentsModel> appointments;

    @SerializedName("specialization")
    private List<String> specialization;

    @SerializedName("__v")
    private String __v;

    public String getClinicName() {
        return clinicName;
    }

}
