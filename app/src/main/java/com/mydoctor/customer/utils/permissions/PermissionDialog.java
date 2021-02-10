package com.mydoctor.customer.utils.permissions;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mydoctor.customer.R;


class PermissionDialog extends Dialog {

    private final Activity activity;
    private final String dialogMessage;
    private final View.OnClickListener onClickListener;
    private final View.OnClickListener onCancelClickListener;
    private Button btnOkay;
    private Button btnCancel;


    public PermissionDialog(@NonNull Activity activity, String msg, View.OnClickListener onClickListener, View.OnClickListener onCancelClickListener) {
        super(activity);
        this.activity = activity;
        this.dialogMessage = msg;
        this.onClickListener = onClickListener;
        this.onCancelClickListener = onCancelClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        setContentView(R.layout.dialog_common);
        initUI();
        setListener();
    }

    private void initUI() {
        TextView tvNotificationMsg = findViewById(R.id.txt_common_msg);
        btnOkay = findViewById(R.id.btn_okay);
        btnCancel = findViewById(R.id.btn_cancel);

        btnOkay.setText(activity.getResources().getString(R.string.txt_allow));
        btnCancel.setText(activity.getResources().getString(R.string.cancel));
        tvNotificationMsg.setText(dialogMessage);

    }

    private void setListener() {
        btnOkay.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onCancelClickListener);
    }

}
