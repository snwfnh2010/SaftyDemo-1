package com.example.a360safty.model;

import android.graphics.drawable.Drawable;

/**
 * Created by snwfnh on 2016/10/26.
 */
public class ProcessInfo {

    public String name;
    public Drawable icon;
    public long memSize;
    public boolean isCheck;
    public boolean isSystem;
    public String packageName;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    public long getMemSize() {
        return memSize;
    }
    public void setMemSize(long memSize) {
        this.memSize = memSize;
    }
    public boolean isCheck() {
        return isCheck;
    }
    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
    public boolean isSystem() {
        return isSystem;
    }
    public void setSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
