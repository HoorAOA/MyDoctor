package com.mydoctor.customer.fragments.patient;

import android.content.Intent;
import android.os.Bundle;
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
import com.mydoctor.customer.models.AppointmentsModel;
import com.mydoctor.customer.models.ErrorResponseModel;
import com.mydoctor.customer.models.GetAllAppointmentsResponseModel;
import com.mydoctor.customer.models.UserModel;
import com.mydoctor.customer.models.DeleteAppointmentsResponseModel;
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

public class MyAppointmentsFragment extends AbstractFragment {

    private UserModel userModel;
    private MyAppointmentsAdapter myAppointmentsAdapter;

    @BindView(R.id.rv_appointments)
    RecyclerView rvAppointments;
    @BindView((R.id.tvNoData))
    TextView tvNoData;

    @OnClick(R.id.floating_action_button)
    void newAppointmentClicked() {
       NewAppointmentFragment newAppointmentFragment = new NewAppointmentFragment();
        getActivity().findViewById(R.id.img_app_logo).setVisibility(View.GONE);
        TextView txtHeader = getActivity().findViewById(R.id.txt_header);
        txtHeader.setText(getString(R.string.menu_new_appointment));
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, newAppointmentFragment);
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
        View view = inflater.inflate(R.layout.fragment_my_appointments, container, false);

        ButterKnife.bind(this, view);

        initUI(view);

        setAdapter();

        setListeners();

        return view;
    }

    @Override
    public void setListeners() {
        super.setListeners();

        if (myAppointmentsAdapter != null) {
            myAppointmentsAdapter.setAppointmentListener(new AppointmentItemListener());
        }

    }

    private class AppointmentItemListener implements MyAppointmentsAdapter.AppointmentDeleteListener {

        @Override
        public void onAppointmentDelete(String Id, int index) {
            callDeleteAppointmentApi(Id, index);
        }

        @Override
        public void isAppointmentAvailable(boolean isAvailable) {
            if (isAvailable) {
                tvNoData.setVisibility(View.GONE);
            } else {
                tvNoData.setVisibility(View.VISIBLE);
            }
        }
    }

    public void callDeleteAppointmentApi(String Id, int index) {
        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.deleteAppointment(new DataCallback<DeleteAppointmentsResponseModel>() {
            @Override
            public void onSuccess(DeleteAppointmentsResponseModel response) {
                if (response != null) {
                    myAppointmentsAdapter.removeItem(index);
                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
                UiUtils.displaySnackbar(getActivity(), errorResponse.getMessage(), AppConstants.MessageType.ERROR);
            }
        }, Id);

    }

    private void setAdapter() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvAppointments.setLayoutManager(linearLayoutManager);
        myAppointmentsAdapter = new MyAppointmentsAdapter(getActivity());
        rvAppointments.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAppointments.setAdapter(myAppointmentsAdapter);
        callGetMyAppointmentsList();
    }

    private void callGetMyAppointmentsList() {

        ApiProxy apiProxy = ApiProxyImpl.getInstance();
        apiProxy.getAllAppointmentsUrl(new DataCallback<GetAllAppointmentsResponseModel>() {
            @Override
            public void onSuccess(GetAllAppointmentsResponseModel response) {

                List<AppointmentsModel> appointmentsModel = new ArrayList<>();

                appointmentsModel = response.getAppointments();
                Log.d("Appointments:::", new Gson().toJson(appointmentsModel));

                if (appointmentsModel != null && !appointmentsModel.isEmpty()) {
                    rvAppointments.setAdapter(null);
                    rvAppointments.setAdapter(myAppointmentsAdapter);
                    myAppointmentsAdapter.setAppointmentsList(appointmentsModel);
                    myAppointmentsAdapter.notifyDataSetChanged();
                } else if(appointmentsModel.isEmpty()) {
                    if (tvNoData != null && rvAppointments != null) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvAppointments.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onError(ErrorResponseModel errorResponse) {
                dismissProgressDialog();
                UiUtils.displaySnackbar(getActivity(), errorResponse.getMessage(), AppConstants.MessageType.ERROR);
            }
        });
    }

}
