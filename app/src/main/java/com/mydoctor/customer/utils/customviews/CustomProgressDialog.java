package com.mydoctor.customer.utils.customviews;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mydoctor.customer.R;

public class CustomProgressDialog extends Dialog {

    private final ImageView rotatingImageView;
    private final TextView txtMessage;
    private String message;

    public CustomProgressDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);

        message = context.getResources().getString(R.string.please_wait);
        RelativeLayout layout = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        rotatingImageView = new ImageView(context);
        rotatingImageView.setImageResource(R.drawable.loader);
        rotatingImageView.setId(View.generateViewId());

        layout.addView(rotatingImageView, params);

        txtMessage = new TextView(context);
        txtMessage.setText(message);


//        if (Build.VERSION.SDK_INT < 23) {
//            txtMessage.setTextAppearance(context, R.style.MediumWhiteBoldColorTextViewStyle);
//        } else {
//            txtMessage.setTextAppearance(R.style.MediumWhiteBoldColorTextViewStyle);
//        }

        params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, rotatingImageView.getId());

        layout.addView(txtMessage, params);

        addContentView(layout, params);

    }

    public void setDisplayMessage(String msg) {
        this.message = msg;
        if (txtMessage != null && !TextUtils.isEmpty(msg)) {
            txtMessage.setVisibility(View.VISIBLE);
            txtMessage.setText(message);
        } else {
            hideMessage();
        }

    }

    private void hideMessage() {
        if (txtMessage != null) {
            txtMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void show() {
        super.show();

        RotateAnimation anim = new RotateAnimation(0.0f, 359.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1500);

        rotatingImageView.setAnimation(anim);
        rotatingImageView.startAnimation(anim);

    }
}
