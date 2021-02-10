package com.mydoctor.customer.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.mydoctor.customer.utils.InputUtils;
import com.mydoctor.customer.utils.UiUtils;


public class ActivityHelperImpl implements ActivityHelper {

    private final Context activity;

    ActivityHelperImpl(Context activity) {

        this.activity = activity;
    }

    @Override
    public void hideKeyboard(View view) {

        InputUtils.hideKeyboard(activity, view);
    }

    @Override
    public Drawable createRepeatableDrawable(int imageId) {
        return null;
    }


    @Override
    public void switchToActivity(Activity current, Class<? extends Activity> otherActivityClass, Bundle extras) {

        UiUtils.switchToActivity(current, otherActivityClass, extras);

    }

    @Override
    public void goToActivity(Activity current, Class<? extends Activity> otherActivityClass, Bundle extras) {

        UiUtils.goToActivity(current, otherActivityClass, extras);
    }

    @Override
    public void initUI() {
        // Any common code to be implemented before initialization of UI
    }

    @Override
    public void setListeners() {
        // Any common code to be implemented before setting listeners, or common listeners if any
    }
}
