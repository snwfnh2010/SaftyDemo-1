package com.example.a360safty.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a360safty.R;
import com.example.a360safty.model.AddressDao;
import com.example.a360safty.view.fragment.SecurityFragment;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class PhoneAddressActivity extends Activity implements View.OnClickListener {
    private TextView tv_address_title;
    private EditText et_phone;
    private TextView tv_search;
    private TextView tv_result;
    private Context mContext;
    private String mAddress;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_result.setText(mAddress);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();
    }

    private void initView() {
        mContext=this;
        tv_address_title = (TextView) findViewById(R.id.tv_address_title);
        tv_address_title.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_result= (TextView) findViewById(R.id.tv_result);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
    }

    private void setView() {
        setContentView(R.layout.activity_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_address_title:
                startActivity(new Intent(mContext, SecurityFragment.class));
                this.finish();
                break;
            case R.id.tv_search:
                String phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                query(phone);
                et_phone.setText("");
                break;
        }

    }

    private void query(final String phone) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mAddress= AddressDao.getAddress(phone);
                mHandler.sendEmptyMessage(0);
            }
        }.start();
    }


}
