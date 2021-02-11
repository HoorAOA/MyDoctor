package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicalModel {

    @SerializedName("examName")
    private String examName;

    @SerializedName("examResult")
    private String examResult;

    public String getExamName() {
        return examName;
    }

    public String getExamResult() {
        return examResult;
    }

}
