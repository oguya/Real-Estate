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
import com.droid.keja.model.HomeRent;

import java.util.ArrayList;

/**
 * Created by james on 20/02/14.
 */
public class HomeRentAdapter extends ArrayAdapter<HomeRent> {

    private Activity context;
    private ArrayList<HomeRent> homeRentList;

    private static class ViewHolder{
        public ImageView home_img;
        public TextView rent_txt;
        public TextView name_txt;
        public TextView location_txt;
        public TextView br_ba_txt;
        public TextView type_txt;
    }

    public HomeRentAdapter(Activity context, ArrayList<HomeRent> homeRentList) {
        super(context, R.layout.list_item_rent, homeRentList);

        this.context = context;
        this.homeRentList = homeRentList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = convertView;

        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_rent, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.home_img = (ImageView)rowView.findViewById(R.id.list_home_img);
            viewHolder.name_txt = (TextView)rowView.findViewById(R.id.home_name);
            viewHolder.location_txt = (TextView)rowView.findViewById(R.id.home_location);
            viewHolder.br_ba_txt = (TextView)rowView.findViewById(R.id.home_br_ba);
            viewHolder.type_txt = (TextView)rowView.findViewById(R.id.home_type);

            rowView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder)rowView.getTag();

        String home_name = homeRentList.get(position).getName();
        viewHolder.name_txt.setText(home_name);
        viewHolder.home_img.setImageResource(R.drawable.item_pic);
        viewHolder.location_txt.setText(homeRentList.get(position).getLocation());
        String br_ba = homeRentList.get(position).getBedrooms()+" / "+homeRentList.get(position).getBaths();
        viewHolder.br_ba_txt.setText(br_ba);
        viewHolder.type_txt.setText(homeRentList.get(position).getType());

        //TODO add list scroll animation

        return rowView;
    }
}
