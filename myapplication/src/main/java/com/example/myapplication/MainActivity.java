package com.example.myapplication;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {
    private FragmentA mFragmentA;
    private FragmentB mFragmentB;
    private FragmentManager mManager;
    private SetSelectedCityListener mListener=new SetSelectedCityListener() {
        @Override
        public void selectedCity(String city) {
            mFragmentB.setText(city);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager=getSupportFragmentManager();
        mFragmentA= (FragmentA) mManager.findFragmentById(R.id.fragmentA);
        mFragmentA.setListener(mListener);
        mFragmentB= (FragmentB) mManager.findFragmentById(R.id.fragmentB);

    }
}
