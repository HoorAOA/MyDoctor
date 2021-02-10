package com.mydoctor.customer.utils.permissions;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.mydoctor.customer.R;

class PermissionAlertDialog extends AlertDialog {

    private final Activity activity;
    private final String cancelMsg;
    private final String message;
    private final AlertDialogProperties alertDialogProperties;
    private String okayMsg;

    public PermissionAlertDialog(Activity activity, AlertDialogProperties alertDialogProperties) {

        super(activity);
        this.activity = activity;
        this.message = alertDialogProperties.getMessage();
        this.okayMsg = alertDialogProperties.getOkayButtonText();
        this.cancelMsg = alertDialogProperties.getCancelButtonText();
        this.alertDialogProperties = alertDialogProperties;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iniUI();

    }

    private void iniUI() {

        TextView txtNotificationMsg = findViewById(R.id.txt_common_msg);
        if (alertDialogProperties.getMessageTextColor() == 0) {
            txtNotificationMsg.setTextColor(activity.getResources().getColor(R.color.black));
        } else {
            txtNotificationMsg.setTextColor(alertDialogProperties.getMessageTextColor());
        }

        Button btnCancel = findViewById(R.id.btn_cancel);
        Button btnOkay = findViewById(R.id.btn_okay);


        btnOkay.setOnClickListener(alertDialogProperties.getOkayButtonListener());
        btnCancel.setOnClickListener(alertDialogProperties.getCancelButtonListener());

        txtNotificationMsg.setText(Html.fromHtml(message));
        if (okayMsg == null || okayMsg.length() == 0) {
            okayMsg = activity.getResources().getString(R.string.ok);
        }
        if (cancelMsg == null || cancelMsg.length() == 0) {
            okayMsg = activity.getResources().getString(R.string.cancel);
        }
        btnOkay.setText(okayMsg);
        btnCancel.setText(cancelMsg);

    }

}

