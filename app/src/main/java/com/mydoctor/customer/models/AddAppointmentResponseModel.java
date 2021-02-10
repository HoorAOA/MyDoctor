package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddAppointmentResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("appointment")
    private AppointmentsModel appointment;

    public AppointmentsModel getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentsModel clinics) {
        this.appointment = appointment;
    }

}
