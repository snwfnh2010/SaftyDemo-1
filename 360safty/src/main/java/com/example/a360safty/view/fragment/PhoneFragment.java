package com.example.a360safty.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a360safty.R;
import com.example.a360safty.model.PhoneRecord;
import com.example.a360safty.view.adapter.PhoneAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class PhoneFragment extends Fragment {
    private ListView mListView;
    private List<PhoneRecord> mPhoneRecordList;
    private Context mContext;
    private PhoneAdapter mPhoneAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_phone,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mContext=this.getActivity();
        mPhoneRecordList=new ArrayList<>();

        mPhoneAdapter=new PhoneAdapter(mPhoneRecordList,mContext);

    }

    private void initView(View view) {
        mListView= (ListView) view.findViewById(R.id.list_phone);
    }
}
