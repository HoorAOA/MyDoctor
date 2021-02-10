package com.mydoctor.customer.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ErrorResponseModel implements Parcelable {

    public static final Creator<com.mydoctor.customer.models.ErrorResponseModel> CREATOR = new Creator<com.mydoctor.customer.models.ErrorResponseModel>() {
        @Override
        public com.mydoctor.customer.models.ErrorResponseModel createFromParcel(Parcel source) {
            return new com.mydoctor.customer.models.ErrorResponseModel(source);
        }

        @Override
        public com.mydoctor.customer.models.ErrorResponseModel[] newArray(int size) {
            return new com.mydoctor.customer.models.ErrorResponseModel[size];
        }
    };
    private static com.mydoctor.customer.models.ErrorResponseModel errorResponseModel = null;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;

    private ErrorResponseModel() {

    }

    protected ErrorResponseModel(Parcel in) {
        this.status = in.readString();
        this.message = in.readString();
    }

    public static com.mydoctor.customer.models.ErrorResponseModel getErrorResponseModel() {
        if (errorResponseModel == null) {
            errorResponseModel = new com.mydoctor.customer.models.ErrorResponseModel();
        }

        return errorResponseModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.message);
    }
}
