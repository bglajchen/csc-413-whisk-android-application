package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/10/21 0021.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProduct extends BaseAdapter  {
    private List<ProductXmlHandle.Product> rItem;
    private Context rContext;

    public AdapterProduct(List<ProductXmlHandle.Product> rItem, Context rContext)
    {
        this.rItem = rItem;
        this.rContext = rContext;
    }

    @Override
    public int getCount() {
        return rItem.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
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
        ProductXmlHandle.Product item = (ProductXmlHandle.Product) getItem(position);

        ImageView thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
        Picasso.with(rContext).load(item.getItemImage()).into(thumbnail);

        TextView title = (TextView) rowView.findViewById(R.id.title);
        title.setText(item.getItemName());

        TextView ingredients = (TextView) rowView.findViewById(R.id.ingredients);
        ingredients.setText(item.getItemDescription());

        return rowView;
    }
}

