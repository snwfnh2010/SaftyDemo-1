package com.example.a360safty.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class AppLockOpenHelper extends SQLiteOpenHelper {
    private static final String CREAT_APPLOCK = "create table applock (" +
            "_id integer primary key autoincrement, packagename text)";
    private Context mContext;

    public AppLockOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAT_APPLOCK);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
