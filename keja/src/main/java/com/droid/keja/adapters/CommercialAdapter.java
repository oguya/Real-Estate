package com.droid.keja.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.droid.keja.R;
import com.droid.keja.model.Commercial;

import java.util.ArrayList;

/**
 * Created by james on 22/02/14.
 */
public class CommercialAdapter extends ArrayAdapter<Commercial> {

    private Activity context;
    private ArrayList<Commercial> commercialList;

    private static class ViewHolder{
        public ImageView property_img;
        public TextView name_txt;
        public TextView location_txt;
        public TextView size_txt;
        public TextView type_txt;
    }

    public CommercialAdapter(Activity context, ArrayList<Commercial> commercialList) {
        super(context, R.layout.list_item_commercial, commercialList);

        this.context = context;
        this.commercialList = commercialList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = convertView;

        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_commercial, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.property_img = (ImageView)rowView.findViewById(R.id.list_property_img);
            viewHolder.name_txt = (TextView)rowView.findViewById(R.id.property_name);
            viewHolder.location_txt = (TextView)rowView.findViewById(R.id.property_location);
            viewHolder.size_txt = (TextView)rowView.findViewById(R.id.property_size);
            viewHolder.type_txt = (TextView)rowView.findViewById(R.id.property_type);

            rowView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder)rowView.getTag();

        viewHolder.property_img.setImageResource(R.drawable.item_pic);
        viewHolder.name_txt.setText(commercialList.get(position).getName());
        viewHolder.location_txt.setText(commercialList.get(position).getLocation());
        viewHolder.size_txt.setText(commercialList.get(position).getSize());
        viewHolder.type_txt.setText(commercialList.get(position).getType());

        //TODO add list scroll animation

        return rowView;
    }

}
