package com.mydoctor.customer.utils;

import com.mydoctor.customer.BuildConfig;

public class Config {

    public static final String BUILD_TYPE = BuildConfig.BUILD_TYPE;
    public static final String VERSION_NAME = BuildConfig.VERSION_NAME;
    public static final int VERSION_CODE = BuildConfig.VERSION_CODE;
    public static final String URL_PATTERN = "%s/%s/%s";
    public static final String USER_ROLE = BuildConfig.USER_ROLE;
    public static final String APPLICATION_ID = BuildConfig.APPLICATION_ID;
    public static final String BASE_URL = BuildConfig.BASE_URL;


    private Config() {
    }


}
