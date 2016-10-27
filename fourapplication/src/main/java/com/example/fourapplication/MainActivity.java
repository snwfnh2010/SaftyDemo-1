package com.example.fourapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextView;
    private Button mButton;
    private List<Map<String, String>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        setView();

        initView();



    }

    private void initDate() {
        String path="http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
      new Thread(){
          @Override
          public void run() {
              super.run();
          }
      }.start();
        mList=new ArrayList<>();
        try {
            mList=Json.getJSON(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initView() {
        mTextView= (TextView) findViewById(R.id.tv_weather);
        mButton= (Button) findViewById(R.id.btn_wea);
        mButton.setOnClickListener(this);

    }

    private void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<mList.size();i++) {
            sb.append(mList.get(i));
        }

        mTextView.setText(sb.toString());
    }
}
