package com.example.junt_t.mapdemo;

import android.content.Context;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junt_T on 2015/11/19 0019.
 */
public class StoreAdapter extends ArrayAdapter<Store> {

    public StoreAdapter(Context ctx, int textViewResourceId, List<Store> pList) {
        super(ctx, textViewResourceId, pList);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        RelativeLayout row = (RelativeLayout)convertView;
        Log.i("xml", "getView pos = " + pos);
        if(null == row){
            //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout)inflater.inflate(R.layout.activity_item_list, null);
        }

        //Get our View References
        TextView nameTxt = (TextView)row.findViewById(R.id.nameTxt);
        TextView aboutTxt = (TextView)row.findViewById(R.id.addressTxt);
        TextView idTxt = (TextView)row.findViewById(R.id.idTxt);
        //final ProgressBar indicator = (ProgressBar)row.findViewById(R.id.progress);

        //Load the image and use our options so caching is handled.
       // imageLoader.displayImage(getItem(pos).getItemImage(), iconImg, options, listener);

        //Set the relavent text in our TextViews
        nameTxt.setText(getItem(pos).getStorename());
        aboutTxt.setText(getItem(pos).getAddress());
        idTxt.setText(getItem(pos).getStoreId());

        return row;
    }
}
