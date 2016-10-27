package com.example.thirdapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snwfnh on 2016/10/25.
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{


    EditText mEditText;
    ListView mListView;
    int mPosition;//--用于在长按事件时获取长按的项的下标

    MyAdapter mMyAdapter;

    List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setView();
        initView();
        itemListener();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.main,menu);
    }

    /**
     * 如果重写的方法返回值为boolean类型多数为事件是否在当前处理true是处理
     * false是传递给父容器或子控件处理
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_del:

                //--删除. 移除数据,通知适配器
                mList.remove(mPosition);
                mMyAdapter.notifyDataSetChanged();
                break;
            case R.id.menu_update:
                //--弹出修改的对话框. 对话框android提供了一个类Dialog.推荐使用AlertDialog

                //--1.构建Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //--2.给Builder设置参数
                builder.setTitle("这是修改对话框");
                final EditText edit = new EditText(this);
                edit.setHint("请输入修改后的内容");
                builder.setView(edit);
                builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //--局部内部类访问方法中的局部变量时,该变量需要时final的无论是引用还是基本
                        String temp = edit.getText().toString();
                        if (TextUtils.isEmpty(temp))
                            return;
                        mList.set(mPosition,temp);
                        mMyAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


                //--4.通过Builder构建对话框
                AlertDialog dialog = builder.create();
                //--5.通过show方法显示
                dialog.show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * ListView  针对项提供了项单击,项长按,项选中3个事件
     *  选中需要物理键盘支持
     *  长按事件触发的同时也会出发单击
     *
     *
     *
     *
     */
    private void itemListener() {

        mListView.setOnItemLongClickListener(this);
    }

    private void init() {
        mList = new ArrayList<>();
        mMyAdapter = new MyAdapter();
    }


    private void initView() {
        mEditText = (EditText) findViewById(R.id.edit_entryCountry);
        mListView = (ListView) findViewById(R.id.lv_showCountry);
    }


    /**
     * 当在xml中添加android:onClick属性时其属性值为方法的名称.
     *  该方法是处理单击事件的方法..且该方法必须是public.必须是void
     *  必须有一个参数为View
     */
    public void onClick(View view){
        //--1.获取EditText中的内容
        String temp = mEditText.getText().toString();
        if (TextUtils.isEmpty(temp)) {
            Toast.makeText(this, "国家名称不可以为空", Toast.LENGTH_SHORT).show();
            return ;
        }

        //--2.把EditText中内容添加到List中
        mList.add(temp);

        //--3.刷新适配器
        mMyAdapter.notifyDataSetChanged();

        //--4.给ListView添加数据
        mListView.setAdapter(mMyAdapter);

        //--5.清掉之前添加内容
        mEditText.setText("");


    }

    private void setView() {
        setContentView(R.layout.activity_listview);
    }

    /**
     *
     *
     *
     *
     * @param adapterView  因为ListView继承AbsListView AbsListView继承AdapterView 触发长按事件的容器
     * @param view 被长按的View对象.思考:对应getView方法返回的View对象
     * @param i 所长按的项的下标
     * @param l 所长按的项的ID 就是BaseAdapter中getItemId方法的返回值
     * @return
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast t = Toast.makeText(this,i+"",Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
        mPosition = i;
        return false;
    }


    public class MyAdapter extends BaseAdapter {

        /**
         * 返回ListView 要显示多少项.通常是数据源的大小
         * @return
         */
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        /**
         * 创建每一项的View对象:
         *  思考:
         *         1.如果一个列表有3项那么getView方法被调用多少次
         * @param i  项的位置
         * @param view is an old view can reuse
         * @param viewGroup 容器
         * @return
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View convertView = view;
            if (convertView ==  null){
                //--生成一个View对象.通过解析xml文件生成.
//                LayoutInflater lf = getLayoutInflater();
//                LayoutInflater lf = LayoutInflater.from(ListViewActivity.this);
//                LayoutInflater lf = LayoutInflater.from(viewGroup.getContext());
                LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view = View.inflate(Context context, int resource, ViewGroup root)

                //int resource, ViewGroup root
                convertView = lf.inflate(android.R.layout.simple_list_item_1,null);
                registerForContextMenu(convertView);//--给每一项注册上下文菜单
            }
            //--2.从View中找到控件
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setTextSize(40);
//            LinearLayout.LayoutParams  通过它设置项的宽高
            textView.setTextColor(Color.RED);
            //--3.从数据源中取出值给控件赋值
            textView.setText(mList.get(i));


            return convertView;
        }
    }





















}
