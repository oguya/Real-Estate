package com.droid.keja.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.droid.keja.R;

import java.util.ArrayList;

/**
 * Created by james on 23/02/14.
 */
public class ImageSliderAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<Integer> imageURLList;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(Activity activity, ArrayList<Integer> imageURLList){
        this.activity = activity;
        this.imageURLList = imageURLList;
    }

    @Override
    public int getCount() {
        return this.imageURLList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imgDisplay;
        Button closeBTN;

        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = layoutInflater.inflate(R.layout.imageslider_item, container, false);
        imgDisplay = (ImageView)viewLayout.findViewById(R.id.imgDisplay);
        closeBTN = (Button)viewLayout.findViewById(R.id.btnClose);

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        Bitmap bitmap = BitmapFactory.decodeFile(imageURLList.get(position), options);
//        imgDisplay.setImageBitmap(bitmap);

        imgDisplay.setImageResource(imageURLList.get(position));

        //close button click event
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        ((ViewPager)container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
