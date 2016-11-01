package com.example.a360safty.model;

import android.graphics.drawable.Drawable;

/**
 * Created by snwfnh on 2016/10/27.
 */
public class TaskBean {
    private boolean isChecked;
    private Drawable icon;
    private String name;
    private String packName;
    private long memSize;
    private boolean isSystem;
    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPackName() {
        return packName;
    }
    public void setPackName(String packName) {
        this.packName = packName;
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
    public void setSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
