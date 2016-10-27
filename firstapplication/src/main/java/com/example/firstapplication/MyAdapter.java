package com.example.firstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by snwfnh on 2016/10/22.
 */
public class MyAdapter extends BaseAdapter {
    private List<MessageInfo> mList;
    private Context mContext;
    private MessageInfo mMessageInfo;

    public MyAdapter(List<MessageInfo> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold=null;
        mMessageInfo=mList.get(position);

        if (convertView==null){

            convertView= LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            convertView.setTag(viewHold);
        }else {
            viewHold= (ViewHold) convertView.getTag();
        }

      viewHold.mTextView_mobile.setText(mMessageInfo.getName());
        viewHold.mTextView_numb.setText(mMessageInfo.getNum());
        viewHold.mTextView_image.setText(mMessageInfo.getImage());


        return convertView;
    }

    class ViewHold{
        TextView mTextView_mobile;
        TextView mTextView_numb;
        TextView mTextView_image;
      ViewHold(View view) {
            mTextView_mobile= (TextView) view.findViewById(R.id.tv_mobile);
            mTextView_numb= (TextView) view.findViewById(R.id.tv_numb);
            mTextView_image= (TextView) view.findViewById(R.id.tv_image);
        }



    }

}
