package com.mydoctor.customer.models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.database.DbPreference;

public class DoctorModel {

    @SerializedName("_id")
    private String _id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

//    public static List<UserRoleModel> fetchUserRolesModelList(Context context) {
//        String values = DbPreference.getString(context, AppConstants.USER_KEY, null);
//
//        com.mydoctor.customer.models.UserModel userModel = new Gson().fromJson(values, new TypeToken<UserModel>() {
//        }.getType());
//
//        return userModel.getRoles();
//    }
//
    public static DoctorModel fetchUserModel(Context context) {

        String values = DbPreference.getString(context, AppConstants.USER_KEY, null);


        return new Gson().fromJson(values, new TypeToken<DoctorModel>() {
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
    public static DoctorModel fetchUser(Context context) {
        String values = DbPreference.getString(context, AppConstants.USER_KEY, null);

        return new Gson().fromJson(values, new TypeToken<DoctorModel>() {
        }.getType());
    }
//
//    public static String getUserId(Context context) {
//
//        UserDetailsModel mUserDetailsModel = fetchUserDetails(context);
//        return mUserDetailsModel.getUserId();
//    }
//
    public static void putDoctor(DoctorModel userModel, Context context) {
        String userData = new Gson().toJson(userModel);
        DbPreference.putString(context, AppConstants.USER_KEY, userData);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
