package com.mydoctor.customer.activities.doctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.mydoctor.customer.R;
import com.mydoctor.customer.fragments.patient.MyAppointmentsFragment;
import com.mydoctor.customer.models.DoctorModel;
import com.mydoctor.customer.models.RegisterDoctorResponseModel;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.UiUtils;
import com.mydoctor.customer.utils.database.DbPreference;
import com.mydoctor.customer.utils.ui.AbstractActivity;
import com.mydoctor.customer.utils.ui.DarkStatusBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DoctorMenuActivity extends AbstractActivity {

    private final ArrayList<String> permissionList = new ArrayList<>();
    private final ArrayList<String> listRationals = new ArrayList<>();
    @BindView(R.id.txt_header)
    TextView txtHeader;
    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Unbinder unbinder;
    private boolean isBackPressedTwiceToExit;
    private TextView txtName;
    private TextView txtMobileNumber;
    private ImageView imgProfilePic;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    private boolean isFromEditAddress;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor_menu);

        unbinder = ButterKnife.bind(this);

        activity = this;
        mPrefs = getPreferences(MODE_PRIVATE);
        initUI();

        View view = getWindow().getDecorView();
        DarkStatusBar.setLightStatusBar(view, this);

        moveToHomeFragment();

        setCustomerProfileData();

        setDrawerLayout();

        setListener();

        toolbar.setNavigationIcon(R.drawable.menu);

    }

    private void setCustomerProfileData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String data = sharedPreferences.getString("doctor", "");
        RegisterDoctorResponseModel obj = new Gson().fromJson(data, RegisterDoctorResponseModel.class);
        String fullName = obj.getDoctor().getUsername();
        String email = obj.getDoctor().getEmail();
        txtName.setText(fullName);
        txtMobileNumber.setText(email);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void initUI() {
        super.initUI();

        setSupportActionBar(toolbar);

        View headerLayout = navigationView.getHeaderView(0);

        txtName = headerLayout.findViewById(R.id.txt_name);

        txtMobileNumber = headerLayout.findViewById(R.id.txt_mobile_number);

        imgProfilePic = headerLayout.findViewById(R.id.img_profile_pic);


    }

    private void setListener() {

        actionBarDrawerToggle.setToolbarNavigationClickListener(new ToolbarNavigationClickListener());

        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void setDrawerLayout() {

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0 /*R.string.openDrawer, R.string.closeDrawer*/) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Logger.debug("customer_drawer_menu is closed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                setCustomerProfileData();
                hideKeyboard(activity);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.syncState();
        enableViews(false);
    }

    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     * <p>
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    public void enableViews(boolean enable) {
        if (enable) {
            isFromEditAddress = true;
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);
            }
            if (!mToolBarNavigationListenerIsRegistered) {
                actionBarDrawerToggle.setToolbarNavigationClickListener(v -> moveToMyBookingsFragment());

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
            isFromEditAddress = false;
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
            toolbar.setNavigationIcon(R.drawable.menu);
        }

    }


    private void loadDefaultFragment() {
        moveToHomeFragment();
    }


    public void setHeaderFragment(String title, boolean showAppIcon, boolean isHome) {

        if (isHome) {

            txtHeader.setVisibility(View.GONE);

            if (toolbar != null) {
                toolbar.setBackgroundResource(R.color.transparent_color);
            }

        } else if (showAppIcon) {

            txtHeader.setVisibility(View.INVISIBLE);

        } else {

            txtHeader.setVisibility(View.VISIBLE);

            txtHeader.setText(title);

        }
    }

    private void goToFragment(Fragment fragment, String header, boolean showAppIcon, boolean isHome) {
        replace(fragment.getClass(), null);
        setHeaderFragment(header, showAppIcon, isHome);
    }

    private void moveToHomeFragment() {
        MyAppointmentsFragment customerHomeFragment = new MyAppointmentsFragment();
        goToFragment(customerHomeFragment, activity.getResources().getString(R.string.menu_my_patients), false, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        handleBackPressed();
    }

    private void handleBackPressed() {
        Fragment fragment = peek();
        if ((fragment instanceof MyAppointmentsFragment) && isFromEditAddress) {
            moveToMyBookingsFragment();
        } else if (!(fragment instanceof MyAppointmentsFragment)) {
            navigationView.getMenu().getItem(0).setChecked(true);
            loadDefaultFragment();
        } else if (fragment instanceof MyAppointmentsFragment) {
            if (isBackPressedTwiceToExit) {
                finishAffinity();
                return;
            }
            if (activity != null) {
                UiUtils.displaySnackbar(activity, getString(R.string.click_back_again_exit), AppConstants.MessageType.GENERAL);
            }
            this.isBackPressedTwiceToExit = true;
            new Handler().postDelayed(() -> isBackPressedTwiceToExit = false, 2000);
        } else {
            back();
        }
    }

    private DoctorModel getDoctorData() {
        return DoctorModel.fetchUser(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isFromAppSettings = DbPreference.getBoolean(activity, AppConstants.IS_NAVIGATED_TO_SETTINGS, false);
        if (isFromAppSettings) {
            DbPreference.putBoolean(activity, AppConstants.IS_NAVIGATED_TO_SETTINGS, false);
            //checkLocPermission();
        }
    }

    public void moveToMyBookingsFragment() {
//        enableViews(false);
//        CustomerOrdersFragment customerOrdersFragment = new CustomerOrdersFragment();
//        goToFragment(customerOrdersFragment, activity.getResources().getString(R.string.menu_my_shipments), false, false);
    }

    public void moveToMyPatientsFragment() {
//        enableViews(false);
//        CustomerOrdersFragment customerOrdersFragment = new CustomerOrdersFragment();
//        goToFragment(customerOrdersFragment, activity.getResources().getString(R.string.menu_my_shipments), false, false);
    }

    private class ToolbarNavigationClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            drawerLayout.openDrawer(GravityCompat.START);

        }
    }

    private class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            item.setChecked(true);
            drawerLayout.closeDrawers();


            switch (item.getItemId()) {

                case R.id.menu_my_patients:
                    moveToMyPatientsFragment();
                    break;

                default:
                    break;
            }


            return true;
        }
    }
}