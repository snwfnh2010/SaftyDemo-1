package com.example.a360safty.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/28.
 */
public class BlackNumberDao {
    private BlackNumberOpenHelper blackNumberOpenHelper;
    private Context context;
    private BlackNumberDao(Context context){
        this.context=context;
        blackNumberOpenHelper = new BlackNumberOpenHelper(context, "blacknumber.db", null, 1);
    }

    private static BlackNumberDao blackNumberDao = null;
    public static BlackNumberDao getInstance(Context context){
        if(blackNumberDao == null){
            blackNumberDao = new BlackNumberDao(context);
        }
        return blackNumberDao;
    }

    public void insert(String phone,String mode){

        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("mode", mode);
        db.insert("blacknumber", null, values);

        db.close();
    }

    public void delete(String phone){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();

        db.delete("blacknumber", "phone = ?", new String[]{phone});

        db.close();
    }


    public void update(String phone,String mode){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("mode", mode);

        db.update("blacknumber", contentValues, "phone = ?", new String[]{phone});

        db.close();
    }


    public List<BlackNumberInfo> findAll(){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();

        Cursor cursor = db.query("blacknumber", new String[]{"phone","mode"}, null, null, null, null, "_id desc");
        List<BlackNumberInfo> blackNumberList = new ArrayList<>();
        while(cursor.moveToNext()){
            BlackNumberInfo blackNumberInfo = new BlackNumberInfo();
            blackNumberInfo.phone = cursor.getString(0);
            blackNumberInfo.mode = cursor.getString(1);
            blackNumberList.add(blackNumberInfo);
        }
        cursor.close();
        db.close();

        return blackNumberList;
    }


    public List<BlackNumberInfo> find(int index){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select phone,mode from blacknumber order by _id desc limit ?,20;", new String[]{index+""});

        List<BlackNumberInfo> blackNumberList = new ArrayList<>();
        while(cursor.moveToNext()){
            BlackNumberInfo blackNumberInfo = new BlackNumberInfo();
            blackNumberInfo.phone = cursor.getString(0);
            blackNumberInfo.mode = cursor.getString(1);
            blackNumberList.add(blackNumberInfo);
        }
        cursor.close();
        db.close();

        return blackNumberList;
    }


    public int getCount(){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("select count(*) from blacknumber;", null);
        if(cursor.moveToNext()){
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return count;
    }


    public int getMode(String phone){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();
        int mode = 0;
        Cursor cursor = db.query("blacknumber", new String[]{"mode"}, "phone = ?", new String[]{phone}, null, null,null);
        if(cursor.moveToNext()){
            mode = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return mode;
    }
}
