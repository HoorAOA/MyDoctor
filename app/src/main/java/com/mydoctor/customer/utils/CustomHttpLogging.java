package com.mydoctor.customer.utils;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import okhttp3.logging.HttpLoggingInterceptor;

public class CustomHttpLogging implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        final String logName = "OkHttp";
        if (!message.startsWith("{")) {
            Log.d(logName, message);
            return;
        }
        try {
            String prettyPrintJson = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(message));
            Log.d(logName, prettyPrintJson);
        } catch (Exception m) {
            Log.d(logName, message);
        }
    }
}