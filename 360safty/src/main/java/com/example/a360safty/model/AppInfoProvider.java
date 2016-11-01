package com.example.a360safty.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/22.
 */
public class AppInfoProvider {
    public static List<AppInfo> getAppInfoList(Context ctx){

        PackageManager pm = ctx.getPackageManager();

        List<PackageInfo> packageInfoList = pm.getInstalledPackages(0);
        List<AppInfo> appInfoList = new ArrayList<>();

        for (PackageInfo packageInfo : packageInfoList) {
            AppInfo appInfo = new AppInfo();

            appInfo.packageName = packageInfo.packageName;

            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            appInfo.name = applicationInfo.loadLabel(pm).toString();

            appInfo.icon = applicationInfo.loadIcon(pm);

            if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==ApplicationInfo.FLAG_SYSTEM){

                appInfo.isSystem = true;
            }else{

                appInfo.isSystem = false;
            }

            if((applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE)==ApplicationInfo.FLAG_EXTERNAL_STORAGE){

                appInfo.isSdCard = true;
            }else{

                appInfo.isSdCard = false;
            }
            appInfoList.add(appInfo);
        }
        return appInfoList;
    }
}
