package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class DemoActivity extends FragmentActivity {
    private TextView[] mTextViews;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;
    private Fragment[] mFragments;
    private String[] mStrings={"TAB1","TAB2","TAB3"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        init();
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private void init() {

        mLinearLayout= (LinearLayout) findViewById(R.id.llayout);
        int count = mLinearLayout.getChildCount();
        mTextViews=new TextView[count];
        mFragments=new Fragment[count];

        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                selectedItem(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mFragments[0]=new Fragment1();
        mFragments[1]=new Fragment2();
        mFragments[2]=new Fragment3();
        for (int i=0;i<count;i++){
            mTextViews[i]= (TextView) mLinearLayout.getChildAt(i);
            mTextViews[i].setText(mStrings[i]);
            mTextViews[i].setEnabled(true);
            mTextViews[i].setTag(i);
            mTextViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item= (Integer) v.getTag();
                      mViewPager.setCurrentItem(item);
                    selectedItem(item);
                }
            });
        }
        mTextViews[0].setEnabled(false);
        mTextViews[0].setBackgroundColor(Color.RED);
    }

    private void selectedItem(int item) {
        for (int i=0;i<mTextViews.length;i++){
            mTextViews[i].setBackgroundColor(Color.GRAY);
            mTextViews[i].setEnabled(true);
        }
        mTextViews[item].setBackgroundColor(Color.RED);
        mTextViews[item].setEnabled(false);
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }
}
