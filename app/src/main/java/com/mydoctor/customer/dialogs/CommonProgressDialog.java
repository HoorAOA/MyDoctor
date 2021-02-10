package com.mydoctor.customer.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.mydoctor.customer.R;

import java.util.Objects;


public class CommonProgressDialog extends Dialog {


    public CommonProgressDialog(Context context) {

        super(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_progress_wheel);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setCanceledOnTouchOutside(false);

        setCancelable(false);
    }

    public void showProgressDialog() {

        if (!this.isShowing()) {

            show();
        }
    }

    public void dismissProgressDialog() {

        if (isShowing()) {

            dismiss();
        }
    }

}