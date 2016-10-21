package com.example.a360safty.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class Md5Utils {

    public static String encoder(String psd){
        psd=psd+"360safetyMd5Utils";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest();
            StringBuffer stringBuffer=new StringBuffer();
            for (byte b:bytes){
                int i=b&0xff;
                String hexString = Integer.toHexString(i);
                if(hexString.length()<2){
                    hexString="0"+hexString;
                }
                stringBuffer.append(hexString);
            }
           return stringBuffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } return "";
    }
}
