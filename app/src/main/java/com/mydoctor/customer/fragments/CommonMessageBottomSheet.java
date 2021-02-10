package com.mydoctor.customer.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.mydoctor.customer.R;
import com.mydoctor.customer.delegates.CommunicationInterface;
import com.mydoctor.customer.utils.Logger;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class CommonMessageBottomSheet extends BottomSheetDialogFragment {


    private final BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View view, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View view, float v) {
            Logger.debug("On Slide");
        }
    };
    @BindView(R.id.btn_okay)
    Button btnOkay;
    @BindView(R.id.txt_message)
    TextView txtMessage;
    private CommunicationInterface communicationInterface;
    private String message;
    private String actionType;

    public CommonMessageBottomSheet() {
    }

    public CommonMessageBottomSheet(String message, String actionType, CommunicationInterface communicationInterface) {
        this.message = message;
        this.actionType = actionType;
        this.communicationInterface = communicationInterface;
    }

    @OnClick(R.id.btn_okay)
    void onOkayButtonClick() {
        if (communicationInterface != null) {
            communicationInterface.setCommunication(message, actionType);
        }
        callDismissMessageDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(dialog1 -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog1;
            FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        return dialog;

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.bottomsheet_common_message, null);
        ButterKnife.bind(this, contentView);

        dialog.setContentView(contentView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        setMessageArgumentsData();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetCallback);
        }
        ((View) contentView.getParent()).setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.shape_fare_bg));
    }

    private void setMessageArgumentsData() {

        if (!TextUtils.isEmpty(message)) {
            txtMessage.setText(message);
        }

    }

    private void callDismissMessageDialog() {
        dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            // dark navigation bar icons
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

}
