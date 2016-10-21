package com.example.a360safty.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a360safty.R;

/**
 * Created by snwfnh on 2016/10/20.
 */
public class GridAdapter extends BaseAdapter {
    private int[] icon;
    private String[] iconName;
    private Context mContext;

    public GridAdapter(Context context,int[] icon, String[] iconName ) {
        this.icon = icon;
        mContext = context;
        this.iconName = iconName;
    }

    @Override
    public int getCount() {
        return icon.length;
    }

    @Override
    public Object getItem(int position) {
        return icon[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view=View.inflate(mContext, R.layout.gridlist_item,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.image_item);
        TextView textView= (TextView) view.findViewById(R.id.text_item);
        imageView.setBackgroundResource(icon[position]);
        textView.setText(iconName[position]);
        return view;
    }
}
