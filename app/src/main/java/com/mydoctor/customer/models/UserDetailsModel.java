package com.mydoctor.customer.models;

import com.google.gson.annotations.SerializedName;

public class UserDetailsModel {

    @SerializedName("id")
    private String id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("userType")
    private String userType;

    @SerializedName("referralCode")
    private String referralCode;

    @SerializedName("profileImage")
    private String profileImage;

    @SerializedName("dutyStatus")
    private boolean dutyStatus;

    @SerializedName("referralCredit")
    private String referralCredit;

    @SerializedName("tenantId")
    private String tenantId;

    @SerializedName("taxInformation")
    private String taxInformation;

    @SerializedName("zipCode")
    private String zipCode;

    @SerializedName("credit")
    private String credit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(boolean dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public String getReferralCredit() {
        return referralCredit;
    }

    public void setReferralCredit(String referralCredit) {
        this.referralCredit = referralCredit;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTaxInformation() {
        return taxInformation;
    }

    public void setTaxInformation(String taxInformation) {
        this.taxInformation = taxInformation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }


}
