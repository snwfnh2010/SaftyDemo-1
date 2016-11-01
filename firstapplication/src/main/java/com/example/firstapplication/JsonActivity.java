package com.example.firstapplication;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by snwfnh on 2016/10/29.
 */
public class JsonActivity extends AppCompatActivity {
    private TextView tv_json;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initView();

    }

    private String getContent(Resources resources, int id) {
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();

        try {
            inputStream = resources.openRawResource(id);
            byte[] bys = new byte[1024];
            int len = 0;
            len = inputStream.read(bys, 0, 1024);
            while (len != -1) {
                String s = new String(bys, 0, len);
                sb.append(s);
                len = inputStream.read(bys, 0, 1024);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return sb.toString();
    }

    private void initView()  {
        StringBuffer sb=new StringBuffer();
        tv_json = (TextView) findViewById(R.id.tv_json);
        String data=getContent(getResources(),R.raw.weather);
        JSONObject weather= null;
        try {
            weather = new JSONObject(data);
            JSONObject retData = weather.getJSONObject("retData");
            String city=retData.getString("city");
            String pinyin=retData.getString("pinyin");
            String citycode=retData.getString("citycode");
            String date=retData.getString("date");
            String time=retData.getString("time");
            String WS=retData.getString("WS");
            sb.append(city+"\n"+pinyin+"\n"+citycode+"\n"+date+"\n"+time+"\n"+WS);
        } catch (JSONException e) {
            e.printStackTrace();
        }




        tv_json.setText(sb.toString());
    }
}
