package com.example.a360safty.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.a360safty.R;
import com.example.a360safty.model.TaskInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class TaskUtils {
    /**
     * 获取当前正在进行的进程数
     * @param context
     * @return
     */
    public static int getRunningAppProcessInfoSize(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return am.getRunningAppProcesses().size();
    }

    /**
     * 获取系统可用内存
     * @param context
     * @return
     */
    public static long getAvailMem(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //得到可用内存
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(outInfo);
        long availMem = outInfo.availMem; //单位是byte
        return availMem;
    }

    /**
     * 获取系统所有的进程信息列表
     * @param context
     * @return
     */
    public static List<TaskInfo> getTaskInfos(Context context){
        List<TaskInfo> taskInfos  = new ArrayList<TaskInfo>();
        PackageManager pm = context.getPackageManager();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo info : runningAppProcesses){
            TaskInfo taskInfo = new TaskInfo();
            //进程名称
            String packageName = info.processName;
            taskInfo.setPackageName(packageName);
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
                //图标
                Drawable task_icon = applicationInfo.loadIcon(pm);
                if(task_icon == null){
                    taskInfo.setTask_icon(context.getResources().getDrawable(R.drawable.ic_launcher));
                }else{
                    taskInfo.setTask_icon(task_icon);
                }
                //名称
                String task_name = applicationInfo.loadLabel(pm).toString();
                taskInfo.setTask_name(task_name);
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                taskInfo.setTask_icon(context.getResources().getDrawable(R.drawable.ic_launcher));
                taskInfo.setTask_name(packageName);
            }

            //进程id
            int pid = info.pid;
            taskInfo.setPid(pid);
            //获取进程占用的内存
            android.os.Debug.MemoryInfo[] processMemoryInfo = am.getProcessMemoryInfo(new int[]{pid});
            android.os.Debug.MemoryInfo memoryInfo  = processMemoryInfo[0];
            long totalPrivateDirty = memoryInfo.getTotalPrivateDirty(); //KB
            taskInfo.setTask_memory(totalPrivateDirty);
            taskInfos.add(taskInfo);
        }
        return taskInfos;
    }
}
