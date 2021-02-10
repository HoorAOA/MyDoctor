package com.mydoctor.customer.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.mydoctor.customer.R;
import com.mydoctor.customer.delegates.CommunicationInterfaceAll;
import com.mydoctor.customer.utils.Logger;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class CommonActionBottomSheet extends BottomSheetDialogFragment {


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
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.txt_message)
    TextView txtMessage;
    private String actionMessage;
    private String message;
    private String noButtonText;
    private String yesButtonText;
    private String negativeAction;
    private String positiveAction;
    private CommunicationInterfaceAll communicationInterfaceAll;

    public CommonActionBottomSheet() {

    }

    public CommonActionBottomSheet(String noButtonText, String yesButtonText, String actionMessage, String message, String negativeAction, String positiveAction, CommunicationInterfaceAll communicationInterfaceAll) {
        this.noButtonText = noButtonText;
        this.yesButtonText = yesButtonText;
        this.message = message;
        this.actionMessage = actionMessage;
        this.negativeAction = negativeAction;
        this.positiveAction = positiveAction;
        this.communicationInterfaceAll = communicationInterfaceAll;
    }

    @OnClick({R.id.btn_yes, R.id.btn_no})
    public void onViewClicked(View view) {

        if (view.getId() == R.id.btn_yes) {
            callPositiveAction();
        } else if (view.getId() == R.id.btn_no) {
            callNegativeAction();
        }

    }

    private void callPositiveAction() {

        if (communicationInterfaceAll != null) {
            communicationInterfaceAll.setCommunication(message, actionMessage, positiveAction);
        }
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog3 = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog3.setOnShowListener(dialog4 -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog4;
            FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        return dialog3;

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.bottomsheet_common_action, null);
        ButterKnife.bind(this, contentView);

        dialog.setContentView(contentView);

        setArgumentsData();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetCallback);
        }
        ((View) contentView.getParent()).setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.shape_fare_bg));
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

    private void setArgumentsData() {

        txtMessage.setText(message);
        btnNo.setText(noButtonText);
        btnYes.setText(yesButtonText);

    }

    private void callNegativeAction() {

        if (communicationInterfaceAll != null) {
            communicationInterfaceAll.setCommunication(message, actionMessage, negativeAction);
        }
        dismiss();
    }


}
