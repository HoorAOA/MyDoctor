package com.mydoctor.customer.fragments.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.adapters.MyAppointmentsAdapter;
import com.mydoctor.customer.adapters.MyMedicalAdapter;
import com.mydoctor.customer.models.AppointmentsModel;
import com.mydoctor.customer.models.DeleteAppointmentsResponseModel;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.GetAllAppointmentsResponseModel;
import com.mydoctor.customer.models.GetPatientMedicalsResponseModel;
import com.mydoctor.customer.models.MedicalModel;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.models.UserModel;
import com.mydoctor.customer.networking.ApiProxy;
import com.mydoctor.customer.networking.ApiProxyImpl;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.UiUtils;
import com.mydoctor.customer.utils.networkingutils.DataCallback;
import com.mydoctor.customer.utils.ui.AbstractFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyMedicalFragment extends AbstractFragment {

    private UserModel userModel;
    private MyMedicalAdapter myMedicalAdapter;
    private String userId;

    @BindView(R.id.rv_medicals)
    RecyclerView rvMedicals;
    @BindView((R.id.tvNoData))
    TextView tvNoData;

    @OnClick(R.id.floating_action_button)
    void newAppointmentClicked() {
        NewMedicalFragment newMedicalFragment = new NewMedicalFragment();
        getActivity().findViewById(R.id.img_app_logo).setVisibility(View.GONE);
        TextView txtHeader = getActivity().findViewById(R.id.txt_header);
        txtHeader.setText(getString(R.string.menu_new_medical_examination));
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, newMedicalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (data != null) {
//            switch (requestCode) {
//                case AppConstants.IMAGE_PICKER_CODE:
//                    profileImagePath = data.getStringExtra(IMAGE_FILE);
//                    extension = data.getStringExtra(AppConstants.FILE_EXTENSION);
//                    setProfileImage(profileImagePath);
//                    break;
//                default:
//                    break;
//            }
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_medicals, container, false);

        ButterKnife.bind(this, view);

        initUI(view);

        setCustomerProfileData();

        setAdapter();

        return view;
    }

    private void setCustomerProfileData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String data = sharedPreferences.getString("user", "");
        RegisterPatientResponseModel obj = new Gson().fromJson(data, RegisterPatientResponseModel.class);
        userId = obj.getUser().getUserId();
    }

    private void setAdapter() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvMedicals.setLayoutManager(linearLayoutManager);
        myMedicalAdapter = new MyMedicalAdapter(getActivity());
        rvMedicals.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMedicals.setAdapter(myMedicalAdapter);
        callGetMedicalList();
    }

    private void callGetMedicalList() {

        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.getAllPatientMedicals(new DataCallback<GetPatientMedicalsResponseModel>() {
            @Override
            public void onSuccess(GetPatientMedicalsResponseModel response) {

                List<MedicalModel> medicalModel = new ArrayList<>();

                medicalModel = response.getMedicalExaminations();
                Log.d("Appointments:::", new Gson().toJson(medicalModel));

                if (medicalModel != null && !medicalModel.isEmpty()) {
                    rvMedicals.setAdapter(null);
                    rvMedicals.setAdapter(myMedicalAdapter);
                    myMedicalAdapter.setMedicalList(medicalModel);
                    myMedicalAdapter.notifyDataSetChanged();
                } else if(medicalModel.isEmpty()) {
                    if (tvNoData != null && rvMedicals != null) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvMedicals.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
                dismissProgressDialog();
                UiUtils.displaySnackbar(getActivity(), errorResponse.getMessage(), AppConstants.MessageType.ERROR);
            }
        }, userId);
    }

}
