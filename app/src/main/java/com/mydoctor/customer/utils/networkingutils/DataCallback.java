package com.mydoctor.customer.utils.networkingutils;


import com.mydoctor.customer.models.ErrorResponseModel;

public interface DataCallback<T> {

    void onSuccess(T response);

    void onError(ErrorResponseModel errorResponse);


}
