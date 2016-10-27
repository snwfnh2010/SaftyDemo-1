package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a360safty.R;

/**
 * Created by snwfnh on 2016/10/25.
 */
public class EnterPsdActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    private String packageName;
    private TextView tv_name;
    private ImageView iv_icon;
    private EditText et_psd;
    private TextView tv_okpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();

    }



    private void setView() {
        setContentView(R.layout.activity_enterpwd);
    }

    private void initView() {
        mContext=this;
        tv_name = (TextView) findViewById(R.id.tv_name);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        et_psd = (EditText) findViewById(R.id.et_psd);
        tv_okpwd = (TextView) findViewById(R.id.tv_okpwd);

        packageName=getIntent().getStringExtra("packageName");
        PackageManager pm = getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = pm.getApplicationInfo(packageName, 0);
            Drawable icon = applicationInfo.loadIcon(pm);
            String name = applicationInfo.loadLabel(pm).toString();
            tv_name.setText(name);
            iv_icon.setBackgroundDrawable(icon);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



        tv_okpwd.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        String psd = et_psd.getText().toString().trim();
        if (!TextUtils.isEmpty(psd)) {
            if (psd.equals("123")){
                finish();

                Intent intent = new Intent("com.example.a360safty.SKIP_WATCH");
                intent.putExtra("packageName", packageName);
                sendBroadcast(intent);
            }

        }else {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;

            }


    }
}
