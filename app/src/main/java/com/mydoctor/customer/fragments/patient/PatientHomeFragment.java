package com.mydoctor.customer.fragments.patient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.activities.doctor.RegisterDoctorActivity;
import com.mydoctor.customer.activities.patient.RegisterPatientActivity;
import com.mydoctor.customer.models.RegisterPatientResponseModel;
import com.mydoctor.customer.models.UserModel;
import com.mydoctor.customer.utils.Config;
import com.mydoctor.customer.utils.ui.AbstractFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mydoctor.customer.utils.AppConstants.DOCTOR;

public class PatientHomeFragment extends AbstractFragment {

    private UserModel userModel;

    @BindView(R.id.txt_user_name)
    TextView txtUserName;

    @OnClick({R.id.clickable_add_appointment, R.id.clickable_add_medical})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clickable_add_appointment:
                moveToMyAppointmentFragment();
                break;

            case R.id.clickable_add_medical:
                moveToNewMedicalFragment();
                break;

            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_home, container, false);

        ButterKnife.bind(this, view);

        initUI(view);

        setUserData();

        return view;
    }

    private void setUserData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String data = sharedPreferences.getString("user", "");
        RegisterPatientResponseModel obj = new Gson().fromJson(data, RegisterPatientResponseModel.class);
        String fullName = obj.getUser().getName();
        txtUserName.setText(fullName.substring(0, fullName.indexOf(" ")));
    }

    public void moveToMyAppointmentFragment() {
        MyAppointmentsFragment myAppointmentsFragment = new MyAppointmentsFragment();
        getActivity().findViewById(R.id.img_app_logo).setVisibility(View.GONE);
        TextView txtHeader = getActivity().findViewById(R.id.txt_header);
        txtHeader.setText(getString(R.string.menu_my_appointments));
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, myAppointmentsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void moveToNewMedicalFragment() {
        NewMedicalFragment newMedicalFragment = new NewMedicalFragment();
        getActivity().findViewById(R.id.img_app_logo).setVisibility(View.GONE);
        TextView txtHeader = getActivity().findViewById(R.id.txt_header);
        txtHeader.setText(getString(R.string.add_medical));
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, newMedicalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
