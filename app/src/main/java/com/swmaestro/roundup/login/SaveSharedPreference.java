package com.swmaestro.roundup.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by JeongMinCha on 16. 6. 16..
 */
public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";

    static SharedPreferences getSharedPreferences(Context ctxt) {
        return PreferenceManager.getDefaultSharedPreferences(ctxt);
    }

    public static void setUserName(Context ctxt, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctxt).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctxt) {
        return getSharedPreferences(ctxt).getString(PREF_USER_NAME, "");
    }

    public static void clearUserName(Context ctxt) {
        SharedPreferences.Editor editor = getSharedPreferences(ctxt).edit();
        editor.clear();
        editor.commit();
    }
}
