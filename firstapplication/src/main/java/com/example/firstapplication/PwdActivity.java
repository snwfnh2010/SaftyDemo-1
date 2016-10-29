package com.example.firstapplication;

import android.app.Activity;
import android.os.Bundle;

import com.panes.shapelocker.property.ShapeLockerProperties;
import com.panes.shapelocker.view.ShapeLocker;

/**
 * Created by snwfnh on 2016/10/29.
 */
public class PwdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        ShapeLockerProperties.with(this).loadLightTheme();
        // or:
        ShapeLocker sl = (ShapeLocker) findViewById(R.id.sl);
        sl.loadLightTheme();

    }
}
