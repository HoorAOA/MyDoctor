package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

public class RegisterDoctorRequestModel {

    @SerializedName("doctorFirstName")
    private String doctorFirstName;

    @SerializedName("doctorLastName")
    private String doctorLastName;

    @SerializedName("password")
    private String password;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("practicingFrom")
    private String practicingFrom;

    @SerializedName("doctorSpecialization")
    private String doctorSpecialization;

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPracticingFrom() {
        return practicingFrom;
    }

    public void setPracticingFrom(String practicingFrom) {
        this.practicingFrom = practicingFrom;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }
}
