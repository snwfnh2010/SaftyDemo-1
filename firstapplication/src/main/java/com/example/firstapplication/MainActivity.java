package com.example.firstapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private Context mContext;
    private List<MessageInfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        init();
        intiView();
        mListView.setAdapter(new MyAdapter(mList,mContext));


    }

    private void init() {
        mList=new ArrayList<>();
        mContext=this;
        mList.add(new MessageInfo("China Mobile","10086",""));
        mList.add(new MessageInfo("China Mobile","10086",""));
        mList.add(new MessageInfo("China Mobile","10086",""));
        mList.add(new MessageInfo("China Mobile","10086",""));
        mList.add(new MessageInfo("China Mobile","10086",""));
        mList.add(new MessageInfo("China Mobile","10086",""));

    }

    private void intiView() {
        mListView= (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);

    }

    private void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext, mList.get(position).getName()+","+ mList.get(position).getNum(), Toast.LENGTH_SHORT).show();
    }
}
