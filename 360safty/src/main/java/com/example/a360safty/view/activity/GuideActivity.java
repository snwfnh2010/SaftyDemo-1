package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a360safty.MainActivity;
import com.example.a360safty.R;
import com.example.a360safty.view.adapter.DepthPageTransformer;
import com.example.a360safty.view.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/23.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private List<View> mList;
    private int[] points;
    private ImageView[] indicationPoint;
    private MyPagerAdapter mMyPagerAdapter;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inti();
        setView();
        intiDate();
        initView();

    }

    private void initView() {
        mMyPagerAdapter=new MyPagerAdapter(mList);
        mViewPager= (ViewPager) this.findViewById(R.id.vp_guide);
        mViewPager.setAdapter(mMyPagerAdapter);
        mViewPager.setPageTransformer(true,new DepthPageTransformer() );
        mViewPager.setOffscreenPageLimit(mList.size());// 加载缓存的页面个数
        mViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) this);

        mButton = (Button) mList.get(mList.size()-1).findViewById(R.id.btn_ok);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void intiDate() {
        LayoutInflater inflater=LayoutInflater.from(this);
        mList.add(inflater.inflate(R.layout.start1_layout,null));
        mList.add(inflater.inflate(R.layout.start2_layout,null));
        mList.add(inflater.inflate(R.layout.start3_layout,null));
        mList.add(inflater.inflate(R.layout.start4_layout,null));
        indicationPoint=new ImageView[mList.size()];
        for(int i=0;i<mList.size();i++){
            indicationPoint[i]= (ImageView) findViewById(points[i]);
        }
    }

    private void inti() {
        mList=new ArrayList<>();
        points=new int[]{R.id.point1,R.id.point2,R.id.point3,R.id.point4};

    }

    private void setView() {
        setContentView(R.layout.activity_guide);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i=0;i<points.length;i++){
            if(position==i){
                indicationPoint[i].setImageResource(R.mipmap.page_indicator_focused_1);
            }else {
                indicationPoint[i].setImageResource(R.mipmap.page_indicator_unfocused_1);

            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
