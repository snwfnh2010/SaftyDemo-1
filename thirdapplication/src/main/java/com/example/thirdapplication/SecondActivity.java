package com.example.thirdapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by snwfnh on 2016/10/27.
 */
public class SecondActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextView= (TextView) findViewById(R.id.tv_out);
        Intent intent=new Intent();
        String content=intent.getStringExtra("content");
        mTextView.setText(content);

    }
}
