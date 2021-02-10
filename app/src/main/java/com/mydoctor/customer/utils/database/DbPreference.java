package com.mydoctor.customer.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import net.sqlcipher.database.SQLiteDatabase;

public class DbPreference {

    private static final String TABLE_DB_PREFERENCE = "db_preference";
    private static final String KEY = "key";
    private static final String KEY_VALUE = "value";
    private static DbPreference instance;

    private DbPreference() {

    }

    @SuppressWarnings("unused")
    public static synchronized DbPreference getInstance() {

        if (instance == null) {
            instance = new DbPreference();
        }
        return instance;
    }

    private static void putValue(Context mContext, String key, String value) {
        SQLiteDatabase db = DbHelper.getDatabaseInstance(mContext);
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put(KEY_VALUE, value);
        if (db != null) {
            // insert row
            db.insertWithOnConflict(TABLE_DB_PREFERENCE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    private static String getValue(Context mContext, String key) {
        SQLiteDatabase db = DbHelper.getDatabaseInstance(mContext);
        String value = "";
        if (db != null) {
            String selection = KEY + " =?";
            String[] selectionArgs = {key};
            try (Cursor cursor = db.query(TABLE_DB_PREFERENCE, null, selection, selectionArgs, null, null, null)) {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        value = cursor.getString(cursor.getColumnIndex(KEY_VALUE));
                    }
                    cursor.close();
                }
            }
        }
        return value;
    }

    @SuppressWarnings("unused")
    public static void putString(Context mContext, String key, String value) {
        putValue(mContext, key, value);
    }

    @SuppressWarnings("unused")
    public static String getString(Context mContext, String key, String defaultValue) {
        String value = getValue(mContext, key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    @SuppressWarnings("unused")
    public static void putLong(Context mContext, String key, long value) {
        putValue(mContext, key, String.valueOf(value));
    }

    @SuppressWarnings("unused")
    public static long getLong(Context mContext, String key, long defaultValue) {
        String value = getValue(mContext, key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    @SuppressWarnings("unused")
    public static void putInt(Context mContext, String key, int value) {
        putValue(mContext, key, String.valueOf(value));
    }

    @SuppressWarnings("unused")
    public static int getInt(Context mContext, String key, int defaultValue) {
        String value = getValue(mContext, key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    @SuppressWarnings("unused")
    public static void putFloat(Context mContext, String key, float value) {
        putValue(mContext, key, String.valueOf(value));
    }

    @SuppressWarnings("unused")
    public static float getFloat(Context mContext, String key, float defaultValue) {
        String value = getValue(mContext, key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Float.parseFloat(value);
    }

    @SuppressWarnings("unused")
    public static void putBoolean(Context mContext, String key, boolean value) {
        putValue(mContext, key, String.valueOf(value));
    }

    @SuppressWarnings("unused")
    public static boolean getBoolean(Context mContext, String key, boolean defaultValue) {
        String value = getValue(mContext, key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    @SuppressWarnings("unused")
    public static void remove(Context mContext, String key) {
        SQLiteDatabase db = DbHelper.getDatabaseInstance(mContext);
        if (db != null) {
            db.delete(TABLE_DB_PREFERENCE, KEY + " =?", new String[]{key});
        }
    }

    @SuppressWarnings("unused")
    public static void clearPreferences(Context mContext) {
        SQLiteDatabase db = DbHelper.getDatabaseInstance(mContext);
        if (db != null) {
            db.delete(TABLE_DB_PREFERENCE, "1", null);
        }
    }
}