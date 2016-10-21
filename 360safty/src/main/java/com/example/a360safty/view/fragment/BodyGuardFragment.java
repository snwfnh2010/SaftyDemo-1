package com.example.a360safty.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.a360safty.R;
import com.example.a360safty.view.activity.CleanupActivity;
import com.example.a360safty.view.activity.InterceptActivity;
import com.example.a360safty.view.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snwfnh on 2016/10/17.
 */
public class BodyGuardFragment extends Fragment implements View.OnClickListener,GridView.OnItemClickListener {
    private ImageView mImageView;
    private TextView mTextView_score, mTextView_optimize;
    private GridView mGridView;
    private int[] icon;
    private String[] iconName;
    private Context mContext;
    private GridAdapter mGridAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guard, container, false);
        initView(view);
        initData();


        return view;
    }

    private void initData() {
        mContext = this.getActivity();

        icon = new int[]{R.mipmap.ug, R.mipmap.tg, R.mipmap.qg, R.mipmap.yg,};
        iconName = new String[]{"清理加速", "骚扰拦截", "有更新啦", "手机杀毒"};
        mGridAdapter=new GridAdapter(mContext,icon,iconName);
        mGridView.setAdapter(mGridAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                mImageView.setBackgroundResource(R.drawable.flame_animation);
                AnimationDrawable drawable = (AnimationDrawable) mImageView.getBackground();
                drawable.start();

                mTextView_score.setText(83 + "");
                break;
        }

    }

    private void initView(View view) {
        mImageView = (ImageView) view.findViewById(R.id.iv_animation);
        mTextView_score = (TextView) view.findViewById(R.id.tv_score);
        mTextView_optimize = (TextView) view.findViewById(R.id.textView);
        mGridView = (GridView) view.findViewById(R.id.grid_view);

        mTextView_optimize.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=null;
        switch (position){
            case 0:
                intent=new Intent(mContext,CleanupActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent=new Intent(mContext,InterceptActivity.class);
                startActivity(intent);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;

        }

    }
}
