package com.example.a360safty.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

import com.example.a360safty.model.AppLockDao;
import com.example.a360safty.view.activity.EnterPsdActivity;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/25.
 */
public class WatchDogService extends Service {
    protected static final String tag = "WatchDogService";
    private AppLockDao mDao;
    private boolean isWatch;
    private InnerReceiver mInnerReceiver;
    private String skipPackageName;
    private List<String> packageList;
    private MyContentObserver mContentObserver;

    @Override
    public void onCreate() {

        mDao = AppLockDao.getInstance(getApplicationContext());

        isWatch = true;
        watch();


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.a360safty.SKIP_WATCH");

        mInnerReceiver = new InnerReceiver();
        registerReceiver(mInnerReceiver, intentFilter);



        mContentObserver = new MyContentObserver(new Handler());
        getContentResolver().registerContentObserver(
                Uri.parse("content://com.example.a360safty/applock/change"), true, mContentObserver);

        super.onCreate();
    }

    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }
        @Override
        public void onChange(boolean selfChange) {

            packageList = mDao.findAll();
            super.onChange(selfChange);
        }
    }

    class InnerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            skipPackageName = intent.getStringExtra("packageName");
        }
    }
    private void watch() {

        packageList = mDao.findAll();
        new Thread(){
            public void run() {
                while(isWatch){

                    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(1);
                    ComponentName componentName = runningTasks.get(0).topActivity;
                    String packageName = componentName.getPackageName();
                    if(packageList.contains(packageName)){
                        if(!packageName.equals(skipPackageName)){

                            Intent intent = new Intent(getApplicationContext(),EnterPsdActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("packageName",packageName);
                            startActivity(intent);
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onDestroy() {

        isWatch = false;
        if(mInnerReceiver!=null){
            unregisterReceiver(mInnerReceiver);
        }
        if(mContentObserver!=null){
            getContentResolver().unregisterContentObserver(mContentObserver);
        }
        super.onDestroy();
    };
}
