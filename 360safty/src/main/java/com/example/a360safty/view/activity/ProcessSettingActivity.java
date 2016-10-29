package com.example.a360safty.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.a360safty.R;
import com.example.a360safty.model.ConstantValue;
import com.example.a360safty.model.SpUtil;

/**
 * Created by snwfnh on 2016/10/26.
 */
public class ProcessSettingActivity extends Activity  {

    private TextView tv_processSetting_title;
    private CheckBox cb_hide;
    private CheckBox cb_lock_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();

    }

    private void initView() {

        tv_processSetting_title = (TextView) findViewById(R.id.tv_process_title);

        cb_hide = (CheckBox) findViewById(R.id.cb_hide);

        cb_lock_clear = (CheckBox) findViewById(R.id.cb_lock_clear);

    }

    private void initSystemShow() {
        boolean showSystem = SpUtil.getBoolean(this, ConstantValue.SHOW_SYSTEM, false);
        cb_hide.setChecked(showSystem);

        if (showSystem) {
            cb_hide.setText("显示系统进程");
        } else {
            cb_hide.setText("隐藏系统进程");
        }

        cb_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_hide.setText("显示系统进程");
                } else {
                    cb_hide.setText("隐藏系统进程");
                }
                SpUtil.putBoolean(ProcessSettingActivity.this, ConstantValue.SHOW_SYSTEM, isChecked);
            }
        });
    }

    private void setView() {
        setContentView(R.layout.activity_setting);
    }


}
