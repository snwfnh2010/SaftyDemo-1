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
import com.example.a360safty.model.TextRecord;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class TextFragment extends Fragment {
    private ListView mListView;
    private Context mContext;
    private List<TextRecord> mTextRecordList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_text,container,false);
        return view;
    }
}
