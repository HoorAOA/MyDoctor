package com.mydoctor.customer.utils.ui;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.ContextThemeWrapper;

import java.util.Locale;

public class LocaleUtils {

    private static Locale sLocale;

    private LocaleUtils() {
    }

    public static void setLocale(Locale locale) {
        sLocale = locale;
        if (sLocale != null) {
            Locale.setDefault(sLocale);
        }
    }

    public static Locale getsLocale() {
        return sLocale;
    }

    public static void updateConfig(ContextThemeWrapper wrapper) {
        if (sLocale != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration configuration = new Configuration();
            configuration.setLocale(sLocale);
            wrapper.applyOverrideConfiguration(configuration);
        }
    }

    public static void updateConfig(Application app, Configuration configuration) {
        Configuration config = new Configuration(configuration);
        config.locale = sLocale;
        Resources res = app.getBaseContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}