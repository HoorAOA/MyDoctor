package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPatientMedicalsResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("medicalExaminations")
    private List<MedicalModel> medicalExaminations;

    public List<MedicalModel> getMedicalExaminations() {
        return medicalExaminations;
    }

    public void setMedicalExaminations(List<MedicalModel> medicalExaminations) {
        this.medicalExaminations = medicalExaminations;
    }

}
