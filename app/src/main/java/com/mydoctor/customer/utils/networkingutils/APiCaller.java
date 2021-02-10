package com.mydoctor.customer.utils.networkingutils;

import android.util.Log;

import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.application.ApplicationController;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.GetContextModel;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APiCaller {

    private static final int INTERNAL_SERVER_ERROR_STATUS_CODE = 500;
    private static APiCaller instance;

    public static synchronized APiCaller getInstance() {
        if (instance == null) {
            instance = new APiCaller();
        }
        return instance;
    }

    public <T> void getData(Supplier<Call<T>> callSupplier, final DataCallback<T> callback) {
        Call<T> call = callSupplier.get();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.body() != null) {
                    callback.onSuccess(response.body());
                    Log.e("TAG", "onResponse: " + response.body());
                    Log.d("onResponse: ", response.body().toString());
                    return;
                }
                try {
                    ErrorResponseModel errorResponseModel = null;
                    if (response.errorBody() != null) {
                        errorResponseModel = ErrorUtils.parseJsonArray(response.errorBody().string());
                        Log.d("errorResponseModel:::", new Gson().toJson(errorResponseModel));
                    }
                    callback.onError(errorResponseModel);
                } catch (Exception e) {
                    Logger.debug(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e("TAG", "onResponse: failure");
                if (t instanceof SocketTimeoutException || t instanceof IOException) {
                    ErrorResponseModel.getErrorResponseModel().setMessage("Please try again");
                    callback.onError(ErrorResponseModel.getErrorResponseModel());
                    return;
                }
                Log.d("errorMessage:::", String.valueOf(t.getMessage()));
                ErrorResponseModel.getErrorResponseModel().setMessage(t.getMessage());
                callback.onError(ErrorResponseModel.getErrorResponseModel());

            }
        });
    }
}
