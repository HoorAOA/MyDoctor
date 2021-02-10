package com.mydoctor.customer.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
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
public class CommonActionMessageBottomSheet extends BottomSheetDialogFragment {


    private final BottomSheetBehavior.BottomSheetCallback callback = new BottomSheetBehavior.BottomSheetCallback() {
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
    @BindView(R.id.txt_message)
    TextView txtMessage;
    @BindView(R.id.btn_okay)
    Button btnOkay;
    private String actionMsg;
    private String message;
    private String positiveAct;
    private CommunicationInterfaceAll communicationInterface;

    public CommonActionMessageBottomSheet() {
    }

    public CommonActionMessageBottomSheet(String actionMsg, String message, String positiveAct, CommunicationInterfaceAll communicationInterface) {
        this.message = message;
        this.actionMsg = actionMsg;
        this.positiveAct = positiveAct;
        this.communicationInterface = communicationInterface;
    }

    @OnClick(R.id.btn_okay)
    public void onViewClicked() {
        callOkAction();
    }

    private void callOkAction() {
        if (communicationInterface != null) {
            communicationInterface.setCommunication(message, actionMsg, positiveAct);
        }
        dismiss();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.bottomsheet_common_message, null);
        ButterKnife.bind(this, contentView);

        dialog.setContentView(contentView);

        setArgumentsData();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(callback);
        }
        ((View) contentView.getParent()).setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.shape_fare_bg));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog sheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        sheetDialog.setOnShowListener(sheetDialog2 -> {
            BottomSheetDialog d = (BottomSheetDialog) sheetDialog2;
            FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        return sheetDialog;

    }

    private void setArgumentsData() {
        txtMessage.setText(message);
    }


}
