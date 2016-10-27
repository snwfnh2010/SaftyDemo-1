package com.example.a360safty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a360safty.R;
import com.example.a360safty.model.PhoneRecord;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class PhoneAdapter extends BaseAdapter {
    private List<PhoneRecord> mPhoneRecordList;
    private Context mContext;

    public PhoneAdapter(List<PhoneRecord> phoneRecordList, Context context) {
        mPhoneRecordList = phoneRecordList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mPhoneRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPhoneRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.phone_list_item,null);
            holder=new ViewHolder();
            holder.mTextView_number= (TextView) convertView.findViewById(R.id.tv_number);
            holder.mTextView_sign= (TextView) convertView.findViewById(R.id.tv_sign);
            holder.mTextView_date= (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mTextView_number.setText(mPhoneRecordList.get(position).getNumber());
        holder.mTextView_sign.setText("");
        holder.mTextView_date.setText(mPhoneRecordList.get(position).getData());

        return convertView;
    }
    class ViewHolder{
        TextView mTextView_number,mTextView_date,mTextView_sign;


    }
}
