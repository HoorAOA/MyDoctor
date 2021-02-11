package com.mydoctor.customer.activities.doctor;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.models.AddSpecializationResponseModel;
import com.mydoctor.customer.models.DoctorModel;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.LoginRequestModel;
import com.mydoctor.customer.models.RegisterDoctorRequestModel;
import com.mydoctor.customer.models.RegisterDoctorResponseModel;
import com.mydoctor.customer.models.SpecializationRequestModel;
import com.mydoctor.customer.networking.ApiProxy;
import com.mydoctor.customer.networking.ApiProxyImpl;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.NetworkUtils;
import com.mydoctor.customer.utils.UiUtils;
import com.mydoctor.customer.utils.database.DbPreference;
import com.mydoctor.customer.utils.networkingutils.DataCallback;
import com.mydoctor.customer.utils.ui.AbstractActivity;
import com.mydoctor.customer.utils.ui.DarkStatusBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterDoctorActivity extends AbstractActivity {

    @BindView(R.id.et_first_name)
    TextView etFirstName;

    @BindView(R.id.et_last_name)
    TextView etLastName;

    @BindView(R.id.et_email)
    TextView etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_doctor_specialization)
    EditText etSpecialization;

    @BindView(R.id.et_practice_from)
    EditText etPracticeFrom;

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;

    @BindView(R.id.constraint_main)
    ConstraintLayout constraintMain;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @BindView(R.id.scrollView_main)
    NestedScrollView scrollViewMain;

    private Unbinder unbinder;

    @OnClick(R.id.btn_register)
    void registerUser() {
        callRegisterUser();
    }

    private void callRegisterUser() {

        if (!NetworkUtils.isNetworkAvailable(activity)) {

            UiUtils.displaySnackbar(activity, getString(R.string.no_internet_connection), AppConstants.MessageType.ERROR);

            return;
        }
        callRegisterApi();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        View view = getWindow().getDecorView();
        DarkStatusBar.setLightStatusBar(view, this);

        activity = this;
        unbinder = ButterKnife.bind(this);
        initUI();
        setListeners();
    }

    @Override
    public void initUI() {
        super.initUI();
        tvHeader.setVisibility(View.GONE);
        ibnBack.setVisibility(View.VISIBLE);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

    }

    private void callRegisterApi() {

        RegisterDoctorRequestModel registerDoctorRequestModel = getRegisterRequestModel();

        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.registerDoctor(new DataCallback<RegisterDoctorResponseModel>() {

            @Override
            public void onSuccess(RegisterDoctorResponseModel response) {
                if (response != null) {
                    Logger.debug("" + response);
                    callAddSpecializationApi();
                    clearActivityStack();
                    addUserDataToDb(response);
                    goToActivity(activity, DoctorMenuActivity.class, null);
                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
                UiUtils.displaySnackbar(activity, errorResponse.getMessage(), AppConstants.MessageType.ERROR);
            }
        }, registerDoctorRequestModel);
    }

    private void callAddSpecializationApi() {

        SpecializationRequestModel specializationRequestModel = getSpecializationRequestModel();

        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.addSpecialization(new DataCallback<AddSpecializationResponseModel>() {

            @Override
            public void onSuccess(AddSpecializationResponseModel response) {
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
            }
        }, specializationRequestModel);
    }

    private RegisterDoctorRequestModel getRegisterRequestModel() {
        RegisterDoctorRequestModel registerDoctorRequestModel = new RegisterDoctorRequestModel();
        registerDoctorRequestModel.setDoctorFirstName(getFirstName());
        registerDoctorRequestModel.setDoctorLastName(getLastName());
        registerDoctorRequestModel.setPhoneNumber(getPhoneNumber());
        registerDoctorRequestModel.setEmail(getEmail());
        registerDoctorRequestModel.setPassword(getPassword());
        registerDoctorRequestModel.setPracticingFrom(getPracticeFrom());
        registerDoctorRequestModel.setDoctorSpecialization(getDoctorSpecialization());
        return registerDoctorRequestModel;
    }

    private SpecializationRequestModel getSpecializationRequestModel() {
        SpecializationRequestModel specializationRequestModel = new SpecializationRequestModel();
        specializationRequestModel.setSpecializationName(getDoctorSpecialization());
        return specializationRequestModel;
    }

    private void addUserDataToDb(RegisterDoctorResponseModel response) {
        DbPreference.putString(activity, AppConstants.SESSION_KEY, response.getToken());
        DoctorModel userModel = response.getDoctor();
        String userData = new Gson().toJson(userModel);
        DbPreference.putString(activity, AppConstants.USER_KEY, userData);
    }

    private String getFirstName() {
        return etFirstName.getText().toString().trim();
    }

    private String getLastName() {
        return etLastName.getText().toString().trim();
    }

    private String getEmail() {
        return etEmail.getText().toString().trim();
    }

    private String getPassword() {
        return etPassword.getText().toString().trim();
    }

    private String getPhoneNumber() {
        return etPhoneNumber.getText().toString().trim();
    }

    private String getPracticeFrom() {
        return etPracticeFrom.getText().toString().trim();
    }

    private String getDoctorSpecialization() {
        return etSpecialization.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
