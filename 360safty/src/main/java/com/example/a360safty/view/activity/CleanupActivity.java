package com.example.a360safty.view.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a360safty.R;
import com.example.a360safty.model.TaskInfo;
import com.example.a360safty.tools.TaskUtils;
import com.example.a360safty.tools.TextFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/20.
 */
public class CleanupActivity extends Activity  {
    protected static final int SUCCESS_GETTASKINFO = 0;
    private TextView tv_task_manager_task_count;
    private TextView tv_task_manager_task_memory;
    private ListView lv_taskmanage;
    private RelativeLayout rl_loading;
    private ProgressBar pb;
    private List<TaskInfo> taskInfos;
    private TaskManagerAdapter mAdapter;
    private static final int ALL_SELECTED_ID = 1;
    private static final int CANCEL_SELECTED_ID = 2;
    private ActivityManager am;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SUCCESS_GETTASKINFO:
                    long total = TaskUtils.getAvailMem(CleanupActivity.this);
                    for(TaskInfo info : taskInfos){
                        total += info.getTask_memory() * 1024;
                    }
                    //可用内存
                    String availMemStr = TextFormat.formatByte(TaskUtils.getAvailMem(CleanupActivity.this));
                    //总内存
                    String totalMemStr = TextFormat.formatByte(total);
                    tv_task_manager_task_memory.setText("可用/总内存:"+availMemStr+"/"+totalMemStr);

                    mAdapter = new TaskManagerAdapter();
                    mAdapter.setInfos(taskInfos);
                    rl_loading.setVisibility(View.GONE);
                    lv_taskmanage.setAdapter(mAdapter);
                    break;

                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_manager);
        tv_task_manager_task_count  = (TextView) findViewById(R.id.tv_task_manager_task_count);
        tv_task_manager_task_memory = (TextView) findViewById(R.id.tv_task_manager_task_memory);
        lv_taskmanage = (ListView) findViewById(R.id.lv_taskmanage);
        rl_loading = (RelativeLayout) findViewById(R.id.rl_loading);
        pb = (ProgressBar) findViewById(R.id.pb);
        //获取ActivityManager系统服务
        int size = TaskUtils.getRunningAppProcessInfoSize(this);
        tv_task_manager_task_count.setText("进程数:"+size);

        new Thread(new Runnable() {

            @Override
            public void run() {
                taskInfos = TaskUtils.getTaskInfos(getApplicationContext());
                Message msg = new Message();
                msg.what = SUCCESS_GETTASKINFO;
                mHandler.sendMessage(msg);
            }
        }).start();
        lv_taskmanage.setOnItemClickListener(new MyOnItemClickListener());
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Log.i("i", "position ====== " +position);
            CheckBox checkBox = ((ViewHolder) view.getTag()).cb_task_manager_selected;
            TaskInfo taskInfo = (TaskInfo) mAdapter.getItem(position);
            //如果是自身应用程序，则直接不执行下面的操作
            if(taskInfo.getPackageName().equals(getPackageName())){
                return;
            }
            if(taskInfo.isChecked()){
                taskInfo.setChecked(false);
                checkBox.setChecked(false);
            }else{
                taskInfo.setChecked(true);
                checkBox.setChecked(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(0, ALL_SELECTED_ID, 0, "全选");
        menu.add(0, CANCEL_SELECTED_ID, 0, "取消选择");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int id = item.getItemId();
        switch (id) {
            case ALL_SELECTED_ID:
                //将每个条目设置为选中状态
                for(TaskInfo taskInfo : taskInfos){
                    //不修改自身应用程序的状态
                    if(!taskInfo.getPackageName().equals(getPackageName())){
                        taskInfo.setChecked(true);
                    }
                }
                //刷新列表
                mAdapter.notifyDataSetChanged();
                break;
            case CANCEL_SELECTED_ID:
                //将每个列表设置为不选中状态
                for(TaskInfo taskInfo : taskInfos){
                    taskInfo.setChecked(false);
                }
                //刷新列表
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void kill_process(View v){
        List<TaskInfo> newTaskInfos = new ArrayList<TaskInfo>();
        for(TaskInfo taskInfo : taskInfos){
            if(taskInfo.isChecked()){
                //杀死选中的进程
                am.killBackgroundProcesses(taskInfo.getPackageName());
            }else{
                newTaskInfos.add(taskInfo);
            }
        }
        mAdapter.setInfos(newTaskInfos);
        mAdapter.notifyDataSetChanged();
    }
    /**
     * 自定义适配器
     * @author liuyazhuang
     *
     */
    private class TaskManagerAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<TaskInfo> infos;

        public void setInfos(List<TaskInfo> infos) {
            this.infos = infos;
        }

        public TaskManagerAdapter(){
            mInflater = getLayoutInflater();
        }
        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder = null;
            if(convertView != null){
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }else{
                view = mInflater.inflate(R.layout.task_manager_item, null);
                holder = new ViewHolder();
                holder.iv_task_manager_icon = (ImageView) view.findViewById(R.id.iv_task_manager_icon);
                holder.iv_task_manager_name = (TextView) view.findViewById(R.id.tv_task_manager_name);
                holder.iv_task_manager_memory = (TextView) view.findViewById(R.id.tv_task_manager_memory);
                //获取到UI上的CheckBox控件
                holder.cb_task_manager_selected = (CheckBox) view.findViewById(R.id.cb_task_manager_selected);
                view.setTag(holder);
            }
            TaskInfo taskInfo = infos.get(position);
            holder.iv_task_manager_icon.setImageDrawable(taskInfo.getTask_icon());
            holder.iv_task_manager_memory.setText("占用的内存:"+TextFormat.formatByte(taskInfo.getTask_memory()*1024));
            holder.iv_task_manager_name.setText(taskInfo.getTask_name());

            String packageName = taskInfo.getPackageName();
            //应用程序是当前运行的程序
            if(packageName.equals(getPackageName())){
                holder.cb_task_manager_selected.setVisibility(View.GONE);
            }else{
                holder.cb_task_manager_selected.setVisibility(View.VISIBLE);
            }
            //获取条目的选中状态
            boolean isChecked = taskInfo.isChecked();
            if(isChecked){
                holder.cb_task_manager_selected.setChecked(true);
            }else{
                holder.cb_task_manager_selected.setChecked(false);
            }
            return view;
        }
    }

    static class ViewHolder{
        ImageView iv_task_manager_icon;
        TextView iv_task_manager_name;
        TextView iv_task_manager_memory;
        CheckBox cb_task_manager_selected;
    }


}
