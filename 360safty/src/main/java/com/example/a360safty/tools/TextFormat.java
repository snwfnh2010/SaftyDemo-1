package com.example.a360safty.tools;

import java.text.DecimalFormat;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class TextFormat {
    public static String formatByte(long data){
        DecimalFormat format = new DecimalFormat("##.##");
        if(data < 1024){
            return data+"bytes";
        }else if(data < 1024 * 1024){
            return format.format(data/1024f) +"KB";
        }else if(data < 1024 * 1024 * 1024){
            return format.format(data/1024f/1024f) +"MB";
        }else if(data < 1024 * 1024 * 1024 * 1024){
            return format.format(data/1024f/1024f/1024f) +"GB";
        }else{
            return "超出统计范围";
        }
    }
}
