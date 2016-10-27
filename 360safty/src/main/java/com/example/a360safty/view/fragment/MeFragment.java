package com.example.a360safty.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a360safty.R;
import com.example.a360safty.service.WatchDogService;
import com.example.a360safty.tools.ServiceUtil;
import com.example.a360safty.view.widget.SettingItemView;

/**
 * Created by snwfnh on 2016/10/17.
 */
public class MeFragment extends Fragment {
    private SettingItemView siv_update;
    private SettingItemView siv_address;
    private String[] mStrs;
    private Context mContext;

    private SettingItemView siv_black_number;
    private SettingItemView siv_app_lock;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mContext=this.getActivity();
        siv_app_lock = (SettingItemView) view.findViewById(R.id.siv_app_lock);

        final boolean isRunning = ServiceUtil.isRunning("com.example.a360safty.service.WatchDogService", mContext);

        siv_app_lock.setCheck(isRunning);


//        siv_app_lock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean check = siv_app_lock.isCheck();
//                siv_app_lock.setCheck(!check);
//                //5,服务的开启关闭
//                if(!check){
//                    //开启
//
//                    startService(new Intent(mContext,WatchDogService.class));
//                }else{
//                    //关闭
//                    stopService(new Intent(mContext,WatchDogService.class));
//                }
//            }
//        });
    }
}
