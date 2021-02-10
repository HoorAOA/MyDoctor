package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

public class NewMedicalRequestModel {

    @SerializedName("examName")
    private String examName;

    @SerializedName("examResult")
    private String examResult;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

}
