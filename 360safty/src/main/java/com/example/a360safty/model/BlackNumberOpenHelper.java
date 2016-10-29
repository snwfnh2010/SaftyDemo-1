package com.example.a360safty.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by snwfnh on 2016/10/28.
 */
public class BlackNumberOpenHelper extends SQLiteOpenHelper {
    private static final String CREAT_BLACKNUMBER = "create table blacknumber (" +
            "_id integer primary key autoincrement, phone text, mode text)";

    private Context mContext;

    public BlackNumberOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_BLACKNUMBER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
