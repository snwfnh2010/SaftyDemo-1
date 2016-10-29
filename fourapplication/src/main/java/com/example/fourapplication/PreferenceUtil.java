package com.example.fourapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by snwfnh on 2016/10/29.
 */
public class PreferenceUtil {
    public static String getGesturePassword(Context context) {
        SharedPreferences sp = context.getSharedPreferences("gesturePassword", Context.MODE_PRIVATE);
        String password = sp.getString("password", "");
        return password;
    }

    public static void setGesturePassword(Context context, String gesturePassword) {
        SharedPreferences sp = context.getSharedPreferences("gesturePassword", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("password", gesturePassword);
        editor.commit();
    }
}
