package com.mydoctor.customer.utils.networkingutils;

import android.text.TextUtils;

import com.mydoctor.customer.models.ErrorResponseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorUtils {

    private ErrorUtils() {
        throw new IllegalStateException("ErrorUtils class");
    }

    public static ErrorResponseModel parseJsonArray(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            for (int i = 0; i < jsonObject.length(); i++) {
                if (!TextUtils.isEmpty(jsonObject.getString("message"))) {
                    ErrorResponseModel.getErrorResponseModel().setMessage(jsonObject.getString("message"));
                }

                if (!TextUtils.isEmpty(jsonObject.getString("status"))) {
                    ErrorResponseModel.getErrorResponseModel().setStatus(jsonObject.getString("status"));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return ErrorResponseModel.getErrorResponseModel();
    }
}
