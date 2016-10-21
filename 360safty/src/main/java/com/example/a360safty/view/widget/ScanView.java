package com.example.a360safty.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by snwfnh on 2016/10/18.
 */
public class ScanView extends SurfaceView implements SurfaceHolder.Callback {
    public ScanView(Context context) {
        super(context);
    }

    public ScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
