package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllClinicsResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("clinics")
    private List<ClinicsModel> clinics;

    public List<ClinicsModel> getClinics() {
        return clinics;
    }

    public void setClinics(List<ClinicsModel> clinics) {
        this.clinics = clinics;
    }

}
