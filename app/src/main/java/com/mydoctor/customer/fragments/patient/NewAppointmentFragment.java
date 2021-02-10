package com.mydoctor.customer.fragments.patient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.fragments.CommonActionBottomSheet;
import com.mydoctor.customer.models.AddAppointmentRequestModel;
import com.mydoctor.customer.models.AddAppointmentResponseModel;
import com.mydoctor.customer.models.ClinicsModel;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.GetAllClinicsResponseModel;
import com.mydoctor.customer.models.NewMedicalRequestModel;
import com.mydoctor.customer.models.NewMedicalResponseModel;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.networking.ApiProxy;
import com.mydoctor.customer.networking.ApiProxyImpl;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.UiUtils;
import com.mydoctor.customer.utils.networkingutils.DataCallback;
import com.mydoctor.customer.utils.ui.AbstractFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewAppointmentFragment extends AbstractFragment {

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.menu_clinic_name)
    Spinner spinnerClinic;

    @BindView(R.id.et_appointment_kind)
    EditText etAppointmentKind;

    @BindView(R.id.et_appointment_date)
    EditText etAppointmentDate;

    private CommonActionBottomSheet dialogFragment;
    private String userId;
    private String selectedClinic;

    public NewAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_appointment, container, false);
        ButterKnife.bind(this, view);
        initUI(view);
        callGetAllClinicsApi();
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
                callNewAppointmentApi();
                break;

            default:
                break;
        }
    }

    private void callGetAllClinicsApi() {
        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.getAllClinics(new DataCallback<GetAllClinicsResponseModel>() {

            @Override
            public void onSuccess(GetAllClinicsResponseModel response) {
                ArrayList<String> items = new ArrayList<String>();
                if (response != null) {
                    items.add(getString(R.string.clinic_name));

                    for(ClinicsModel clinic : response.getClinics()){
                        items.add(clinic.getClinicName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            R.layout.spinner_item, items);

                    spinnerClinic.setAdapter(adapter);

                    spinnerClinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int position, long id) {
                            if(!parent.getItemAtPosition(position).equals(getString(R.string.clinic_name)))
                                selectedClinic = (String) parent.getItemAtPosition(position);

                            Log.d("item", (String) parent.getItemAtPosition(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // TODO Auto-generated method stub
                        }
                    });

                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
            }

        });
    }

    private void callNewAppointmentApi() {
        AddAppointmentRequestModel addAppointmentRequestModel = getNewAppointmentRequestModel();
        ApiProxy apiProxy = ApiProxyImpl.getInstance();
            apiProxy.addAppointmentUrl(new DataCallback<AddAppointmentResponseModel>() {

                @Override
                public void onSuccess(AddAppointmentResponseModel response) {
                    if (response != null) {
                        Logger.debug("" + response);
                        UiUtils.displaySnackbar(getActivity(), getString(R.string.request_sent_success), AppConstants.MessageType.SUCCESS);
                    }
                }

                @Override
                public void onError(ErrorResponseModel errorResponse) {
                    UiUtils.displaySnackbar(getActivity(), errorResponse.getMessage(), AppConstants.MessageType.ERROR);
                }

            }, addAppointmentRequestModel);
    }

    private AddAppointmentRequestModel getNewAppointmentRequestModel() {
        AddAppointmentRequestModel addAppointmentRequestModel = new AddAppointmentRequestModel();
        addAppointmentRequestModel.setClinicName(selectedClinic);
        addAppointmentRequestModel.setAppointmentDate(getAppointmentDate());
        addAppointmentRequestModel.setAppointmentKind(getAppointmentKind());
        return addAppointmentRequestModel;
    }

    private String getAppointmentDate() {
        return etAppointmentDate.getText().toString();
    }

    private String getAppointmentKind() {
        return etAppointmentKind.getText().toString();
    }
}