package com.example.a360safty.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a360safty.R;
import com.example.a360safty.model.AppInfo;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/22.
 */
public class AppListAdapter extends BaseAdapter {
    private List<AppInfo> mCustomerList;
    private List<AppInfo> mSystemList;
    private Context mContext;

    public AppListAdapter(List<AppInfo> customerList, List<AppInfo> systemList, Context context) {
        mCustomerList = customerList;
        mSystemList = systemList;
        mContext = context;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || position == mCustomerList.size()+1){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getCount() {
        return mCustomerList.size()+mSystemList.size()+2;
    }

    @Override
    public AppInfo getItem(int position) {
        if(position == 0 || position == mCustomerList.size()+1){
            return null;
        }else{
            if(position<mCustomerList.size()+1){
                return mCustomerList.get(position-1);
            }else{

                return mSystemList.get(position - mCustomerList.size()-2);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        if(type == 0){

            ViewTitleHolder holder = null;
            if(convertView == null){
                convertView = View.inflate(mContext, R.layout.listview_app_item_title, null);
                holder = new ViewTitleHolder();
                holder.tv_title = (TextView)convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            }else{
                holder = (ViewTitleHolder) convertView.getTag();
            }
            if(position == 0){
                holder.tv_title.setText("用户应用("+mCustomerList.size()+")");
            }else{
                holder.tv_title.setText("系统应用("+mSystemList.size()+")");
            }
            return convertView;
        }else{
            //展示图片+文字条目
            ViewHolder holder = null;
            if(convertView == null){
                convertView = View.inflate(mContext, R.layout.listview_app_item, null);
                holder = new ViewHolder();
                holder.iv_icon = (ImageView)convertView.findViewById(R.id.iv_icon);
                holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
                holder.tv_path = (TextView) convertView.findViewById(R.id.tv_path);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.iv_icon.setBackgroundDrawable(getItem(position).icon);
            holder.tv_name.setText(getItem(position).name);
            if(getItem(position).isSdCard){
                holder.tv_path.setText("sd卡应用");
            }else{
                holder.tv_path.setText("手机应用");
            }
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_path;
    }

    static class ViewTitleHolder{
        TextView tv_title;
    }

}
