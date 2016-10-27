package com.example.a360safty.tools;

import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.content.Context;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/25.
 */
public class ServiceUtil {
    public static boolean isRunning(String serviceName,Context ctx) {

        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(100);

        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {

            String className = runningServiceInfo.service.getClassName();

            if(serviceName.equals(className)){

                return true;
            }
        }

        return false;
    }
}
