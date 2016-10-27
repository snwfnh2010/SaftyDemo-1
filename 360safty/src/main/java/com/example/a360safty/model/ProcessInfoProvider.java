package com.example.a360safty.model;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.a360safty.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/26.
 */
public class ProcessInfoProvider {

    public static int getProcessCount(Context context){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = manager.getRunningAppProcesses();
        return runningAppProcesses.size();
    }

    public static long getAvailSpace(Context ctx){
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long getTotalSpace(Context ctx){
        FileReader fileReader  = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader= new FileReader("proc/meminfo");
            bufferedReader = new BufferedReader(fileReader);
            String lineOne = bufferedReader.readLine();
            char[] charArray = lineOne.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            for (char c : charArray) {
                if(c>='0' && c<='9'){
                    stringBuffer.append(c);
                }
            }
            return Long.parseLong(stringBuffer.toString())*1024;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(fileReader!=null && bufferedReader!=null){
                    fileReader.close();
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static List<ProcessInfo> getProcessInfo(Context context){
        List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = context.getPackageManager();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.packageName = info.processName;
            android.os.Debug.MemoryInfo[] processMemoryInfo = manager.getProcessMemoryInfo(new int[]{info.pid});
            android.os.Debug.MemoryInfo memoryInfo = processMemoryInfo[0];
            processInfo.memSize = memoryInfo.getTotalPrivateDirty()*1024;
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo(processInfo.packageName, 0);
                processInfo.name = applicationInfo.loadLabel(pm).toString();
                processInfo.drawable = applicationInfo.loadIcon(pm);
                if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM){
                    processInfo.isSystem = true;
                }else{
                    processInfo.isSystem = false;
                }
            } catch (PackageManager.NameNotFoundException e) {
                processInfo.name = info.processName;
                processInfo.drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
                processInfo.isSystem = true;
                e.printStackTrace();
            }
            processInfoList.add(processInfo);
        }
        return processInfoList;
    }

    public static void killProcess(Context context,ProcessInfo processInfo) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(processInfo.packageName);
    }

    public static void killAll(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
            if(info.processName.equals(context.getPackageName())){
                continue;
            }
            am.killBackgroundProcesses(info.processName);
        }
    }
}
