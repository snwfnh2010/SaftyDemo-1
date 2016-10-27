package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a360safty.R;
import com.example.a360safty.model.AppInfo;
import com.example.a360safty.model.AppInfoProvider;
import com.example.a360safty.model.AppLockDao;
import com.example.a360safty.service.WatchDogService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class SetupOverActivity extends Activity implements View.OnClickListener{
    private TextView mTextView_unLock,mTextView_Lock,mTextView_unLockNum,mTextView_LockNum,mTextView_addService;
    private LinearLayout ll_unLock,ll_Lock;
    private ListView mListView_unLock,mListView_Lock;
    private List<AppInfo> mAppInfoList;
    private List<AppInfo> mLockList;
    private List<AppInfo> mUnLockList;
    private AppLockDao mDao;
    private AppLockAdapter mLockAdapter;
    private AppLockAdapter mUnLockAdapter;
    private Context mContext;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            mLockAdapter = new AppLockAdapter(true);
            mListView_Lock.setAdapter(mLockAdapter);

            mUnLockAdapter = new AppLockAdapter(false);
            mListView_unLock.setAdapter(mUnLockAdapter);
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();
        initData();
    }

    private void initView() {
        mTextView_unLock= (TextView) findViewById(R.id.tv_unlock);
        mTextView_Lock= (TextView) findViewById(R.id.tv_lock);

        ll_unLock= (LinearLayout) findViewById(R.id.ll_unlock);
        ll_Lock= (LinearLayout) findViewById(R.id.ll_lock);

        mTextView_unLockNum= (TextView) findViewById(R.id.tv_unlocknum);
        mTextView_LockNum= (TextView) findViewById(R.id.tv_locknum);

        mListView_unLock= (ListView) findViewById(R.id.lv_unlock);
        mListView_Lock= (ListView) findViewById(R.id.lv_lock);

        mTextView_unLock.setOnClickListener(this);
        mTextView_Lock.setOnClickListener(this);
        mTextView_addService= (TextView) findViewById(R.id.tv_addservice);
        mTextView_addService.setOnClickListener(this);

    }

    private void initData() {
        mContext=this;
        new Thread(){
            public void run() {

                mAppInfoList = AppInfoProvider.getAppInfoList(mContext);

                mLockList = new ArrayList<AppInfo>();
                mUnLockList = new ArrayList<AppInfo>();

                mDao = AppLockDao.getInstance(mContext);
                List<String> lockPackageList = mDao.findAll();

                for (AppInfo appInfo : mAppInfoList) {

                    if(lockPackageList.contains(appInfo.packageName)){
                        mLockList.add(appInfo);
                    }else{
                        mUnLockList.add(appInfo);
                    }
                }

                mHandler.sendEmptyMessage(0);
            };
        }.start();
    }

    private void setView() {
        setContentView(R.layout.activity_setupover);
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_unlock:
                ll_Lock.setVisibility(View.GONE);
                ll_unLock.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_lock:
                ll_Lock.setVisibility(View.VISIBLE);
                ll_unLock.setVisibility(View.GONE);
                break;
            case R.id.tv_addservice:

                startService(new Intent(mContext, WatchDogService.class));
                Toast.makeText(mContext, "开启程序锁服务成功", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    class AppLockAdapter extends BaseAdapter {
        private boolean isLock;

        public AppLockAdapter(boolean isLock) {
            this.isLock = isLock;
        }
        @Override
        public int getCount() {
            if(isLock){
                mTextView_LockNum.setText("已加锁应用:"+mLockList.size());
                return mLockList.size();
            }else{
                mTextView_unLockNum.setText("未加锁应用:"+mUnLockList.size());
                return mUnLockList.size();
            }
        }

        @Override
        public AppInfo getItem(int position) {
            if(isLock){
                return mLockList.get(position);
            }else{
                return mUnLockList.get(position);
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = View.inflate(mContext, R.layout.listview_applock_item, null);
                holder = new ViewHolder();
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.iv_lock = (ImageView) convertView.findViewById(R.id.iv_lock);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            final AppInfo appInfo = getItem(position);
            final View animationView = convertView;

            holder.iv_icon.setBackgroundDrawable(appInfo.icon);
            holder.tv_name.setText(appInfo.name);
            if(isLock){
                holder.iv_lock.setBackgroundResource(R.mipmap.locked);
            }else{
                holder.iv_lock.setBackgroundResource(R.mipmap.unlocked);
            }
            holder.iv_lock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isLock){

                        mLockList.remove(appInfo);
                        mUnLockList.add(appInfo);

                        mDao.delete(appInfo.packageName);

                        mLockAdapter.notifyDataSetChanged();
                    }else{

                        mLockList.add(appInfo);
                        mUnLockList.remove(appInfo);

                        mDao.insert(appInfo.packageName);

                        mUnLockAdapter.notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_name;
        ImageView iv_lock;
    }





}
