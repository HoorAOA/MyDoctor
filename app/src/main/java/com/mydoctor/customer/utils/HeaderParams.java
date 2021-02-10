package com.mydoctor.customer.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.mydoctor.customer.activities.LoginActivity;
import com.mydoctor.customer.models.GetContextModel;
import com.mydoctor.customer.models.RegisterDoctorResponseModel;
import com.mydoctor.customer.utils.database.DbPreference;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.mydoctor.customer.utils.AppConstants.ACCEPT_LANGUAGE;
import static com.mydoctor.customer.utils.AppConstants.ACCEPT_LANGUAGE_VALUE_AR_SD;
import static com.mydoctor.customer.utils.AppConstants.ACCEPT_LANGUAGE_VALUE_EN;
import static com.mydoctor.customer.utils.AppConstants.CONTENT_TYPE;
import static com.mydoctor.customer.utils.AppConstants.CONTENT_TYPE_VALUE;
import static com.mydoctor.customer.utils.AppConstants.CONTENT_TYPE_VALUE_HTML;
import static com.mydoctor.customer.utils.AppConstants.SESSION_KEY;

public class HeaderParams {

    private HeaderParams() {
        throw new IllegalStateException("Header class");
    }

    public static Map<String, String> addHeaderParams() {

        Map<String, String> headerHashMap = new HashMap<>();

        String languageTag = Locale.getDefault().toLanguageTag();

        if (!TextUtils.equals(languageTag, ACCEPT_LANGUAGE_VALUE_AR_SD)) {
            languageTag = ACCEPT_LANGUAGE_VALUE_EN;
        }

        headerHashMap.put(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        headerHashMap.put(ACCEPT_LANGUAGE, languageTag);
        return headerHashMap;

    }

    public static Map<String, String> addHeaderParamsSessionKey() {

        Map<String, String> headerHashMap = new HashMap<>();


        String languageTag = Locale.getDefault().toLanguageTag();

        if (!TextUtils.equals(languageTag, ACCEPT_LANGUAGE_VALUE_AR_SD)) {
            languageTag = ACCEPT_LANGUAGE_VALUE_EN;
        }

        headerHashMap.put(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        headerHashMap.put(ACCEPT_LANGUAGE, languageTag);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(GetContextModel.getContextModel().getApplicationContext());
        String sessionKey = sharedPreferences.getString(AppConstants.SESSION_KEY, "");

//        String sessionKey = DbPreference.getString(GetContextModel.getContextModel().getApplicationContext(), AppConstants.SESSION_KEY, "");
        if (!TextUtils.isEmpty(sessionKey)) {

            headerHashMap.put(SESSION_KEY, "Bearer" + " " + sessionKey);
            Logger.debug("Session Key  " + sessionKey);

        }

        return headerHashMap;

    }

    public static Map<String, String> addHeaderParamsMultipart() {

        Map<String, String> headerHashMap = new HashMap<>();

        String languageTag = Locale.getDefault().toLanguageTag();

        if (!TextUtils.equals(languageTag, ACCEPT_LANGUAGE_VALUE_AR_SD)) {
            languageTag = ACCEPT_LANGUAGE_VALUE_EN;
        }

        headerHashMap.put(ACCEPT_LANGUAGE, languageTag);

        String sessionKey = DbPreference.getString(GetContextModel.getContextModel().getApplicationContext(), AppConstants.SESSION_KEY, "");
        if (!TextUtils.isEmpty(sessionKey)) {

            headerHashMap.put(SESSION_KEY, "Bearer" + " " + sessionKey);

        }
        return headerHashMap;

    }

    public static Map<String, String> addHeaderParamsHtmlData() {

        Map<String, String> headerHashMap = new HashMap<>();

        headerHashMap.put(CONTENT_TYPE, CONTENT_TYPE_VALUE_HTML);

        return headerHashMap;

    }


}
