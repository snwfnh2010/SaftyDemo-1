package com.example.a360safty.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.a360safty.R;
import com.example.a360safty.model.ConstantValue;
import com.example.a360safty.model.Md5Utils;
import com.example.a360safty.model.SpUtil;
import com.example.a360safty.view.activity.SetupOverActivity;
import com.example.a360safty.view.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snwfnh on 2016/10/17.
 */
public class ToolKitFragment extends Fragment implements GridView.OnItemClickListener{
    private GridView mGridView;
    private Context mContext;
    private int[] icon;
    private String[] iconName;
    private GridAdapter mGridAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_toolkit,container,false);
        intiView(view);
        initData();

        return view;
    }



    private void initData() {
        mContext=this.getActivity();
        icon = new int[]{R.mipmap.tof_p1,R.mipmap.tof_p2,R.mipmap.tof_p3,R.mipmap.tof_p4,R.mipmap.tof_p5,R.mipmap.tof_p6,R.mipmap.tof_p7,R.mipmap.tof_p8,R.mipmap.top_p9};
        iconName = new String[]{"智能锁屏", "流量监控", "支付保镖", "程度锁","手机备份", "免费WiFi", "防广告骚扰", "更多工具"," "};
        mGridAdapter=new GridAdapter(mContext,icon,iconName);
        mGridView.setAdapter(mGridAdapter);


    }

    private void intiView(View view) {
        mGridView= (GridView) view.findViewById(R.id.gridview_toolkit);

        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                showDialog();

                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
           default:
               break;

        }

    }

    private void showDialog() {
        String psd= SpUtil.getString(mContext, ConstantValue.MOBILE_SAFE_PSD,"");
        if(TextUtils.isEmpty(psd)){
            showSetPsdDialog();
        }else {
            showConfirmPsdDialog();
        }
    }

    private void showConfirmPsdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        final View view = View.inflate(mContext, R.layout.dialog_confirm_psd, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_confirm_psd = (EditText)view.findViewById(R.id.et_confirm_psd);

                String confirmPsd = et_confirm_psd.getText().toString();

                if(!TextUtils.isEmpty(confirmPsd)){

                    String psd = SpUtil.getString(mContext, ConstantValue.MOBILE_SAFE_PSD, "");

                    if(psd.equals(Md5Utils.encoder(confirmPsd))){

//						Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                        Intent intent = new Intent(mContext, SetupOverActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }else{
                        Toast.makeText(mContext, "确认密码错误", Toast.LENGTH_SHORT).show();

                    }
                }else{

                    Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();

                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showSetPsdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();

        final View view = View.inflate(mContext, R.layout.dialog_set_psd, null);
        //让对话框显示一个自己定义的对话框界面效果
//		dialog.setView(view);

        //为了兼容低版本,给对话框设置布局的时候,让其没有内边距(android系统默认提供出来的)
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_set_psd = (EditText) view.findViewById(R.id.et_set_psd);
                EditText et_confirm_psd = (EditText)view.findViewById(R.id.et_confirm_psd);

                String psd = et_set_psd.getText().toString();
                String confirmPsd = et_confirm_psd.getText().toString();

                if(!TextUtils.isEmpty(psd) && !TextUtils.isEmpty(confirmPsd)){
                    if(psd.equals(confirmPsd)){


                        Intent intent = new Intent(mContext, SetupOverActivity.class);
                        startActivity(intent);

                        dialog.dismiss();

                        SpUtil.putString(mContext,
                                ConstantValue.MOBILE_SAFE_PSD,Md5Utils.encoder(confirmPsd));
                    }else{
                        Toast.makeText(mContext, "确认密码错误", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();

                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
