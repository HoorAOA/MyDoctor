package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommonSuccessMessageModel {

    @SerializedName("general")
    private List<CommonMessageModel> general;

    public List<CommonMessageModel> getGeneral() {
        return general;
    }

    public void setGeneral(List<CommonMessageModel> general) {
        this.general = general;
    }
}
