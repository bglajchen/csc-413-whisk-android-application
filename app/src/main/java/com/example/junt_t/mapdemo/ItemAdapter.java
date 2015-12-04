package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/10/21 0021.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/*
 * Custom Adapter class that is responsible for holding the list of sites after they
 * get parsed out of XML and building row views to display them on the screen.
 */
public class ItemAdapter extends BaseAdapter {

    private ArrayList<String> rItem;
    private Context rContext;

    public ItemAdapter(ArrayList<String> rItem, Context rContext)
    {
        this.rItem = rItem;
        this.rContext = rContext;
    }

    @Override
    public int getCount() {
        return rItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) rContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_item_list, parent, false);

//        TableLayout hide = (TableLayout) rowView.findViewById(R.id.nameTxt);
//        hide.setVisibility(View.GONE);

//        String item = (IngredientSearchResponse.HitsEntity) getItem(position);

        TextView nameTxt = (TextView) rowView.findViewById(R.id.nameTxt);
        nameTxt.setText(rItem.get(position));
        return rowView;
    }
}
