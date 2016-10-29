package com.example.a360safty.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.a360safty.R;
import com.example.a360safty.view.activity.ChildSaftyActivity;
import com.example.a360safty.view.activity.PhoneAddressActivity;
import com.example.a360safty.view.activity.SearchActivity;
import com.example.a360safty.view.adapter.GridAdapter;

/**
 * Created by snwfnh on 2016/10/17.
 */
public class SecurityFragment extends Fragment implements GridView.OnItemClickListener,View.OnClickListener {
    private GridView mGridView;
    private Context mContext;
    private int[] icon;
    private String[] iconName;
    private GridAdapter mGridAdapter;
    private RelativeLayout mRelativeLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_security,container,false);
        intiView(view);
        initData();
        return view;
    }

    private void  intiView(View view) {
        mRelativeLayout= (RelativeLayout) view.findViewById(R.id.rl_search);
        mGridView= (GridView) view.findViewById(R.id.grid_service);

        mGridView.setOnItemClickListener(this);
        mRelativeLayout.setOnClickListener(this);
    }

    private void initData() {
        mContext=this.getActivity();
        icon = new int[]{R.mipmap.tof_p1,R.mipmap.wo,R.mipmap.tof_p3};
        iconName = new String[]{"归属地查询", "儿童安全", "常用号码查询"};
        mGridAdapter=new GridAdapter(mContext,icon,iconName);
        mGridView.setAdapter(mGridAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_search:
                Intent intent=new Intent(mContext,SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=null;
        switch (position){
            case 0:
                intent=new Intent(mContext,PhoneAddressActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent=new Intent(mContext,ChildSaftyActivity.class);
                startActivity(intent);
                break;
            case 2:
                break;
            default:
                break;
        }

    }
}
