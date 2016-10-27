package com.example.a360safty.model;

import android.graphics.drawable.Drawable;

/**
 * Created by snwfnh on 2016/10/26.
 */
public class ProcessInfo {

    public String name;
    public Drawable drawable;
    public long memSize;
    public boolean isSystem;
    public boolean isCheck;
    public String packageName;


    public ProcessInfo() {
        super();
    }

    public ProcessInfo(String name, Drawable drawable, long memSize, boolean isSystem, boolean isCheck, String packageName) {
        this.name = name;
        this.drawable = drawable;
        this.memSize = memSize;
        this.isSystem = isSystem;
        this.isCheck = isCheck;
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public long getMemSize() {
        return memSize;
    }

    public void setMemSize(long memSize) {
        this.memSize = memSize;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "ProcessInfo{" +
                "name='" + name + '\'' +
                ", drawable=" + drawable +
                ", memSize=" + memSize +
                ", isSystem=" + isSystem +
                ", isCheck=" + isCheck +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
