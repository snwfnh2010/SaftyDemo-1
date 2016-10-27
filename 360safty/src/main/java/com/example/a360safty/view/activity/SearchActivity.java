package com.example.a360safty.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.a360safty.R;

/**
 * Created by snwfnh on 2016/10/23.
 */
public class SearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_search);
    }
}
