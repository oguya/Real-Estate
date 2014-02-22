package com.droid.keja.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.droid.keja.R;
import com.droid.keja.model.HomeSale;

import java.util.ArrayList;

/**
 * Created by james on 21/02/14.
 */
public class HomeSaleAdapter extends ArrayAdapter<HomeSale> {

    private Activity context;
    private ArrayList<HomeSale> homeSaleList;

    private static class ViewHolder{
        public ImageView home_img;
        public TextView price_txt;
        public TextView name_txt;
        public TextView location_txt;
        public TextView br_ba_txt;
        public TextView type_txt;
    }

    public HomeSaleAdapter(Activity context, ArrayList<HomeSale> homeSaleList) {
        super(context, R.layout.list_item_sale, homeSaleList);

        this.context = context;
        this.homeSaleList = homeSaleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = convertView;

        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_sale, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.home_img = (ImageView)rowView.findViewById(R.id.list_home_img);
            viewHolder.price_txt = (TextView)rowView.findViewById(R.id.home_price);
            viewHolder.name_txt = (TextView)rowView.findViewById(R.id.home_name);
            viewHolder.location_txt = (TextView)rowView.findViewById(R.id.home_location);
            viewHolder.br_ba_txt = (TextView)rowView.findViewById(R.id.home_br_ba);
            viewHolder.type_txt = (TextView)rowView.findViewById(R.id.home_type);

            rowView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder)rowView.getTag();

        String home_name = homeSaleList.get(position).getName();
        viewHolder.price_txt.setText("KES "+homeSaleList.get(position).getPrice());
        viewHolder.name_txt.setText(home_name);
        viewHolder.home_img.setImageResource(R.drawable.item_pic);
        viewHolder.location_txt.setText(homeSaleList.get(position).getLocation());
        String br_ba = homeSaleList.get(position).getBedrooms()+"br / "+homeSaleList.get(position).getBaths()+"ba";
        viewHolder.br_ba_txt.setText(br_ba);
        viewHolder.type_txt.setText(homeSaleList.get(position).getType());

        //TODO add list scroll animation

        return rowView;
    }
}
