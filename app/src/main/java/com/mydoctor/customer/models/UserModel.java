package com.mydoctor.customer.models;

import android.content.Context;

import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class UserModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

//    public static List<UserRoleModel> fetchUserRolesModelList(Context context) {
//        String values = DbPreference.getString(context, AppConstants.USER_KEY, null);
//
//        com.mydoctor.customer.models.UserModel userModel = new Gson().fromJson(values, new TypeToken<UserModel>() {
//        }.getType());
//
//        return userModel.getRoles();
//    }
//
    public static UserModel fetchUserModel(Context context) {
        String values = DbPreference.getString(context, AppConstants.USER_KEY, null);
        return new Gson().fromJson(values, new TypeToken<UserModel>() {
        }.getType());
    }
//
//    public static UserDetailsModel fetchUserDetails(Context context) {
//
//        com.mydoctor.customer.models.UserModel userModel = fetchUser(context);
//
//        return userModel.getUserDetailsModel();
//    }
//
    public static UserModel fetchUser(Context context) {
        String values = DbPreference.getString(context, AppConstants.USER_KEY, null);
        return new Gson().fromJson(values, new TypeToken<UserModel>() {
        }.getType());
    }

//    public static String getUserId(Context context) {
//
//        UserDetailsModel mUserDetailsModel = fetchUserDetails(context);
//        return mUserDetailsModel.getUserId();
//    }
//
    public static void putUser(UserModel userModel, Context context) {
        String userData = new Gson().toJson(userModel);
        DbPreference.putString(context, AppConstants.USER_KEY, userData);
    }

    public String getUserId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
