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
    private Context ctx;
    //1,私有化构造方法
    private AppLockDao(Context ctx){
        this.ctx = ctx;
        applockOpenHelper = new AppLockOpenHelper(ctx,"applock.db",null,1);

    };
    //2,创建一个对象
    private static AppLockDao mAppLockDao = null;
    private AppLockOpenHelper applockOpenHelper;

    //3,对外提供一个返回创建对象的方法
    public static AppLockDao getInstance(Context ctx){
        if(mAppLockDao == null){
            mAppLockDao = new AppLockDao(ctx);
        }
        return mAppLockDao;
    }

    //加入到已加锁表中方法
    public void insert(String packageName){
        SQLiteDatabase db = applockOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("packagename", packageName);

        db.insert("applock", null, contentValues);

        db.close();

        //通过内容观察者告知数据发生改变,内容解析者
        ctx.getContentResolver().notifyChange(Uri.parse("content://com.example.a360safty/applock/change"), null);
    }

    public void delete(String packageName){
        SQLiteDatabase db = applockOpenHelper.getWritableDatabase();

        db.delete("applock", "packagename = ?", new String[]{packageName});

        db.close();
        //通过内容观察者告知数据发生改变,内容解析者
        ctx.getContentResolver().notifyChange(Uri.parse("content://com.example.a360safty/applock/change"), null);
    }

    /**
     * @return	查询所有已加锁应用包名
     */
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
