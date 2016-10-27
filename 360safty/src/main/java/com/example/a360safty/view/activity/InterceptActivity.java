package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a360safty.MainActivity;
import com.example.a360safty.R;
import com.example.a360safty.view.fragment.BodyGuardFragment;
import com.example.a360safty.view.fragment.PhoneFragment;
import com.example.a360safty.view.fragment.TextFragment;

/**
 * Created by snwfnh on 2016/10/20.
 */
public class InterceptActivity extends FragmentActivity {
    private TextView[] mTextViews;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;
    private Fragment[] mFragments;
    private TextView mTextView_title;
    private String[] mStrings={"短信拦截","来电记录",};
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();
        init();
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private void initView() {
        mLinearLayout= (LinearLayout) findViewById(R.id.llayout);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTextView_title= (TextView) findViewById(R.id.tv_intercept_title);
    }

    private void setView() {
        setContentView(R.layout.activity_intercept);
    }

    private void init() {
        mContext=this;

        int count = mLinearLayout.getChildCount();
        mTextViews=new TextView[count];
        mFragments=new Fragment[count];


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
        mFragments[0]=new TextFragment();
        mFragments[1]=new PhoneFragment();

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
        mTextViews[0].setTextColor(Color.WHITE);

        mTextView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
                finish();

            }
        });
    }

    private void selectedItem(int item) {
        for (int i=0;i<mTextViews.length;i++){

            mTextViews[i].setTextColor(Color.argb(255,167,167,167));
            mTextViews[i].setEnabled(true);
        }

        mTextViews[item].setTextColor(Color.WHITE);
        mTextViews[item].setEnabled(false);
    }

    class MyAdapter extends FragmentPagerAdapter {

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
