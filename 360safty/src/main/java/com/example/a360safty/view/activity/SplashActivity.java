package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a360safty.MainActivity;
import com.example.a360safty.R;
import com.example.a360safty.tools.DBCopyUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by snwfnh on 2016/10/17.
 */
public class SplashActivity  extends Activity implements View.OnClickListener {
    private static final String TAG = "SplashActivity";
    private boolean isFirst;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static final int SKIP_TIME = 1;
    private TextView mTextView;
    private int mCount=3;
    private Context mContext;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int result=msg.getData().getInt("time");
            switch (msg.what){
                case SKIP_TIME:
                    if(result==0){
                        gotoActivity(isFirst);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setView();
        initView();
        initDB();

    }

    private void initDB() {
      DBCopyUtil.copyDataBaseFromAssets(mContext,"address.db");


    }



    @Override
    protected void onResume() {
        super.onResume();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (mCount!=0){
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCount--;
                    Message msg=new Message();
                    msg.what = SKIP_TIME;
                    Bundle bundle = new Bundle();
                    bundle.putInt("time",mCount);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                }

            }
        }.start();
    }

    private void gotoActivity(boolean isFirst){
        if (isFirst){
            mEditor.putBoolean("isFirst",false);
            mEditor.commit();
            Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        isFirst=false;
        mSharedPreferences=getSharedPreferences("SplashActivity",0);
        mEditor=mSharedPreferences.edit();
        isFirst=mSharedPreferences.getBoolean("isFirst",true);
        mTextView= (TextView) findViewById(R.id.tv_skip);
        mTextView.setOnClickListener(this);

    }

    private void setView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_skip:
                mCount=0;
               gotoActivity(isFirst);
                break;
        }

    }
}
