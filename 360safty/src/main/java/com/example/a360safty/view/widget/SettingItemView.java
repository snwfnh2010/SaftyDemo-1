package com.example.a360safty.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a360safty.R;

/**
 * Created by snwfnh on 2016/10/25.
 */
public class SettingItemView extends RelativeLayout {

    private TextView tv_title;
    private TextView tv_des;
    private CheckBox cb_box;
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.example.a360safty";
    private String mDesTitle;
    private String mDesOff;
    private String mDesOn;
    public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
        initUI(context);
    }
    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }
    public SettingItemView(Context context) {
        this(context,null);

    }

    private void initUI(Context context) {
        View.inflate(context, R.layout.seting_item_view, this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);

        cb_box = (CheckBox) findViewById(R.id.cb_box);

        tv_title.setText(mDesTitle);
    }


    public boolean isCheck(){

        return cb_box.isChecked();
    }


    public void setCheck(boolean isCheck){
        cb_box.setChecked(isCheck);
        if(isCheck){
            tv_des.setText(mDesOn);
        }else{
            tv_des.setText(mDesOff);
        }
    }


    private void initAttrs(AttributeSet attrs) {


        mDesTitle = attrs.getAttributeValue(NAMESPACE,"desTitle");
        mDesOff = attrs.getAttributeValue(NAMESPACE,"desOff");
        mDesOn = attrs.getAttributeValue(NAMESPACE,"desOn");

    }
}
