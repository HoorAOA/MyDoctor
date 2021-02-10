package com.mydoctor.customer.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mydoctor.customer.R;
import com.mydoctor.customer.delegates.CommunicationInterface;

import java.util.Objects;

public class CommonInfoDialog extends Dialog {


    private final String message;
    private final String okayMsg;
    private String actionMessage;
    private CommunicationInterface communicationInterface;

    public CommonInfoDialog(Activity activity, String okayMsg, String message, String actionMessage, CommunicationInterface communicationInterface, boolean isSuccessOrFailure) {
        super(activity);
        this.message = message;
        this.okayMsg = okayMsg;
        this.actionMessage = actionMessage;
        this.communicationInterface = communicationInterface;

    }

    public CommonInfoDialog(Activity activity, String okayMsg, String message) {
        super(activity);
        this.message = message;
        this.okayMsg = okayMsg;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_info);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iniUI();
    }

    private void iniUI() {

        TextView txtNotificationMsg = findViewById(R.id.txt_common_msg);
        txtNotificationMsg.setText(message);

        Button btnOkay = findViewById(R.id.btn_okay);
        btnOkay.setText(okayMsg);
        btnOkay.setOnClickListener(new OkayButtonOnClickListener());


    }

    private class OkayButtonOnClickListener implements
            View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(actionMessage)) {
                sendMessage();
            }
            dismiss();
        }

        private void sendMessage() {
            String[] param = actionMessage.split("~");
            if (param.length > 1) {
                communicationInterface.setCommunication(param[0], param[1]);
            } else {
                communicationInterface.setCommunication(actionMessage, "");
            }
        }

    }

}
