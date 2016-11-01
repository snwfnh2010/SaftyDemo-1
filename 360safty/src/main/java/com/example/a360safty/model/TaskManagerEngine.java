package com.example.a360safty.model;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/27.
 */
public class TaskManagerEngine {

    public static List<TaskBean> getAllRunningTaskInfos(Context context) {
        List<TaskBean> datas = new ArrayList<TaskBean>();

        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        PackageManager pm = context.getPackageManager();


        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am
                .getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            TaskBean bean = new TaskBean();

            String processName = runningAppProcessInfo.processName;

            bean.setPackName(processName);


            PackageInfo packageInfo = null;
            try {
                packageInfo = pm.getPackageInfo(processName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                continue;

            }

            bean.setIcon(packageInfo.applicationInfo.loadIcon(pm));

            bean.setName(packageInfo.applicationInfo.loadLabel(pm) + "");

            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){

                bean.setSystem(true);
            } else {
                bean.setSystem(false);
            }



            android.os.Debug.MemoryInfo[] processMemoryInfo = am.getProcessMemoryInfo(new int[]{runningAppProcessInfo.pid});

            long totalPrivateDirty = processMemoryInfo[0].getTotalPrivateDirty() * 1024;
            bean.setMemSize(totalPrivateDirty);

            datas.add(bean);
        }


        return datas;
    }

    /**
     * @return 获取可用内存的大小
     */
    public static long getAvailMemSize(Context context) {
        long size = 0;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        // MemoryInfo 存放内存的信息
        am.getMemoryInfo(outInfo);

        // 把kb 转换成byte
        size = outInfo.availMem;

        return size;
    }

    /**
     * @return 总内存大小
     */
    public static long getTotalMemSize(Context context) {
        long size = 0;

		/*
		 * //Activity管理器,获取运行相关信息 ActivityManager am = (ActivityManager)
		 * context.getSystemService(Context.ACTIVITY_SERVICE);
		 *
		 * MemoryInfo outInfo = new MemoryInfo(); //MemoryInfo 存放内存的信息
		 * am.getMemoryInfo(outInfo ); //获取总内存大小，16级别以上 ,读取内存文件 size =
		 * outInfo.totalMem;
		 */

        // 读取配置文件来获取总内存大小

        File file = new File("/proc/meminfo");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            // //zip流 pipe流

            String totalMemInfo = reader.readLine();

            int startIndex = totalMemInfo.indexOf(':');
            int endIndex = totalMemInfo.indexOf('k');
            // 单位是kb
            totalMemInfo = totalMemInfo.substring(startIndex + 1, endIndex)
                    .trim();
            size = Long.parseLong(totalMemInfo);
            size *= 1024;// byte单位
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return size;
    }
}
