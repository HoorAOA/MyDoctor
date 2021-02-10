package com.mydoctor.customer.models;


import android.content.Context;

public class GetContextModel {

    private static GetContextModel getContextModel;

    private Context context;

    private GetContextModel() {

    }

    public static GetContextModel getContextModel() {

        if (getContextModel == null) {

            getContextModel = new GetContextModel();
        }

        return getContextModel;
    }

    public void initialize(Context context) {
        this.context = context;
    }

    public Context getApplicationContext() {
        return context;
    }
}
