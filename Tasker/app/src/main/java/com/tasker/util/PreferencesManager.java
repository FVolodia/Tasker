package com.tasker.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by FVolodia on 21.01.16.
 */
public class PreferencesManager {
    private static final String APP_PREFERENCES_SPLASH = "_splash";

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static void putString(Context context, String key, String value) {
        getPreferences(context).edit().putString(key, value).apply();
    }

    private static String getString(Context context, String key) {
        return getPreferences(context).getString(key, null);
    }

    private static void putInt(Context context, String key, int value) {
        getPreferences(context).edit().putInt(key, value).apply();
    }

    private static int getInt(Context context, String key) {
        return getPreferences(context).getInt(key, 0);
    }

    private static void putBool(Context context, String key, boolean value) {
        getPreferences(context).edit().putBoolean(key, value).apply();
    }

    private static boolean getBool(Context context, String key) {
        return getPreferences(context).getBoolean(key, false);
    }

    private static void putLong(Context context, String key, long value) {
        getPreferences(context).edit().putLong(key, value).apply();
    }

    private static long getLong(Context context, String key) {
        return getPreferences(context).getLong(key, 0);
    }

    private static void putFloat(Context context, String key, float value) {
        getPreferences(context).edit().putFloat(key, value).apply();
    }

    private static float getFloat(Context context, String key) {
        return getPreferences(context).getFloat(key, 0);
    }

    private static String getString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear().commit();
    }

    public static void putSplash(Context context, boolean splash) {
        putBool(context, APP_PREFERENCES_SPLASH, splash);
    }

    public static boolean getSplash(Context context) {
        return getBool(context, APP_PREFERENCES_SPLASH);
    }

}

