package com.mydoctor.customer.utils.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mydoctor.customer.R;
import com.mydoctor.customer.delegates.CommunicationInterface;
import com.mydoctor.customer.delegates.CommunicationInterfaceAll;
import com.mydoctor.customer.dialogs.CommonInfoDialog;
import com.mydoctor.customer.fragments.CommonActionBottomSheet;
import com.mydoctor.customer.fragments.CommonMessageBottomSheet;
import com.mydoctor.customer.models.DoctorModel;
import com.mydoctor.customer.models.UserModel;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.customviews.CustomProgressDialog;
import com.mydoctor.customer.utils.permissions.AlertDialogProperties;
import com.mydoctor.customer.utils.permissions.AppPermission;

import java.util.ArrayList;
import java.util.List;

public class AbstractFragment extends Fragment implements FragmentHelper {

    protected static final int MULTIPLE_PERMISSION_REQUEST = 130;
    protected static final int CALL_PERMISSION_REQUEST_CODE = 132;
    protected TextView tvHeader;
    protected AppPermission appPermission;
    private ActivityHelper ah;
    private Activity activity;
    private CommonInfoDialog commonInfoDialog;
    private AlertDialogProperties alertDialogProperties;
    private String[] permissionArray;
    private CustomProgressDialog customProgressDialog;
    private CommonActionBottomSheet commonActionBottomSheet;
    private CommonMessageBottomSheet commonMessageBottomSheet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();

        customProgressDialog = new CustomProgressDialog(activity);
        commonActionBottomSheet = new CommonActionBottomSheet();
        commonMessageBottomSheet = new CommonMessageBottomSheet();

        appPermission = AppPermission.getInstance();

        ah = new ActivityHelperImpl(getActivity());

        appPermission = AppPermission.getInstance();
    }

    @Override
    public void hideKeyboard(View view) {

        ah.hideKeyboard(view);
    }

    public UserModel getUserModel() {
        return UserModel.fetchUserModel(activity);
    }

    public DoctorModel getDoctorModel() {
        return DoctorModel.fetchUserModel(activity);
    }

    @Override
    public Drawable createRepeatableDrawable(int imageId) {

        return ah.createRepeatableDrawable(imageId);
    }


    /* Switch to some activity with bundle values and kill current activity */
    @Override
    public void switchToActivity(Activity current,
                                 Class<? extends Activity> otherActivityClass, Bundle extras) {

        ah.switchToActivity(current, otherActivityClass, extras);

    }

    /* VEHICLE_TYPE_ONE to some activity with bundle values */
    @Override
    public void goToActivity(Activity current,
                             Class<? extends Activity> otherActivityClass, Bundle extras) {

        ah.goToActivity(current, otherActivityClass, extras);
    }

    @Override
    public void initUI(View view) {
        Logger.debug("Initialisation of common elements if any");

    }

    @Override
    public void initUI() {
        Logger.debug("initUI");
    }

    @Override
    public void setListeners() {
        Logger.debug("Set common action listeners");

    }


    public void showCommonSuccessDialog(String message, String actionMessage, CommunicationInterface communicationInterface) {

        commonInfoDialog = new CommonInfoDialog(activity, activity.getResources().getString(R.string.ok),
                message, actionMessage, communicationInterface, true);
        commonInfoDialog.show();
        commonInfoDialog.setCancelable(false);
    }

    public void showCommonErrorDialog(String message) {

        commonInfoDialog = new CommonInfoDialog(activity, activity.getResources().getString(R.string.ok), message);
        commonInfoDialog.show();
        commonInfoDialog.setCancelable(false);
    }

    public void showAlertToGoSetting(boolean toCloseActivity, boolean toExitFromApp) {
        initAlertDialogFromFragment();
        alertDialogProperties.setOkayButtonText(getString(R.string.app_setting));
        alertDialogProperties.setOkayButtonListener(new GoToSettingsFromFragment(toCloseActivity, toExitFromApp));
        alertDialogProperties.setCancelButtonListener(new CancelButtonClickListener(toCloseActivity, toExitFromApp));
    }

    public void showPermissionAlertFromFragment(boolean toCloseActivity, boolean toExitFromApp) {
        initAlertDialogFromFragment();
        alertDialogProperties.setOkayButtonText(getString(R.string.ok));
        alertDialogProperties.setOkayButtonListener(new OkButtonListener());
        alertDialogProperties.setCancelButtonListener(new CancelButtonClickListener(toCloseActivity, toExitFromApp));
    }

    private void initAlertDialogFromFragment() {
        alertDialogProperties = new AlertDialogProperties();
        alertDialogProperties.setButtonTextColor(activity.getResources().getColor(R.color.white));
        alertDialogProperties.setCancelable(false);
        alertDialogProperties.setMessageTextColor(activity.getResources().getColor(R.color.black));
        alertDialogProperties.setCancelButtonText(getString(R.string.exit));
    }

    public void checkPermissionAndShowDialog(List<String> permissionList) {
        String[] tempArray = new String[permissionList.size()];
        permissionList.toArray(tempArray);
        permissionList.clear();
        permissionArray = tempArray;
        if (activity != null && !activity.isFinishing()) {
            appPermission.showPermissionDialog(activity, alertDialogProperties, permissionArray);
        }
    }

    protected void clearActivityStack() {
        activity.finishAffinity();
    }


    /**
     * Show common action bottom sheet
     */

    protected void showCommonActionBottomSheet(String noButtonText, String yesButtonText, String actionMessage, String message, String negativeAction, String positiveAction, CommunicationInterfaceAll communicationInterfaceAll) {
        dismissCommonActionBottomSheet();
        commonActionBottomSheet = new CommonActionBottomSheet(noButtonText, yesButtonText, actionMessage, message, negativeAction, positiveAction, communicationInterfaceAll);
        commonActionBottomSheet.setCancelable(false);
        commonActionBottomSheet.show(getChildFragmentManager(), commonActionBottomSheet.getTag());
    }

    private void dismissCommonActionBottomSheet() {
        if (commonActionBottomSheet != null && commonActionBottomSheet.isVisible()) {
            commonActionBottomSheet.dismiss();
        }
    }

    public void showCommonMessageBottomSheet(String message, String actionType, CommunicationInterface communicationInterface) {
        dismissCommonMessageBottomSheet();
        commonMessageBottomSheet = new CommonMessageBottomSheet(message, actionType, communicationInterface);
        commonMessageBottomSheet.show(getChildFragmentManager(), commonMessageBottomSheet.getTag());
    }

    private void dismissCommonMessageBottomSheet() {
        if (commonMessageBottomSheet != null && commonMessageBottomSheet.isVisible()) {
            commonMessageBottomSheet.dismiss();
        }
    }

    /**
     * Show progress dialog
     *
     * @param message
     */
    protected void showProgressDialog(String message) {

        if (customProgressDialog == null) {
            return;
        }

        if (customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }

        customProgressDialog.setDisplayMessage(message);

        customProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (activity != null && !activity.isFinishing() && customProgressDialog != null && customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }
    }

    private class GoToSettingsFromFragment implements View.OnClickListener {

        final boolean toCloseActivity;
        final boolean toExitFromApp;

        GoToSettingsFromFragment(boolean toCloseActivity, boolean toExitFromApp) {
            this.toCloseActivity = toCloseActivity;
            this.toExitFromApp = toExitFromApp;
        }

        @Override
        public void onClick(View v) {
            navigateToAppSettingFromFragment();
            appPermission.closeDialog();
            if (toCloseActivity) {
                activity.onBackPressed();
            }
            if (toExitFromApp) {
                clearActivityStack();
            }
        }

        private void navigateToAppSettingFromFragment() {
            Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + activity.getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(i);
        }
    }

    private class OkButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            appPermission.requestPermissionList(activity, permissionArray, MULTIPLE_PERMISSION_REQUEST);
            appPermission.closeDialog();
        }
    }

    private class CancelButtonClickListener implements View.OnClickListener {

        final boolean toCloseActivity;
        final boolean toExitFromApp;

        CancelButtonClickListener(boolean toCloseActivity, boolean toExitFromApp) {
            this.toCloseActivity = toCloseActivity;
            this.toExitFromApp = toExitFromApp;
        }

        @Override
        public void onClick(View v) {
            appPermission.closeDialog();
            if (toCloseActivity) {
                activity.onBackPressed();
            }
            if (toExitFromApp) {
                clearActivityStack();
            }
        }
    }
}
