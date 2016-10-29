package com.example.a360safty.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by snwfnh on 2016/10/27.
 */
public class SmsBackUp {
    private static int index = 0;

    public static void backup(Context context, String path, CallBack callBack) {
        FileOutputStream fos = null;
        Cursor cursor = null;
        try {
            File file = new File(path);
            cursor = context.getContentResolver().query(Uri.parse("content://sms/"),
                    new String[]{"address","date","type","body"}, null, null, null);
            fos = new FileOutputStream(file);
            XmlSerializer newSerializer = Xml.newSerializer();
            newSerializer.setOutput(fos, "utf-8");
            newSerializer.startDocument("utf-8", true);
            newSerializer.startTag(null, "smss");
            if(callBack!=null){
                callBack.setMax(cursor.getCount());
            }
            while(cursor.moveToNext()){
                newSerializer.startTag(null, "sms");

                newSerializer.startTag(null, "address");
                newSerializer.text(cursor.getString(0));
                newSerializer.endTag(null, "address");

                newSerializer.startTag(null, "date");
                newSerializer.text(cursor.getString(1));
                newSerializer.endTag(null, "date");

                newSerializer.startTag(null, "type");
                newSerializer.text(cursor.getString(2));
                newSerializer.endTag(null, "type");

                newSerializer.startTag(null, "body");
                newSerializer.text(cursor.getString(3));
                newSerializer.endTag(null, "body");

                newSerializer.endTag(null, "sms");
                index++;
                Thread.sleep(500);
                 if(callBack!=null){
                    callBack.setProgress(index);
                }
            }
            newSerializer.endTag(null, "smss");
            newSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cursor!=null && fos!=null){
                    cursor.close();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public interface CallBack{

        public void setMax(int max);
        public void setProgress(int index);
    }
}
