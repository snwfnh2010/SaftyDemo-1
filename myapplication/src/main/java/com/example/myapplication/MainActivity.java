package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_main);
    }

    private void initView() {
        fragment1=new FirstFragment();
        fragment2=new SecondFragment();

    }
}
