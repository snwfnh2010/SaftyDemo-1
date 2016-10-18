package com.example.a360safty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a360safty.view.adapter.PagerAdapter;
import com.example.a360safty.view.fragment.BodyGuardFragment;
import com.example.a360safty.view.fragment.MeFragment;
import com.example.a360safty.view.fragment.SecurityFragment;
import com.example.a360safty.view.fragment.ToolKitFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private LinearLayout ll_guard,ll_tool,ll_service,ll_me;

    private TextView mText_guard,mText_tool,mText_service,mText_me;

    private TextView mTextView_guard,mTextView_tool,mTextView_service,mTextView_me;
    
    private List<Fragment> mFragmentList;

    private PagerAdapter mPagerAdapter;

    private int currentPager=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        BodyGuardFragment bodyguardFragment=new BodyGuardFragment();
        ToolKitFragment  toolKitFragment=new ToolKitFragment();
        SecurityFragment securityFragment=new SecurityFragment();
        MeFragment  meFragment=new MeFragment();

        mFragmentList=new ArrayList<>();
        mFragmentList.add(bodyguardFragment);
        mFragmentList.add(toolKitFragment);
        mFragmentList.add(securityFragment);
        mFragmentList.add(meFragment);

       mPagerAdapter=new PagerAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                reSetImg();
                switch (position){
                    case 0:
                        mText_guard.setBackgroundResource(R.mipmap.jh);
                        mTextView_guard.setTextColor(Color.rgb(11,187,9));
                        break;
                    case 1:
                        mText_tool.setBackgroundResource(R.mipmap.ph);
                        mTextView_tool.setTextColor(Color.rgb(11,187,9));
                        break;
                    case 2:
                        mText_service.setBackgroundResource(R.mipmap.mh);
                        mTextView_service.setTextColor(Color.rgb(11,187,9));
                        break;
                    case 3:
                        mText_me.setBackgroundResource(R.mipmap.gh);
                        mTextView_me.setTextColor(Color.rgb(11,187,9));
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_content);


        ll_guard= (LinearLayout) findViewById(R.id.tab_guard);
        ll_tool= (LinearLayout) findViewById(R.id.tab_tool);
        ll_service= (LinearLayout) findViewById(R.id.tab_service);
        ll_me= (LinearLayout) findViewById(R.id.tab_me);

        ll_guard.setOnClickListener(this);
        ll_tool.setOnClickListener(this);
        ll_service.setOnClickListener(this);
        ll_me.setOnClickListener(this);

        mText_guard= (TextView) findViewById(R.id.tab_guard_tv);
        mText_tool= (TextView) findViewById(R.id.tab_tool_tv);
        mText_service= (TextView) findViewById(R.id.tab_service_tv);
        mText_me= (TextView) findViewById(R.id.tab_me_tv);

        mTextView_guard= (TextView) findViewById(R.id.guard_tv);
        mTextView_tool= (TextView) findViewById(R.id.tool_tv);
        mTextView_service= (TextView) findViewById(R.id.service_tv);
        mTextView_me= (TextView) findViewById(R.id.me_tv);





    }

    @Override
    public void onClick(View v) {
        reSetImg();
        switch (v.getId()){
            case R.id.tab_guard:
                mViewPager.setCurrentItem(0,false);
                mText_guard.setBackgroundResource(R.mipmap.jh);
                mTextView_guard.setTextColor(Color.rgb(11,187,9));
                break;
            case R.id.tab_tool:
                mViewPager.setCurrentItem(1,false);
                mText_tool.setBackgroundResource(R.mipmap.ph);
                mTextView_tool.setTextColor(Color.rgb(11,187,9));
                break;
            case R.id.tab_service:
                mViewPager.setCurrentItem(2,false);
                mText_service.setBackgroundResource(R.mipmap.mh);
                mTextView_service.setTextColor(Color.rgb(11,187,9));
                break;
            case R.id.tab_me:
                mViewPager.setCurrentItem(3,false);
                mText_me.setBackgroundResource(R.mipmap.gh);
                mTextView_me.setTextColor(Color.rgb(11,187,9));
                break;
            default:
                break;
        }
    }

    private void reSetImg() {
        mText_guard.setBackgroundResource(R.mipmap.ih);

        mTextView_guard.setTextColor(Color.argb(255,167,167,167));

        mText_tool.setBackgroundResource(R.mipmap.oh);
        mTextView_tool.setTextColor(Color.argb(255,167,167,167));

        mText_service.setBackgroundResource(R.mipmap.lh);
        mTextView_service.setTextColor(Color.argb(255,167,167,167));

        mText_me.setBackgroundResource(R.mipmap.fh);
        mTextView_me.setTextColor(Color.argb(255,167,167,167));
    }




}
