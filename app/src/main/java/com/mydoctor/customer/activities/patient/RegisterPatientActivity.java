package com.mydoctor.customer.activities.patient;

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
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.RegisterPatientRequestModel;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.models.UserModel;
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

public class RegisterPatientActivity extends AbstractActivity {

    @BindView(R.id.et_full_name)
    TextView etFullName;

    @BindView(R.id.et_phone_number)
    TextView etPhoneNumber;

    @BindView(R.id.et_email)
    TextView etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

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
        setContentView(R.layout.activity_patient_sign_up);

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
        RegisterPatientRequestModel mRegisterRequestModel = getRegisterRequestModel();

        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.registerPatient(new DataCallback<RegisterPatientResponseModel>() {

            @Override
            public void onSuccess(RegisterPatientResponseModel response) {
                if (response != null) {
                    Logger.debug("" + response);
                    dismissProgressDialog();
                    addUserDataToDb(response);
                    clearActivityStack();
                    goToActivity(activity, PatientMenuActivity.class, null);
                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
                dismissProgressDialog();
                UiUtils.displaySnackbar(activity, errorResponse.getMessage(), AppConstants.MessageType.ERROR);
            }
        }, mRegisterRequestModel);
    }

    private void addUserDataToDb(RegisterPatientResponseModel response) {
        DbPreference.putString(activity, AppConstants.SESSION_KEY, response.getToken());
        UserModel userModel = response.getUser();
        String userData = new Gson().toJson(userModel);
        DbPreference.putString(activity, AppConstants.USER_KEY, userData);
    }

     private RegisterPatientRequestModel getRegisterRequestModel() {
        RegisterPatientRequestModel registerPatientRequestModel = new RegisterPatientRequestModel();
         registerPatientRequestModel.setPatientName(getName());
         registerPatientRequestModel.setEmail(getEmail());
         registerPatientRequestModel.setPatientPhoneNumber(getMobileNumber());
         registerPatientRequestModel.setPassword(getPassword());
        return registerPatientRequestModel;
    }

    private String getName() {
        return etFullName.getText().toString().trim();
    }

    private String getMobileNumber() {
        return etPhoneNumber.getText().toString().trim();
    }

    private String getEmail() {
        return etEmail.getText().toString().trim();
    }

    private String getPassword() {
        return etPassword.getText().toString().trim();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
