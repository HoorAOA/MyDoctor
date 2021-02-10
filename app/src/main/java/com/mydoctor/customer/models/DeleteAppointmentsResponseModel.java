package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeleteAppointmentsResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("deletedAppointment")
    private AppointmentsModel deletedAppointment;

    public AppointmentsModel getDeletedAppointment() {
        return deletedAppointment;
    }

    public void setDeletedAppointment(AppointmentsModel deletedAppointment) {
        this.deletedAppointment = deletedAppointment;
    }

}
