package com.mydoctor.customer.fragments.patient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.activities.doctor.RegisterDoctorActivity;
import com.mydoctor.customer.activities.patient.RegisterPatientActivity;
import com.mydoctor.customer.fragments.CommonActionBottomSheet;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.LoginRequestModel;
import com.mydoctor.customer.models.NewMedicalRequestModel;
import com.mydoctor.customer.models.NewMedicalResponseModel;
import com.mydoctor.customer.models.RegisterDoctorResponseModel;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.networking.ApiProxy;
import com.mydoctor.customer.networking.ApiProxyImpl;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Config;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.UiUtils;
import com.mydoctor.customer.utils.networkingutils.DataCallback;
import com.mydoctor.customer.utils.ui.AbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mydoctor.customer.utils.AppConstants.DOCTOR;

public class NewMedicalFragment extends AbstractFragment {

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.et_exam_name)
    EditText etExamName;

    @BindView(R.id.et_exam_result)
    EditText etExamResult;

    private CommonActionBottomSheet dialogFragment;
    private String userId;

    public NewMedicalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_medical, container, false);
        ButterKnife.bind(this, view);
        initUI(view);
        return view;
    }

    @Override
    public void initUI(View view) {
        super.initUI(view);
    }

    @OnClick({R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                callNewMedicalApi();
                break;

            default:
                break;
        }
    }

    private void callNewMedicalApi() {
        NewMedicalRequestModel newMedicalRequestModel = getNewMedicalRequestModel();
        ApiProxy apiProxy = ApiProxyImpl.getInstance();
            apiProxy.addMedicalExaminationUrl(new DataCallback<NewMedicalResponseModel>() {

                @Override
                public void onSuccess(NewMedicalResponseModel response) {
                    if (response != null) {
                        Logger.debug("" + response);
                        UiUtils.displaySnackbar(getActivity(), getString(R.string.request_sent_success), AppConstants.MessageType.SUCCESS);
                    }
                }

                @Override
                public void onError(ErrorResponseModel errorResponse) {
                    UiUtils.displaySnackbar(getActivity(), errorResponse.getMessage(), AppConstants.MessageType.ERROR);
                }

            }, newMedicalRequestModel, userId);
    }

    private NewMedicalRequestModel getNewMedicalRequestModel() {
        // get the user id
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String data = sharedPreferences.getString("user", "");
        RegisterPatientResponseModel obj = new Gson().fromJson(data, RegisterPatientResponseModel.class);
        userId = obj.getUser().getUserId();

        // get medical info
        NewMedicalRequestModel newMedicalRequestModel = new NewMedicalRequestModel();
        newMedicalRequestModel.setExamName(getExamName());
        newMedicalRequestModel.setExamResult(getExamResult());
        return newMedicalRequestModel;
    }

    private String getExamName() {
        return etExamName.getText().toString();
    }

    private String getExamResult() {
        return etExamResult.getText().toString();
    }
}