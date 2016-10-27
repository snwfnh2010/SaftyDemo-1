package com.example.a360safty.view.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by snwfnh on 2016/10/23.
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth=page.getWidth();
        if(position<-1){
            page.setAlpha(0);
        }else if(position<=0){
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        }else if(position<=1){
            page.setAlpha(1-position);
            page.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }else {
            page.setAlpha(0);
        }

    }
}
