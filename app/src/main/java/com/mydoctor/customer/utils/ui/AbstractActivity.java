package com.mydoctor.customer.utils.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mydoctor.customer.R;
import com.mydoctor.customer.delegates.CommunicationInterface;
import com.mydoctor.customer.delegates.CommunicationInterfaceAll;
import com.mydoctor.customer.dialogs.CommonInfoDialog;
import com.mydoctor.customer.fragments.CommonActionBottomSheet;
import com.mydoctor.customer.fragments.CommonActionMessageBottomSheet;
import com.mydoctor.customer.fragments.CommonMessageBottomSheet;
import com.mydoctor.customer.utils.AppConstants;
import com.mydoctor.customer.utils.Logger;
import com.mydoctor.customer.utils.customviews.CustomProgressDialog;
import com.mydoctor.customer.utils.database.DbPreference;
import com.mydoctor.customer.utils.permissions.AlertDialogProperties;
import com.mydoctor.customer.utils.permissions.AppPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mydoctor.customer.utils.AppConstants.DOCTOR;


public class AbstractActivity extends AppCompatActivity implements ActivityHelper {
//
    public static final int MULTIPLE_PERMISSION_REQUEST = 130;
//    public static final int LOCATION_PERMISSION_REQUEST_CODE = 131;
//    public static final int PERMISSION_REQUEST_RECORD_AUDIO = 132;
//    private static final int REQUEST_LOCATION = 199;
//    private final BroadcastReceiver chargerReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
//                //Add code here to dismiss battery dialog
//            } else {
//                intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
//            }
//        }
//    };
//    private final BroadcastReceiver localBroadCastReceiverForBattery = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            //Add code here to show battery dialog
//        }
//    };
    protected AbstractActivity activity;
    protected ImageButton ibnBack;
    protected TextView tvHeader;
    protected TextView tvSkip;
    protected Toolbar toolbar;
    protected AppPermission appPermission;
    private AlertDialogProperties alertDialogProperties;
    private String[] permissionArray;
    private ActivityHelper ah;
    private CommonInfoDialog commonInfoDialog;
    private CustomProgressDialog customProgressDialog;
    private CommonActionBottomSheet commonActionBottomSheet;
    private CommonMessageBottomSheet commonMessageBottomSheet;
    private CommonActionMessageBottomSheet commonActionMessageBottomSheet;
    private String bookingId;
//    private String notificationCode;
//    private MediaPlayer mediaPlayer;
    private FragmentManager manager;
    private int containerId;

//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            // checking for type intent filter
//            if (intent == null || intent.getStringExtra(AppConstants.MESSAGE) == null) {
//                return;
//            }
//            FcmNotificationModel notificationModel = new Gson().fromJson(intent.getStringExtra("message"), FcmNotificationModel.class);
//            String message = notificationModel.getMessage();
//            Logger.debug("message: " + notificationModel.getMessage());
//            Log.d("Message::::",notificationModel.getMessage() + " :: " + notificationModel.getExtraParams());
//            String extraParams = notificationModel.getExtraParams();
//            Logger.debug("extraparam: " + extraParams);
//            if (!TextUtils.isEmpty(extraParams)) {
//                String[] param = extraParams.split("~");
//                // Booking related notification, contains booking id
//                if (param.length > 1) {
//                    notificationCode = param[0];
//                    bookingId = param[1];
//
//                    if (CommonUtils.isChatScreenActive(activity) && isChatRelatedNotification(notificationCode)
//                            && TextUtils.equals(bookingId, CommonUtils.getCurrentChatBookingId(activity))) {
//                        return;
//                    }
//
//                    if (checkNewRequestAssignByAdmin()) {
//                        playAlert();
//                    } else {
//                        playNotificationSound();
//                    }
//                    if(TextUtils.equals(notificationCode, AppConstants.ADDITIONAL_CHARGES_ADDED)
//                            && activity instanceof ViewShipmentDetailsActivity){
//                        ((ViewShipmentDetailsActivity) activity).callGetPaymentStatus(bookingId);
//                        return;
//                    } else if (TextUtils.equals(notificationCode, AppConstants.MESSAGE_TYPE_BUY_ME_SURCHARGE_ADDED)
//                            && activity instanceof BuyMeDetailsActivity){
//                        ((BuyMeDetailsActivity) activity).callGetPaymentStatus(bookingId);
//                        return;
//                    }
//                    showCommonSuccessDialog(message, extraParams, new DialogCommunicationInterFace());
//
//                } else {
//                    showCommonSuccessDialog(message, "", null);
//                    playNotificationSound();
//                }
//            }
//
//        }
//        private boolean isChatRelatedNotification(String pushType) {
//            return (TextUtils.equals(pushType, AppConstants.CHAT_MESSAGE_CUSTOMER)
//                    || TextUtils.equals(pushType, AppConstants.CHAT_MESSAGE_DELIVERY_AGENT)
//                    || TextUtils.equals(pushType, AppConstants.CHAT_MESSAGE_FROM_BUSINESS_CUSTOMER)
//                    || TextUtils.equals(pushType, AppConstants.CHAT_MESSAGE_FROM_ADMIN));
//        }
//
//        private boolean checkNewRequestAssignByAdmin() {
//            return isDriverMenuActivityOpen() &&
//                    !TextUtils.isEmpty(notificationCode) &&
//                    (notificationCode.equals(AppConstants.NEW_ORDER_ASSIGNED_BY_ADMIN)
//                            || notificationCode.equals(AppConstants.NEW_BOOKING_AVAILABLE))
//                    && !TextUtils.isEmpty(bookingId);
//        }
//    };
//


//    private boolean isDriverMenuActivityOpen() {
//        return Config.USER_ROLE.equalsIgnoreCase(AppConstants.DELIVERY_AGENT)
//                && activity instanceof DeliveryAgentMenuActivity;
//    }
//
//    public void playAlert() {
//        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        assert audio != null;
//        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
//
//
//        try {
//
//            if (mediaPlayer == null) {
//                String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +getPackageName() + "/" + R.raw.notification_tone; //NOSONAR
//                Uri soundUri = Uri.parse(path);
//
//                mediaPlayer = new MediaPlayer();
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//                mediaPlayer = MediaPlayer.create(activity,soundUri);
//                mediaPlayer.setVolume(1f, 1f);
//                mediaPlayer.setLooping(true);
//                mediaPlayer.start();
//
//            }
//
//        } catch (Exception e) {
//            Logger.debug(e.getMessage());
//        }
//
//    }
//
//    protected void showHideButtons(ArrayList<Integer> allButtonsIds, ArrayList<Integer> buttonIdsToShow) {
//
//        if (allButtonsIds == null || allButtonsIds.isEmpty()) {
//            return;
//        }
//
//        for (Integer buttonId : allButtonsIds
//        ) {
//            Button btnToHide = findViewById(buttonId);
//            btnToHide.setVisibility(View.GONE);
//        }
//
//        if (buttonIdsToShow == null || buttonIdsToShow.isEmpty()) {
//            return;
//        }
//
//        for (Integer buttonId : buttonIdsToShow) {
//            Button btnToShow = findViewById(buttonId);
//            btnToShow.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public void playNotificationSound() {
//
//        if (activity != null) {
//            AudioManager audio = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
//            assert audio != null;
//            int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
//            audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
//
//            if (audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL || audio.getRingerMode() == AudioManager.RINGER_MODE_SILENT || audio.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
//                try {
//
//                    String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/" + R.raw.notification_tone; //NOSONAR
//                    Uri uri = Uri.parse(path);
//                    MediaPlayer playNotification = MediaPlayer.create(activity, uri);
//                    playNotification.start();
//
//                } catch (Exception e) {
//                    Logger.error(e.toString());
//                }
//            }
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        registerReceiver(broadcastReceiver, new IntentFilter(AppConstants.PUSH_NOTIFICATION));

        activity = this;

        ah = new ActivityHelperImpl(activity);

        customProgressDialog = new CustomProgressDialog(activity);
        commonActionBottomSheet = new CommonActionBottomSheet();
        commonMessageBottomSheet = new CommonMessageBottomSheet();
        commonActionMessageBottomSheet = new CommonActionMessageBottomSheet();
        manager = getSupportFragmentManager();
        containerId = R.id.frame;
        appPermission = AppPermission.getInstance();
        // clear the notification area when the app is opened
//        NotificationUtils.clearNotifications(activity);
//
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//            getWindow().setStatusBarColor(Color.BLACK);
//        }

//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(activity)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @Override
//                    public void onConnected(@Nullable Bundle bundle) {
//
//                        Logger.debug("google api connected");
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//
//                        Logger.debug("google api connection suspended");
//                    }
//                })
//                .addOnConnectionFailedListener(connectionResult -> Logger.debug("google api connection failed")).build();
//        googleApiClient.connect();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_CANCELED) {
//            if (requestCode == REQUEST_LOCATION) {
//                Logger.debug("received location code");
//            }
//        } else {
//            Logger.debug("location is on");
//        }
//    }

//    public TenantConfigModel getTenantConfigModel() {
//        return MetaDataModel.fetchTenantConfig(this);
//    }
//
//    protected TimeSettingsModel getTimeSettingsModel() {
//        return MetaDataModel.fetchTimeSetting(this);
//    }
//
//    protected List<CancellationReasonModel> getCancellationReasonModels() {
//        return MetaDataModel.fetchCancellationReason(this);
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Logger.debug("in onStart() of activity" + this.getLocalClassName());
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Logger.debug("in onNewIntent() of activity" + this.getLocalClassName());
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Logger.debug("in onResume() of activity" + this.getLocalClassName());
//
//        if (Config.USER_ROLE.trim().equalsIgnoreCase(DELIVERY_AGENT)) {
//
//            checkDeliveryAgentDeviceBattery();
//
//            LocalBroadcastManager.getInstance(this).registerReceiver(localBroadCastReceiverForBattery, new IntentFilter(AppConstants.LOCAL_BROADCAST_MANAGER_FOR_CRITICAL_BATTERY));
//
//            registerReceiver(
//                    chargerReceiver,
//                    new IntentFilter(Intent.ACTION_POWER_CONNECTED)
//            );
//        }
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Logger.debug("in onStop() of activity" + this.getLocalClassName());
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Logger.debug("in onPause() of activity" + this.getLocalClassName());
//
//        if (Config.USER_ROLE.trim().equalsIgnoreCase(DELIVERY_AGENT)) {
//            LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadCastReceiverForBattery);
//            unregisterReceiver(chargerReceiver);
//        }
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Logger.debug("in onRestart() of activity" + this.getLocalClassName());
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        DbPreference.putBoolean(activity, AppConstants.IS_TIMER_ON, false);
//        DbPreference.putLong(activity, AppConstants.BROADCAST_BOOKING_TIME, 0);
//        DbPreference.putString(activity, AppConstants.BROADCAST_BOOKING_ID, "");
//        NotificationUtils.stopAlert();
//        dismissProgressDialog();
//        Logger.debug("in onDestroy() of activity" + this.getLocalClassName());
//        if (broadcastReceiver != null) {
//            unregisterReceiver(broadcastReceiver);
//        }
//    }

    @Override
    public void initUI() {

        toolbar = findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        ibnBack = findViewById(R.id.ibtn_left);

        tvHeader = findViewById(R.id.txt_header);

        tvSkip = findViewById(R.id.txt_skip);


    }

    @Override
    public void setListeners() {

        ibnBack.setOnClickListener(new BackButtonOnClickListener());
    }

    /**
     * Hide Key Board
     */
    protected void hideKeyboard(AbstractActivity activity) {
        if (getWindow() != null) {
            try {
                InputMethodManager imm = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView()
                            .getWindowToken(), 0);
                }
            } catch (Exception e) {
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }

    @Override
    public void hideKeyboard(View view) {
        ah.hideKeyboard(view);

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

    /* Go to some activity with bundle values */
    @Override
    public void goToActivity(Activity current,
                             Class<? extends Activity> otherActivityClass, Bundle extras) {

        ah.goToActivity(current, otherActivityClass, extras);
    }


    protected void showPermissionAlert(boolean toCloseActivity, boolean toExitFromApp) {
        initAlertDialog();
        alertDialogProperties.setOkayButtonText(getString(R.string.ok));
        alertDialogProperties.setOkayButtonListener(new OkButtonListener());
        alertDialogProperties.setCancelButtonListener(new CancelButtonListener(toCloseActivity, toExitFromApp));
    }

    private void initAlertDialog() {
        alertDialogProperties = new AlertDialogProperties();
        alertDialogProperties.setButtonTextColor(activity.getResources().getColor(R.color.white));
        alertDialogProperties.setCancelable(false);
        alertDialogProperties.setMessageTextColor(activity.getResources().getColor(R.color.black));
        alertDialogProperties.setCancelButtonText(getString(R.string.exit));
    }

    protected void checkPermissionAndShowPopup(ArrayList<String> permissionList) {
        String[] tempArray = new String[permissionList.size()];
        permissionList.toArray(tempArray);
        permissionList.clear();
        permissionArray = tempArray;
        if (activity != null && !activity.isFinishing()) {
            appPermission.showPermissionDialog(activity, alertDialogProperties, permissionArray);
        }
    }

    protected void showAlertToGoSetting(boolean toCloseActivity, boolean toExitFromApp) {
        initAlertDialog();
        alertDialogProperties.setOkayButtonText(getString(R.string.app_setting));
        alertDialogProperties.setOkayButtonListener(new GoToSettings(toCloseActivity, toExitFromApp));
        alertDialogProperties.setCancelButtonListener(new CancelButtonListener(toCloseActivity, toExitFromApp));
    }

    protected void clearActivityStack() {
        finishAffinity();

    }

    protected boolean isSearchKeyValid(int length) {
        return length == 3 || length == 5 || length == 7 || length >= 9;
    }

    /**
     * Show common action bottom sheet
     */

    protected void showCommonActionBottomSheet(String noButtonText, String yesButtonText, String actionMessage, String message, String negativeAction, String positiveAction, CommunicationInterfaceAll communicationInterfaceAll) {
        dismissCommonActionBottomSheet();
        commonActionBottomSheet = new CommonActionBottomSheet(noButtonText, yesButtonText, actionMessage, message, negativeAction, positiveAction, communicationInterfaceAll);
        commonActionBottomSheet.setCancelable(false);
        commonActionBottomSheet.show(getSupportFragmentManager(), commonActionBottomSheet.getTag());
    }

    protected void dismissCommonActionBottomSheet() {
        if (commonActionBottomSheet != null && commonActionBottomSheet.isVisible()) {
            commonActionBottomSheet.dismiss();
        }
    }

    protected void showCommonMessageBottomSheet(String message, String actionType, CommunicationInterface communicationInterface) {
        dismissCommonMessageBottomSheet();
        commonMessageBottomSheet = new CommonMessageBottomSheet(message, actionType, communicationInterface);
        commonMessageBottomSheet.show(getSupportFragmentManager(), commonMessageBottomSheet.getTag());
    }

    private void dismissCommonMessageBottomSheet() {
        if (commonMessageBottomSheet != null && commonMessageBottomSheet.isVisible()) {
            commonMessageBottomSheet.dismiss();
        }
    }

    protected void showCommonActionMessageBottomSheet(String actionMsg, String message, String positiveAct, CommunicationInterfaceAll communicationInterface) {
        dismissCommonActionMessageBottomSheet();
        commonActionMessageBottomSheet = new CommonActionMessageBottomSheet(actionMsg, message, positiveAct, communicationInterface);
        commonActionMessageBottomSheet.setCancelable(false);
        commonActionMessageBottomSheet.show(getSupportFragmentManager(), commonActionMessageBottomSheet.getTag());
    }

    private void dismissCommonActionMessageBottomSheet() {
        if (commonActionMessageBottomSheet != null && commonActionMessageBottomSheet.isVisible()) {
            commonActionMessageBottomSheet.dismiss();
        }
    }

    public void showCommonSuccessDialog(final String message, final String actionMessage, final CommunicationInterface communicationInterface) {
        runOnUiThread(() -> {
            if (!activity.isFinishing()) {
                if (commonInfoDialog != null && commonInfoDialog.isShowing()) {
                    commonInfoDialog.dismiss();
                }
                commonInfoDialog = new CommonInfoDialog(activity, activity.getResources().getString(R.string.ok),
                        message, actionMessage, communicationInterface, true);
                commonInfoDialog.show();
                commonInfoDialog.setCancelable(false);
            }
        });

    }

    public void showCommonNotificationPermissionDialog(final String message, final String actionMessage, final CommunicationInterface communicationInterface) {
        runOnUiThread(() -> {
            if (!activity.isFinishing()) {
                commonInfoDialog = new CommonInfoDialog(activity, activity.getResources().getString(R.string.ok),
                        message, actionMessage, communicationInterface, true);
                commonInfoDialog.show();
                commonInfoDialog.setCancelable(false);
            }
        });

    }

    protected void showCommonErrorDialog(String message) {
        if (!activity.isFinishing()) {
            commonInfoDialog = new CommonInfoDialog(activity, activity.getResources().getString(R.string.ok), message);
            commonInfoDialog.show();
            commonInfoDialog.setCancelable(false);
        }
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        if (BuildUtils.isAtLeast24Api()) {
//            super.attachBaseContext(ContextWrapper.wrap(newBase, LocaleUtils.getsLocale()));
//        } else {
//            super.attachBaseContext(newBase);
//        }
//    }
//
//    public void callGetGeoCoderApi(double latitude, double longitude, int maxResult, IGeoCoderAddress iGeoCoderAddress) {
//
////        showProgressDialog("");
//
//
//        ApiProxy apiProxy = ApiProxyImpl.getInstance();
//        apiProxy.getGeoCoderData(new DataCallback<GeocoderOutputModel>() {
//
//            @Override
//            public void onSuccess(GeocoderOutputModel geocoderOutputmodel) {
//                if (geocoderOutputmodel != null && geocoderOutputmodel.getStatus() != null) {
//                    Logger.debug("" + geocoderOutputmodel);
//                    dismissProgressDialog();
//                    if (geocoderOutputmodel.getStatus().equalsIgnoreCase(AppConstants.GeoCodeStatus.OK)) {
//                        iGeoCoderAddress.onGetGeoCoderAddress(onGetUserCurrentLocationAddress(geocoderOutputmodel));
//                    } else {
//                        iGeoCoderAddress.onGetGeoCoderAddressFail(geocoderOutputmodel.getStatus());
//                    }
//                }
//            }
//
//            @Override
//            public void onError(ErrorResponseModel errorResponse) {
//                dismissProgressDialog();
//                UiUtils.displaySnackbar(activity, errorResponse.getErrorMessage(), AppConstants.MessageType.ERROR);
//            }
//        }, latitude + "," + longitude);
//
//
//    }
//
//    public AddAddressInputModel onGetUserCurrentLocationAddress(GeocoderOutputModel geocoderOutputmodel) {
//        AddAddressInputModel addAddressInputModel = new AddAddressInputModel();
//        if (geocoderOutputmodel != null && geocoderOutputmodel.getResults() != null && !geocoderOutputmodel.getResults().isEmpty()) {
//            addAddressInputModel = updateAddress(geocoderOutputmodel);
//        }
//        return addAddressInputModel;
//    }
//
//    private AddAddressInputModel updateAddress(GeocoderOutputModel geocoderOutputmodel) {
//        AddAddressInputModel addAddressInputModel = new AddAddressInputModel();
//        ResultsItem resultsItem = geocoderOutputmodel.getResults().get(0);
//        String zipCode = "";
//        String streetNumber = "";
//        String premise = "";
//        String formatedAddress = "";
//        for (int i = 0; i < resultsItem.getAddressComponents().size(); i++) {
//            for (int j = 0; j < resultsItem.getAddressComponents().get(i).getTypes().size(); j++) {
//                if ("postal_code".equalsIgnoreCase(resultsItem.getAddressComponents().get(i).getTypes().get(j))) {
//                    zipCode = resultsItem.getAddressComponents().get(i).getLongName();
//                } else if ("street_number".equalsIgnoreCase(resultsItem.getAddressComponents().get(i).getTypes().get(j))) {
//                    streetNumber = resultsItem.getAddressComponents().get(i).getLongName();
//                } else if ("premise".equalsIgnoreCase(resultsItem.getAddressComponents().get(i).getTypes().get(j))) {
//                    premise = resultsItem.getAddressComponents().get(i).getLongName();
//                }
//            }
//        }
//        formatedAddress = resultsItem.getFormattedAddress();
//        addAddressInputModel.setStreetNum(streetNumber);
//        if (resultsItem.getGeometry() != null && resultsItem.getGeometry().getLocation() != null) {
//            addAddressInputModel.setAddressLatitude(DataConverter.getStringValueOf(resultsItem.getGeometry().getLocation().getLat()));
//            addAddressInputModel.setAddressLongitude(DataConverter.getStringValueOf(resultsItem.getGeometry().getLocation().getLng()));
//        }
//        addAddressInputModel.setLocationName(formatedAddress);
//        addAddressInputModel.setFlatNum(premise);
//        addAddressInputModel.setZipCode(zipCode);
//        addAddressInputModel.setPlaceId(resultsItem.getPlaceId());
//        return addAddressInputModel;
//    }

    /**
     * Show progress dialog
     *
     * @param message
     */
    public void showProgressDialog(String message) {

        if (customProgressDialog == null) {
            return;
        }

        if (customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }

        customProgressDialog.setDisplayMessage(message);

        if (activity != null && !activity.isFinishing()) {
            customProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (!isFinishing() && customProgressDialog != null && customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }
    }

    /**
     * Check delivery agent device battery status
     */
//    private void checkDeliveryAgentDeviceBattery() {
//
//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
//
//        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        Intent batteryStatus = this.registerReceiver(null, filter);
//        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
//                || status == BatteryManager.BATTERY_STATUS_FULL;
//
//        if (!isCharging) {
//
//            BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
//            int batLevel = bm != null ? bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) : 0;
//
//            try {
//
//                int batteryLevelMetaData = AppConstants.MIN_BATTERY_PERCENTAGE_VALUE;
//                if (batLevel <= batteryLevelMetaData) {
//                    //If app is in foreground then trigger below broadcast else add code to send local notification.
//                    Intent localIntent = new Intent(AppConstants.LOCAL_BROADCAST_MANAGER_FOR_CRITICAL_BATTERY);
//                    localBroadcastManager.sendBroadcast(localIntent);
//                }
//
//            } catch (Exception e) {
//                Logger.error(e.getMessage());
//            }
//        }
//    }

//    protected boolean isLocationServiceRunning() {
//        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : Objects.requireNonNull(activityManager).getRunningServices(Integer.MAX_VALUE)) {
//            if (com.deliverypulse.utils.services.location.LocationTracker.class.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Returns the number of fragments in the stack.
     *
     * @return the number of fragments in the stack.
     */
    public int size() {
        return getFragments().size();
    }

    /**
     * Pushes a fragment to the top of the stack.
     *
     * @param <T>           the type parameter
     * @param fragmentClass the fragment class
     * @param bundle        the bundle
     */
    public <T extends Fragment> void add(@NonNull Class<T> fragmentClass, Bundle bundle) {

        Fragment top = peek();
        Fragment fragment = manager.findFragmentByTag(
                fragmentClass.getSimpleName());
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                fragment.setArguments(bundle);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }
        if (fragment != null) {
            if (top != null) {
                manager.beginTransaction().hide(top)
                        .add(containerId, fragment, indexToTag(manager.getBackStackEntryCount() + 1))
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            } else {
                manager.beginTransaction()
                        .add(containerId, fragment, indexToTag(0))
                        .commitAllowingStateLoss();
            }
            manager.executePendingTransactions();
        }
    }

    /**
     * Pops the top item if the stack.
     * If the fragment implements {@link OnBackPressedHandlingFragment}, calls {@link OnBackPressedHandlingFragment#onBackPressed()} instead.
     * If {@link OnBackPressedHandlingFragment#onBackPressed()} returns false the fragment gets popped.
     *
     * @return true if a fragment has been popped or if {@link OnBackPressedHandlingFragment#onBackPressed()} returned true;
     */
    public void back() {
        Fragment top = peek();
        if (top instanceof OnBackPressedHandlingFragment) {
            if (((OnBackPressedHandlingFragment) top).onBackPressed()) {
                pop();
            }
            return;
        }
        pop();
    }

    /**
     * Pops the topmost fragment from the stack.
     * The lowest fragment can't be popped, it can only be replaced.
     *
     * @return false if the stack can't pop or true if a top fragment has been popped.
     */
    private boolean pop() {
        if (manager.getBackStackEntryCount() == 0) {
            return false;
        }
        for (int i = 0; i < manager.getBackStackEntryCount() + 1; i++) {
            manager.popBackStackImmediate();
        }
        return true;
    }

    /**
     * Replaces stack contents with just one fragment.
     *
     * @param <T>           the type parameter
     * @param fragmentClass the fragment class
     * @param bundle        the bundle
     */
    public <T extends Fragment> void replace(@NonNull Class<T> fragmentClass, Bundle bundle) {
        Fragment fragment = manager.findFragmentByTag(
                fragmentClass.getSimpleName());
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                fragment.setArguments(bundle);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }
        if (fragment != null) {
            if (manager.getBackStackEntryCount() != 0) {
                manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            manager.beginTransaction()
                    .replace(containerId, fragment, indexToTag(0))
                    .commitAllowingStateLoss();

            manager.executePendingTransactions();
        }
    }

    /**
     * Returns the topmost fragment in the stack.
     *
     * @return the fragment
     */
    public Fragment peek() {
        return manager.findFragmentById(containerId);
    }

    /**
     * Returns a back fragment if the fragment is of given class.
     * If such fragment does not exist and activity implements the given class then the activity will be returned.
     *
     * @param <T>          a type of callback.
     * @param fragment     a fragment to search from.
     * @param callbackType a class of type for callback to search.
     * @return a back fragment or activity.
     */
    @SuppressWarnings("unchecked")
    public <T> T findCallback(Fragment fragment, Class<T> callbackType) {

        Fragment back = getBackFragment(fragment);

        if (back != null && callbackType.isAssignableFrom(back.getClass())) {
            return (T) back;
        }

        if (callbackType.isAssignableFrom(activity.getClass())) {
            return (T) activity;
        }

        return null;
    }

    private Fragment getBackFragment(Fragment fragment) {
        List<Fragment> fragments = getFragments();
        for (int f = fragments.size() - 1; f >= 0; f--) {
            if (fragments.get(f) == fragment && f > 0) {
                return fragments.get(f - 1);
            }
        }
        return null;
    }

    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>(manager.getBackStackEntryCount() + 1);
        for (int i = 0; i < manager.getBackStackEntryCount() + 1; i++) {
            Fragment fragment = manager.findFragmentByTag(indexToTag(i));
            if (fragment != null) {
                fragments.add(fragment);
            }
        }
        return fragments;
    }

    private String indexToTag(int index) {
        return Integer.toString(index);
    }

    /**
     * The interface On back pressed handling fragment.
     */
    interface OnBackPressedHandlingFragment {
        /**
         * On back pressed boolean.
         *
         * @return the boolean
         */
        boolean onBackPressed();
    }

//    private class DialogCommunicationInterFace implements CommunicationInterface {
////        @Override
////        public void setCommunication(String message, String bookingsId) {
////
////            boolean isBookingIdEmpty = TextUtils.isEmpty(bookingsId);
////            boolean isStatusCodeEmpty = TextUtils.isEmpty(message);
////
////            if (isStatusCodeEmpty) {
////                return;
////            }
////
////            if (Config.USER_ROLE.equalsIgnoreCase(DELIVERY_AGENT)) {
////                switch (message) {
////                    case AppConstants.NEW_ORDER_ASSIGNED_BY_ADMIN:
////                        handleOrderAssignedByAdmin(isBookingIdEmpty,bookingsId);
////                        break;
////
////                    case AppConstants.NEW_BOOKING_AVAILABLE:
////                        handleOrderBroardcast(isBookingIdEmpty,bookingsId);
////                        break;
////
////                    case AppConstants.ORDER_CANCEL_BY_ADMIN:
////                    case AppConstants.ORDER_CANCEL_BY_CUSTOMER:
////                        setOrderCancelByCustomer();
////                        break;
////
////                    case AppConstants.STATUS_UPDATE_BY_ADMIN:
////                    case AppConstants.CHAT_MESSAGE_DELIVERY_AGENT:
////                    case AppConstants.CHAT_MESSAGE_CUSTOMER:
////                    case AppConstants.CHAT_MESSAGE_FROM_ADMIN:
////                    case AppConstants.CHAT_MESSAGE_FROM_BUSINESS_CUSTOMER:
////                        handleMessageFromBusinessCustomer(isBookingIdEmpty,bookingsId);
////                        break;
////                    default:
////                        break;
////                }
////            } else {
////
////                switch (message) {
////                    case AppConstants.DELIVERY_COMPLETE:
////                        if (!isBookingIdEmpty) {
////                            Intent intent = new Intent(activity, ShipmentInvoiceActivity.class);
////                            intent.putExtra(AppConstants.EXTRA_BOOKING_ID, bookingsId);
////                            intent.putExtra(AppConstants.SHIPMENT, AppConstants.ShipmentType.ACTIVE);
////                            intent.putExtra(AppConstants.DELIVERY_STATUS, AppConstants.DeliveryStatus.STATUS_DELIVERED_ID);
////                            activity.startActivity(intent);
////                        }
////                        break;
////                    case AppConstants.ADDITIONAL_CHARGES_ADDED:
////                        Bundle bundle = new Bundle();
////                        bundle.putString(AppConstants.EXTRA_BOOKING_ID, bookingsId);
////                        goToActivity(activity, ViewShipmentDetailsActivity.class,bundle);
////                        break;
////                    case AppConstants.MESSAGE_TYPE_BUY_ME_SURCHARGE_ADDED:
////                        Bundle bundleBuyMe = new Bundle();
////                        bundleBuyMe.putString(AppConstants.EXTRA_BOOKING_ID, bookingsId);
////                        goToActivity(activity, BuyMeDetailsActivity.class,bundleBuyMe);
////                        break;
////                    case AppConstants.DELIVERY_AGENT_ACCEPT_BOOKING:
////                    case AppConstants.DELIVERY_AGENT_ON_WAY:
////                    case AppConstants.DELIVERY_AGENT_ARRIVED:
////                    case AppConstants.DELIVERY_PICKUP_STARTED:
////                    case AppConstants.DELIVERY_AGENT_IN_EMERGENCY:
////                    case AppConstants.STATUS_UPDATE_BY_ADMIN:
////                    case AppConstants.CHAT_MESSAGE_DELIVERY_AGENT:
////                    case AppConstants.CHAT_MESSAGE_CUSTOMER:
////                    case AppConstants.CHAT_MESSAGE_FROM_ADMIN:
////                    case AppConstants.CHAT_MESSAGE_FROM_BUSINESS_CUSTOMER:
////
////                        if (!isBookingIdEmpty) {
////                            Intent trackOrderIntent = new Intent(activity, TrackOrderActivity.class);
////                            trackOrderIntent.putExtra(AppConstants.EXTRA_BOOKING_ID, bookingsId);
////                            activity.startActivity(trackOrderIntent);
////                        }
////                        break;
////                    case AppConstants.BUYME_BOOKING_NOTI:
////
////                        if (!isBookingIdEmpty) {
////                            Intent buyMeOrderIntent = new Intent(activity, BuyMeDetailsActivity.class);
////                            buyMeOrderIntent.putExtra(AppConstants.EXTRA_BOOKING_ID, bookingsId);
////                            activity.startActivity(buyMeOrderIntent);
////                        }
////                        break;
////                    default:
////                        break;
////                }
////            }
////        }
//
//        private void setOrderCancelByCustomer() {
//            if (isDriverMenuActivityOpen()) {
//                ((DeliveryAgentMenuActivity) activity).setOfflineOnlineMode();
//            }
//        }
//
//        private void handleMessageFromBusinessCustomer(boolean isBookingIdEmpty, String actionType){
//            if (activity instanceof DeliveryAgentMenuActivity && !isBookingIdEmpty) {
//                ((DeliveryAgentMenuActivity) activity).gotoCurrentJobFragment(true, actionType, "", false);
//            }
//        }
//
//        private void handleOrderAssignedByAdmin(boolean isBookingIdEmpty, String actionType){
//            if (!isBookingIdEmpty) {
//                stopMediaPlayer();
//                ((DeliveryAgentMenuActivity) activity).gotoCurrentJobFragment(true, actionType, "", false);
//            }
//        }
//
//        private void handleOrderBroardcast(boolean isBookingIdEmpty, String actionType){
//            if (!isBookingIdEmpty) {
//                stopMediaPlayer();
//                ((DeliveryAgentMenuActivity) activity).gotoCurrentJobFragment(true, actionType, "", true);
//            }
//        }
//
//        private void stopMediaPlayer() {
//            if (mediaPlayer != null) {
//                try {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    mediaPlayer = null;
//                } catch (Exception e) {
//                    Logger.debug(e.getMessage());
//                }
//            }
//        }
//
//    }
//
    protected class BackButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            hideKeyboard(activity);
            finish();

        }

    }
//
    private class GoToSettings implements View.OnClickListener {

        final boolean toCloseActivity;
        final boolean toExitFromApp;

        GoToSettings(boolean toCloseActivity, boolean toExitFromApp) {
            this.toCloseActivity = toCloseActivity;
            this.toExitFromApp = toExitFromApp;
        }

        private void navigateToAppSetting() {
            Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + activity.getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(i);
        }

        @Override
        public void onClick(View v) {
            DbPreference.putBoolean(activity, AppConstants.IS_NAVIGATED_TO_SETTINGS, true);
            navigateToAppSetting();
            appPermission.closeDialog();

            if (toCloseActivity) {
                onBackPressed();
            }
        }
    }

    private class OkButtonListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {

            appPermission.requestPermissionList(activity, permissionArray, MULTIPLE_PERMISSION_REQUEST);
            appPermission.closeDialog();
        }
    }

    private class CancelButtonListener implements View.OnClickListener {

        final boolean toCloseActivity;
        final boolean toExitFromApp;

        CancelButtonListener(boolean toCloseActivity, boolean toExitFromApp) {
            this.toCloseActivity = toCloseActivity;
            this.toExitFromApp = toExitFromApp;
        }

        @Override
        public void onClick(View v) {
            appPermission.closeDialog();

            if (toExitFromApp) {
                clearActivityStack();
            }

            if (toCloseActivity) {
                onBackPressed();
            }

        }
    }


}
