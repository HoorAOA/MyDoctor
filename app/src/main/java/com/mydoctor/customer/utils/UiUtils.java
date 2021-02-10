package com.mydoctor.customer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mydoctor.customer.R;
import com.mydoctor.customer.utils.ui.AbstractActivity;
import com.google.android.material.snackbar.Snackbar;

public class UiUtils {

    private UiUtils() {
        throw new IllegalStateException("UiUtils class");
    }

    public static void switchToActivity(Activity current, Class<? extends Activity> otherActivityClass, Bundle extras) {
        Intent intent = new Intent(current, otherActivityClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        current.startActivity(intent);
        current.finish();
    }

    public static void goToActivity(Activity current, Class<? extends Activity> otherActivityClass, Bundle extras) {
        Intent intent = new Intent(current, otherActivityClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        current.startActivity(intent);
    }

    /**
     * Show message in snackbar
     *
     * @param message
     */
    public static void displaySnackbar(Activity activity, String message, AppConstants.MessageType messageType) {

        if (activity != null) {
            View view = activity.getWindow().getDecorView().getRootView();

            if (view == null) {
                return;
            }
            try {
                view = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            } catch (Exception e) {
                Logger.debug(e.getMessage());
            }

            if (activity instanceof AbstractActivity) {
                ((AbstractActivity) activity).hideKeyboard(view);
            }

            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);

            TextView tvSnackBar = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);

            tvSnackBar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            tvSnackBar.setTextAppearance(activity, R.style.MediumBoldPrimaryColorTextViewStyle);

            switch (messageType) {

                case ERROR:
                    tvSnackBar.setBackgroundColor(activity.getResources().getColor(R.color.red_color));
                    break;
                case SUCCESS:
                    tvSnackBar.setBackgroundColor(activity.getResources().getColor(R.color.green_color));
                    break;
                default:
                    tvSnackBar.setBackgroundColor(activity.getResources().getColor(R.color.grey_color));

            }

            snackbar.getView().setPadding(0, 0, 0, 0);

            snackbar.show();

        }
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getApplicationContext()
                .getResources().getDisplayMetrics();
        return Math.round(dp
                * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * @param countryCode
     * @param isSymbolRequired
     * @return
     */
//    public static String getCountryCode(String countryCode, boolean isSymbolRequired) {
//
//        String pCountryCode = countryCode;
//
//        String changedPCountryCode = "";
//
//        if (!TextUtils.isEmpty(countryCode)) {
//
//            if (countryCode.contains("+")) {
//                pCountryCode = countryCode.substring(1);
//            }
//
//            if (isSymbolRequired) {
//                changedPCountryCode = "+" + pCountryCode;
//            } else {
//                changedPCountryCode = pCountryCode;
//            }
//
//        }
//
//        return changedPCountryCode;
//    }
//
//    public static void setWeightUnit(Context context, BookingDetailsOutputModel bookingDetailsOutputModel, TextView txtWeight) {
//        String unit = "";
//        int weightCount = 0;
//        int volumeCount = 0;
//
//        if (bookingDetailsOutputModel.getBookingGoodTypeList() != null && !bookingDetailsOutputModel.getBookingGoodTypeList().isEmpty()) {
//
//            for (GoodsTypeModel goodsTypeModel : bookingDetailsOutputModel.getBookingGoodTypeList()) {
//                if (TextUtils.equals(goodsTypeModel.getUnitType(), AppConstants.UNIT_TYE_WEIGHT)) {
//                    weightCount++;
//                } else if (TextUtils.equals(goodsTypeModel.getUnitType(), AppConstants.UNIT_TYE_VOLUME)) {
//                    volumeCount++;
//                }
//            }
//
//            if (weightCount > 0 && volumeCount == 0) {
//                unit = context.getString(R.string.kg);
//            } else if (volumeCount > 0 && weightCount == 0) {
//                unit = context.getString(R.string.cc);
//            }
//        }
//        String weight =  bookingDetailsOutputModel.getBookingInfoModel().getWeight() + unit;
//        txtWeight.setText(weight);
//
//    }
//
//    public static void setWeightUnitBuyMe(Context context, BuyMeBookingDetailsOutputModel bookingBuyMeDetailsOutputModelShipment, TextView txtWeight) {
//        String unit = "";
//        int weightCount = 0;
//        int volumeCount = 0;
//
//        if (bookingBuyMeDetailsOutputModelShipment.getBookingGoodTypeList() != null && !bookingBuyMeDetailsOutputModelShipment.getBookingGoodTypeList().isEmpty()) {
//
//            for (GoodsTypeModel goodsTypeModel : bookingBuyMeDetailsOutputModelShipment.getBookingGoodTypeList()) {
//                if (TextUtils.equals(goodsTypeModel.getUnitType(), AppConstants.UNIT_TYE_WEIGHT)) {
//                    weightCount++;
//                } else if (TextUtils.equals(goodsTypeModel.getUnitType(), AppConstants.UNIT_TYE_VOLUME)) {
//                    volumeCount++;
//                }
//            }
//
//            if (weightCount > 0 && volumeCount == 0) {
//                unit = context.getString(R.string.kg);
//            } else if (volumeCount > 0 && weightCount == 0) {
//                unit = context.getString(R.string.cc);
//            }
//        }
//        String weight =  bookingBuyMeDetailsOutputModelShipment.getWeight() + unit;
//        txtWeight.setText(weight);
//
//    }

}
