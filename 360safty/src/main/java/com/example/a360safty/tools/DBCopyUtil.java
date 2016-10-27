package com.example.a360safty.tools;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class DBCopyUtil {
    public static void copyDataBaseFromAssets(Context context, String DBName) {

        InputStream in = null;
        FileOutputStream out = null;
        File file = context.getApplicationContext().getDatabasePath(DBName);   //data/data

        if (!file.exists()) {
            //判断database目录是否为空
            File parent = new File(file.getParent());
            if (!parent.exists()) parent.mkdirs();

            try {
                in = context.getAssets().open(DBName); // 从assets目录下复制
                out = new FileOutputStream(file.getPath());
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
