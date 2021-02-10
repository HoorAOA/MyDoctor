package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

public class CommonMessageModel {

    @SerializedName("message")
    private String message;

    @SerializedName("messageCode")
    private String messageCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
