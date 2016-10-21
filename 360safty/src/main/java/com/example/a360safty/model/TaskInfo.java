package com.example.a360safty.model;

import android.graphics.drawable.Drawable;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class TaskInfo {
    //图标
    private Drawable task_icon;
    //名称
    private String task_name;
    //占用的内存
    private long task_memory;
    //包名
    private String packageName;
    //进程id
    private int pid;

    public TaskInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TaskInfo(Drawable task_icon, String task_name, long task_memory,
                    String packageName, int pid) {
        super();
        this.task_icon = task_icon;
        this.task_name = task_name;
        this.task_memory = task_memory;
        this.packageName = packageName;
        this.pid = pid;
    }
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Drawable getTask_icon() {
        return task_icon;
    }
    public void setTask_icon(Drawable task_icon) {
        this.task_icon = task_icon;
    }
    public String getTask_name() {
        return task_name;
    }
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
    public long getTask_memory() {
        return task_memory;
    }
    public void setTask_memory(long task_memory) {
        this.task_memory = task_memory;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "TaskInfo [task_icon=" + task_icon + ", task_name=" + task_name
                + ", task_memory=" + task_memory + ", packageName="
                + packageName + ", pid=" + pid + "]";
    }

}
