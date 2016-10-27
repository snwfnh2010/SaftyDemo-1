package com.example.a360safty.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class SpUtil {
    private static SharedPreferences sSharedPreferences;

    public static void putString(Context context,String key,String value){
        if (sSharedPreferences==null){
            sSharedPreferences=context.getSharedPreferences("pwd",Context.MODE_PRIVATE);
        }
        sSharedPreferences.edit().putString(key,value).commit();
    }

    public static String getString(Context context,String key,String value){
        if (sSharedPreferences==null){
            sSharedPreferences=context.getSharedPreferences("pwd",Context.MODE_PRIVATE);
        }
        return sSharedPreferences.getString(key,value);
    }

    public static void putBoolean(Context context,String key,boolean value){
        if(sSharedPreferences == null){
            sSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sSharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context,String key,boolean defValue){
        if(sSharedPreferences == null){
            sSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sSharedPreferences.getBoolean(key, defValue);
    }




}
