package com.droid.keja.ui;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.droid.keja.R;
import com.droid.keja.adapters.TabsPagerAdapter;
import com.droid.keja.constants.Constants;

public class SearchActivity extends ActionBarActivity {

    private ActionBar actionBar;
    private final String LOG_TAG = "SearchActivity";

    private ViewPager viewPager;
    private TabsPagerAdapter tabsPagerAdapter;
    private String[] TabsTitle = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        initTabs();

        if (savedInstanceState == null) {
            //default frag view
        }

    }

    private void initTabs(){
        TabsTitle = getResources().getStringArray(R.array.search_tabs_title);
        viewPager = (ViewPager)findViewById(R.id.search_pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), TabsTitle);
        viewPager.setAdapter(tabsPagerAdapter);

        for(String tabTitle : TabsTitle)
            actionBar.addTab(actionBar.newTab().setText(tabTitle).setTabListener(tabListener));

        viewPager.setOnPageChangeListener(pageChangeListener);
    }


    //tab listener
    private ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    };


    //select the respective tab
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int position) {
            actionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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

}
