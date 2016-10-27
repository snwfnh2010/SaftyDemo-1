package com.example.fourapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snwfnh on 2016/10/22.
 */
public class Json {
    public static List<Map<String, String>> getJSON(String path) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();
            byte[] data = readStream(is);
            String json = new String(data);


            JSONObject jsonObject=new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");


            for (int i = 0; i < result.length(); i++) {
                JSONObject item = result.getJSONObject(i);
                int weaid=item.getInt("weaid");
                String days=item.getString("days");
                String week=item.getString("week");
                String cityno=item.getString("cityno");
                String citynm=item.getString("citynm");
                String temperature=item.getString("temperature");
                String weather=item.getString("weather");
                map=new HashMap<>();
                map.put("citynm", citynm + "");
                map.put("temperature", temperature);
                list.add(map);
            }
        }



        for (Map<String, String> list2 : list) {
            String id = list2.get("citynm");
            String name = list2.get("temperature");

        }

        return list;
    }

    public static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();

        return bout.toByteArray();
    }
}
