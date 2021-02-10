package com.mydoctor.customer.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.activities.doctor.DoctorMenuActivity;
import com.mydoctor.customer.activities.doctor.RegisterDoctorActivity;
import com.mydoctor.customer.activities.patient.PatientMenuActivity;
import com.mydoctor.customer.activities.patient.RegisterPatientActivity;
import com.mydoctor.customer.delegates.CommunicationInterface;
import com.mydoctor.customer.fragments.CommonMessageBottomSheet;
import com.mydoctor.customer.models.CommonSuccessMessageModel;
import com.mydoctor.customer.models.DoctorModel;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.LoginRequestModel;
import com.mydoctor.customer.models.RegisterDoctorResponseModel;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.models.UserModel;
import com.mydoctor.customer.networking.ApiProxy;
import com.mydoctor.customer.networking.ApiProxyImpl;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Config;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.UiUtils;
import com.mydoctor.customer.utils.database.DbPreference;
import com.mydoctor.customer.utils.networkingutils.DataCallback;
import com.mydoctor.customer.utils.ui.AbstractActivity;
//import com.deliverypulse.utils.ui.DarkStatusBar;
//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.GraphRequest;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.mydoctor.customer.utils.ui.DarkStatusBar;
//import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.mydoctor.customer.utils.AppConstants.DOCTOR;
//import static com.deliverypulse.utils.Config.TENANT_ID;
//import static com.deliverypulse.utils.Config.VERSION_NAME;

public class LoginActivity extends AbstractActivity {
    private static final String EMAIL = "email";
    private static final String PUBLIC_PROFILE = "public_profile";

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.btn_register)
    TextView btnRegister;

    private Unbinder unbinder;
    private CommonMessageBottomSheet commonMessageBottomSheet;
    private boolean isBackPressedTwiceToExit;
    private SharedPreferences mPrefs;
    private int rcSignIn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        View view = getWindow().getDecorView();
        DarkStatusBar.setLightStatusBar(view, this);

        activity = this;
        unbinder = ButterKnife.bind(this);
        mPrefs = getPreferences(MODE_PRIVATE);
        initUI();
        getIntentData();

    }

    private void getIntentData() {

        if (getIntent() != null && getIntent().getBooleanExtra(AppConstants.IS_SESSION_EXPIRED, false)) {
            showCommonMessageBottomSheet(activity.getString(R.string.msg_session_expired), AppConstants.IS_SESSION_EXPIRED, new DialogDelegate());
        }
    }

    @Override
    public void initUI() {
        super.initUI();
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (!isValidFields()) {
                    return;
                }
                callLoginApi();
                break;

            case R.id.btn_register:
                if(Config.USER_ROLE.equals(DOCTOR))
                    goToActivity(this, RegisterDoctorActivity.class, null);
                else
                    goToActivity(this, RegisterPatientActivity.class, null);
                break;

            default:
                break;
        }
    }

    private void manageDoctorScreenRedirection(RegisterDoctorResponseModel registerDoctorResponseModel) {
        hideKeyboard(activity);
        addDoctorDataToDb(registerDoctorResponseModel);
        clearActivityStack();
        switchToActivity(activity, DoctorMenuActivity.class, null);
    }

    private void managePatientScreenRedirection(RegisterPatientResponseModel registerPatientResponseModel) {
        hideKeyboard(activity);
        addUserDataToDb(registerPatientResponseModel);
        clearActivityStack();
        switchToActivity(activity, PatientMenuActivity.class, null);
    }

    private void callLoginApi() {
        LoginRequestModel loginRequestModel = getLoginRequestModel();
        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        if (Config.USER_ROLE.equals(DOCTOR)) {
            apiProxy.loginDoctor(new DataCallback<RegisterDoctorResponseModel>() {

                @Override
                public void onSuccess(RegisterDoctorResponseModel response) {
                    if (response != null) {
                        Logger.debug("" + response);
                        manageDoctorScreenRedirection(response);
                    }
                }

                @Override
                public void onError(ErrorResponseModel errorResponse) {
                    UiUtils.displaySnackbar(activity, errorResponse.getMessage(), AppConstants.MessageType.ERROR);
                }

            }, loginRequestModel);
        } else {
            apiProxy.loginPatient(new DataCallback<RegisterPatientResponseModel>() {

                @Override
                public void onSuccess(RegisterPatientResponseModel response) {
                    if (response != null) {
                        managePatientScreenRedirection(response);
                    }
                }

                @Override
                public void onError(ErrorResponseModel errorResponse) {
                    UiUtils.displaySnackbar(activity, errorResponse.getMessage(), AppConstants.MessageType.ERROR);
                }

            }, loginRequestModel);
        }


    }

    private LoginRequestModel getLoginRequestModel() {
        LoginRequestModel loginPatientRequestModel = new LoginRequestModel();
        loginPatientRequestModel.setEmail(getEmail());
        loginPatientRequestModel.setPassword(getPassword());
        return loginPatientRequestModel;
    }

    private void addDoctorDataToDb(RegisterDoctorResponseModel response) {
        DbPreference.putString(activity, AppConstants.SESSION_KEY, response.getToken());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("doctor", new Gson().toJson(response));
        editor.putString(AppConstants.SESSION_KEY, response.getToken());
        editor.commit();
    }

    private void addUserDataToDb(RegisterPatientResponseModel response) {
        DbPreference.putString(activity, AppConstants.SESSION_KEY, response.getToken());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", new Gson().toJson(response));
        editor.putString(AppConstants.SESSION_KEY, response.getToken());
        editor.commit();
    }

    private String getPassword() {
        return etPassword.getText().toString().trim();
    }

    private String getEmail() {
        return etEmail.getText().toString();
    }

    private boolean isValidFields() {

        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            UiUtils.displaySnackbar(activity, getString(R.string.msg_empty_password), AppConstants.MessageType.ERROR);
            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * Call logout api if customer login credentials are used in driver app and vice-versa.
     */
    private void callLogoutApi() {

        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.logout(new DataCallback<CommonSuccessMessageModel>() {

            @Override
            public void onSuccess(CommonSuccessMessageModel response) {
                if (response != null) {
                    dismissProgressDialog();
                    DbPreference.remove(activity, AppConstants.SESSION_KEY);
                    UiUtils.displaySnackbar(activity, activity.getString(R.string.warn_invalid_login), AppConstants.MessageType.ERROR);
                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
                dismissProgressDialog();
                UiUtils.displaySnackbar(activity, errorResponse.getMessage(), AppConstants.MessageType.ERROR);
            }
        });
    }

    @Override
    public void onBackPressed() {
        handleBackPressed();
    }

    private void handleBackPressed() {

        if (isBackPressedTwiceToExit) {
            finishAffinity();
            return;
        }

        this.isBackPressedTwiceToExit = true;

        if (activity != null) {
            UiUtils.displaySnackbar(activity, getString(R.string.click_back_again_exit), AppConstants.MessageType.GENERAL);
        }

        new Handler().postDelayed(() -> isBackPressedTwiceToExit = false, 2000);

    }

    private class DialogDelegate implements CommunicationInterface {

        @Override
        public void setCommunication(String message, String actionType) {
            if (commonMessageBottomSheet != null) {
                commonMessageBottomSheet.dismiss();
            }
        }
    }
}