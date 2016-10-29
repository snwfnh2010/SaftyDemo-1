package com.example.a360safty.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class AppLockDao {
    private Context mContext;
    private AppLockDao(Context mContext){
        this.mContext = mContext;
        applockOpenHelper = new AppLockOpenHelper(mContext,"applock.db",null,1);

    };

    private static AppLockDao mAppLockDao = null;
    private AppLockOpenHelper applockOpenHelper;

    public static AppLockDao getInstance(Context ctx){
        if(mAppLockDao == null){
            mAppLockDao = new AppLockDao(ctx);
        }
        return mAppLockDao;
    }

    public void insert(String packageName){
        SQLiteDatabase db = applockOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("packagename", packageName);

        db.insert("applock", null, contentValues);

        db.close();

        mContext.getContentResolver().notifyChange(Uri.parse("content://com.example.a360safty/applock/change"), null);
    }

    public void delete(String packageName){
        SQLiteDatabase db = applockOpenHelper.getWritableDatabase();

        db.delete("applock", "packagename = ?", new String[]{packageName});

        db.close();
        mContext.getContentResolver().notifyChange(Uri.parse("content://com.example.a360safty/applock/change"), null);
    }


    public List<String> findAll(){
        List<String> packageList = new ArrayList<String>();
        SQLiteDatabase db = applockOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("applock", new String[]{"packagename"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            packageList.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return packageList;
    }
}
