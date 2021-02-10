package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

public class AddAppointmentRequestModel {

    @SerializedName("clinicName")
    private String clinicName;

    @SerializedName("appointmentDate")
    private String appointmentDate;

    @SerializedName("appointmentKind")
    private String appointmentKind;

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getAppointmentDate() {  return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentKind() {
        return appointmentKind;
    }

    public void setAppointmentKind(String appointmentKind) {
        this.appointmentKind = appointmentKind;
    }

}
