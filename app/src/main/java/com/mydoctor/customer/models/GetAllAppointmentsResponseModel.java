package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllAppointmentsResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("appointments")
    private List<AppointmentsModel> appointments;

    public List<AppointmentsModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsModel> appointments) {
        this.appointments = appointments;
    }

}
