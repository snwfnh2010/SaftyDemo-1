package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a360safty.R;
import com.example.a360safty.model.AppInfo;
import com.example.a360safty.model.AppInfoProvider;
import com.example.a360safty.view.adapter.AppListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/22.
 */
public class AppManageActivity extends Activity implements View.OnClickListener {
    private List<AppInfo> mAppInfoList;
    private ListView mListView_app;
    private AppListAdapter mAdapter;
    private List<AppInfo> mCustomerList;
    private List<AppInfo> mSystemList;
    private Context mContext;
    private TextView tv_des;
    private AppInfo mAppInfo;

    private TextView tv_uninstall;
    private TextView tv_start;
    private TextView tv_share;
    private TextView tv_app_title;
    private PopupWindow mPopupWindow;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mAdapter = new AppListAdapter(mCustomerList, mSystemList, mContext);
            mListView_app.setAdapter(mAdapter);

            if (tv_des != null && mCustomerList != null) {
                tv_des.setText("用户应用(" + mCustomerList.size() + ")");
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();
        initTitle();
        initData();


    }

    private void initData() {



        new Thread() {
            @Override
            public void run() {
                mAppInfoList = AppInfoProvider.getAppInfoList(mContext);
                mSystemList = new ArrayList<>();
                mCustomerList = new ArrayList<>();
                for (AppInfo appInfo : mAppInfoList) {
                    if (appInfo.isSystem) {
                        mSystemList.add(appInfo);
                    } else {

                        mCustomerList.add(appInfo);
                    }
                }
                mHandler.sendEmptyMessage(0);
            }

            ;
        }.start();
    }

    private void initView() {
        mContext=this;
        mListView_app = (ListView) findViewById(R.id.lv_app_list);
        tv_des = (TextView) findViewById(R.id.tv_des);
        mListView_app.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 || position == mCustomerList.size()+1){
                    return;
                }else{
                    if(position<mCustomerList.size()+1){
                        mAppInfo = mCustomerList.get(position-1);
                    }else{

                        mAppInfo = mSystemList.get(position - mCustomerList.size()-2);
                    }
                    showPopupWindow(view);
                }

            }
        });


    }

    private void setView() {
        setContentView(R.layout.activity_app_manager);
    }




    private void showPopupWindow(View view) {
        view = LayoutInflater.from(mContext).inflate(R.layout.popup_item, null);
        tv_uninstall = (TextView) findViewById(R.id.tv_uninstall);
        tv_uninstall.setOnClickListener(this);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_start.setOnClickListener(this);
        tv_share = (TextView) findViewById(R.id.tv_share);
        tv_share.setOnClickListener(this);

        mPopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAsDropDown(view, 50, -view.getHeight());


    }

    private void initTitle() {
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        long  sdAvailSpace = initAvailSpace(sdPath);
        String dataPath = Environment.getDataDirectory().getAbsolutePath();
        long dataAvailSpace = initAvailSpace(dataPath);

        String strSdAvailSpace = Formatter.formatFileSize(this, sdAvailSpace);
        String strDataAvailSpace = Formatter.formatFileSize(this, dataAvailSpace);


        TextView tv_memory = (TextView) findViewById(R.id.tv_memory);
        TextView tv_sd_memory = (TextView) findViewById(R.id.tv_sd_memory);

        tv_memory.setText("磁盘可用:"+strDataAvailSpace);
        tv_sd_memory.setText("sd卡可用:"+strSdAvailSpace);

        tv_app_title = (TextView) findViewById(R.id.tv_app_title);
    }


    private long initAvailSpace(String path) {


        StatFs statFs = new StatFs(path);


        long blockSize = statFs.getBlockSize();

        long blockCount = statFs.getAvailableBlocks();

        return blockSize*blockCount;

    }

    @Override
    public void onClick(View v) {


        if(mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
        switch (v.getId()) {
            case R.id.tv_uninstall:
                uninstall();
                break;
            case R.id.tv_start:
                start();
                break;
            case R.id.tv_share:

                share();
                break;
        }
    }


    private void uninstall() {
               if(mAppInfo.isSystem){
            Toast.makeText(mContext, "系统应用不能卸载", Toast.LENGTH_SHORT).show();

        }else{
                      Intent intent = new Intent("android.intent.action.DELETE");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:"+mAppInfo.packageName));
            startActivityForResult(intent, 0);
        }


    }

    private void start() {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(mAppInfo.packageName);
        if(intent!=null){
            startActivity(intent);
        }else{
            Toast.makeText(mContext, "此应用无法开启", Toast.LENGTH_SHORT).show();
        }
    }


    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "分享一个很火的应用,"+mAppInfo.name);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
             initData();
        super.onActivityResult(requestCode, resultCode, data);
    }

}
