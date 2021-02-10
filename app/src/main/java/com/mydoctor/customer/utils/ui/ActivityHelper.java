package com.mydoctor.customer.utils.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

interface ActivityHelper {

    void hideKeyboard(View view);

    Drawable createRepeatableDrawable(int imageId);

    void switchToActivity(Activity current,
                          Class<? extends Activity> otherActivityClass,
                          Bundle extras);

    void goToActivity(Activity current,
                      Class<? extends Activity> otherActivityClass,
                      Bundle extras);

    void initUI();

    void setListeners();
}
