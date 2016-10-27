package com.example.myapplication;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by snwfnh on 2016/10/21.
 */
public class FragmentA extends ListFragment {
    private String[] data={"AAA","BBB","CCC"};
    private SetSelectedCityListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,data));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mListener.selectedCity(data[position]);
    }

    public void setListener(SetSelectedCityListener mListener){
        this.mListener=mListener;
    }

}
