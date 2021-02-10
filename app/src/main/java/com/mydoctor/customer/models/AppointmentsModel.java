package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

public class AppointmentsModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("clinicName")
    private String clinicName;

    @SerializedName("patientName")
    private String patientName;

    @SerializedName("patientPhoneNumber")
    private String patientPhoneNumber;

    @SerializedName("appointmentDate")
    private String appointmentDate;

    @SerializedName("appointmentKind")
    private String appointmentKind;

    @SerializedName("__v")
    private String __v;

    public String getId() {
        return _id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentKind() {
        return appointmentKind;
    }

}
