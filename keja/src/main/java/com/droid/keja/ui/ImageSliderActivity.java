package com.droid.keja.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.droid.keja.R;
import com.droid.keja.adapters.ImageSliderAdapter;

import java.util.ArrayList;

/**
 * Created by james on 23/02/14.
 */
public class ImageSliderActivity extends ActionBarActivity {

    private static final String LOG_TAG = "ImageSliderActivity";
    private ActionBar actionBar;
    private ViewPager viewPager;
    private ImageSliderAdapter adapter;
    private ArrayList<Integer> imageList;

    private int IMAGE_POS = 0;
    private static final String KEY_IMAGE_POS = "IMAGE_POS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_imageslider);
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        initUI();
    }

    private void initUI(){
        imageList = new ArrayList<Integer>();

        //dummy data
        imageList.add(R.drawable.home_int_1);
        imageList.add(R.drawable.home_int_2);
        imageList.add(R.drawable.home_int_3);
        imageList.add(R.drawable.home_int_4);
        imageList.add(R.drawable.home_int_5);

        viewPager = (ViewPager)findViewById(R.id.pager);
        adapter = new ImageSliderAdapter(this, imageList);
        if(viewPager == null){
            Log.e(LOG_TAG, "viewPager is NULL");
        }else{
            Log.e(LOG_TAG, "viewPager is NOT NULL");
        }
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(IMAGE_POS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.imageslider, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPause(){
        super.onPause();
        IMAGE_POS = viewPager.getCurrentItem();
    }


    @Override
    public void onResume(){
        super.onResume();
        viewPager.setCurrentItem(IMAGE_POS);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_IMAGE_POS, IMAGE_POS);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        IMAGE_POS = savedInstanceState.getInt(KEY_IMAGE_POS);
    }

}
