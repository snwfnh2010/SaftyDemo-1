package com.example.a360safty.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.*;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/23.
 */
public class MyPagerAdapter extends android.support.v4.view.PagerAdapter {
    private List<View> mList;

    public MyPagerAdapter(List<View> mList) {

        this.mList = mList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
