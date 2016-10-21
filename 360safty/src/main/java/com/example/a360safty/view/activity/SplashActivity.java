package com.example.a360safty.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a360safty.MainActivity;
import com.example.a360safty.R;

/**
 * Created by snwfnh on 2016/10/17.
 */
public class SplashActivity  extends AppCompatActivity implements View.OnClickListener {
    private static final int SKIP_TIME = 1;
    private TextView mTextView;
    private Button mButton;
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
                        startActivity(new Intent(mContext, MainActivity.class));
                       SplashActivity.this.finish();
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

    private void initView() {
        mTextView= (TextView) findViewById(R.id.tv_skip);
        mButton= (Button) findViewById(R.id.btn_splash);

        mTextView.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }

    private void setView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_splash:


                break;
            case R.id.tv_skip:
                mCount=0;
                startActivity(new Intent(mContext, MainActivity.class));
                SplashActivity.this.finish();
                break;
        }

    }
}
