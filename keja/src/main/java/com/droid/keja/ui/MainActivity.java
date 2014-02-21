package com.droid.keja.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.droid.keja.R;
import com.droid.keja.adapters.NavDrawerListAdapter;
import com.droid.keja.db.DBAdapter;
import com.droid.keja.model.NavDrawerItem;
import com.droid.keja.ui.frags.CommercialFrag;
import com.droid.keja.ui.frags.ForRentFrag;
import com.droid.keja.ui.frags.ForSaleFrag;
import com.droid.keja.utils.FirstRunInit;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private ActionBar actionBar;
    private static final String LOG_TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence drawerAppTitle;
    private String[] drawerMenuTitles;
    private TypedArray drawerMenuIcons;
    private ArrayList<NavDrawerItem> drawerItems;
    private NavDrawerListAdapter drawerListAdapter;

    public DBAdapter dbAdapter;
    private FirstRunInit firstRunInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check first run
        checkFirstRun();

        actionBar = getSupportActionBar();
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        prepDrawerMenu();

        if(savedInstanceState == null){
            //default view...homes for sale
            displayDrawerView(0);
        }

    }

    private void prepDrawerMenu(){
        drawerTitle = drawerAppTitle = getTitle();
        drawerMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        drawerMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.list_slidermenu);

        //add nav drawer items to array
        drawerItems = new ArrayList<NavDrawerItem>();
        drawerItems.add(new NavDrawerItem(drawerMenuTitles[0], drawerMenuIcons.getResourceId(0, -1), true, "100+"));
        drawerItems.add(new NavDrawerItem(drawerMenuTitles[1], drawerMenuIcons.getResourceId(1, -1), true, "30"));
        drawerItems.add(new NavDrawerItem(drawerMenuTitles[2], drawerMenuIcons.getResourceId(2, -1), true, "10"));

        //recycle typed array
        drawerMenuIcons.recycle();
        drawerListAdapter = new NavDrawerListAdapter(getApplicationContext(), drawerItems);
        drawerList.setAdapter(drawerListAdapter);
        drawerList.setOnItemClickListener(new drawerClickListener());

        //enable AB app icon..acts a toggle button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, //drawer layout
                R.drawable.ic_drawer, //nav drawer toggle icon
                R.string.app_name, //drawer open - content desc
                R.string.app_name //drawer close - content desc
                ){
            @Override
            public void onDrawerClosed(View view){
                actionBar.setTitle(drawerTitle);
                //call onPrepareOptionsMenu() to show actionbar icons
                if(!(Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1))
                    invalidateOptionsMenu();
                else
                    supportInvalidateOptionsMenu();
            }

            @Override
        public void onDrawerOpened(View view){
                //call onPrepareOptionsMenu() to hide actionbar icons
                if(!(Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1))
                    invalidateOptionsMenu();
                else
                    supportInvalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    //display frags for selected nav drawer list item
    //update current frag by replacing frags
    private void displayDrawerView(int position){
        Fragment fragment = null;
        switch (position){
            case 0: //Homes for Sale
                fragment = new ForSaleFrag();
                break;
            case 1: //Homes for Rent
                fragment = new ForRentFrag(dbAdapter);
                break;
            case 2: //commercial properties
                fragment = new CommercialFrag();
                break;
            default:
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            //update selected item & title, close drawer
            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);
//            setTitle(drawerMenuTitles[position]);
            drawerLayout.closeDrawer(drawerList);
        }else{
            //null fragment
            Log.e(LOG_TAG, "Unable to create fragment");
            Toast.makeText(this, "Unable to create fragment", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //toggle nav drawer on selecting AB app icon/title
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch(item.getItemId()){
            case R.id.action_settings: //settings
                break;
            case R.id.action_search: //fire up search act.
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //called when invalidateOptionsMenu() is called
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        // if nav drawer is open, hide action items
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title){
        drawerAppTitle = title;
        actionBar.setTitle(drawerAppTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        //sync toggle state after onRestoreInstanceState has occured
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        //pass any configuration changes to drawerToggle
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //navdrawer list item click listener
    private class drawerClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //display view for selected nav drawer item
            displayDrawerView(position);
        }
    }

    public void onPause(){
        super.onPause();

        dbAdapter.close();
    }

    public void onResume(){
        super.onResume();

        dbAdapter.open();
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

    }

    private void checkFirstRun(){
        SharedPreferences firstRunPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean firstRun = firstRunPrefs.getBoolean("FirstRun", true);

        if(firstRun){
            //copy db
            Log.e(LOG_TAG, "First Run! initializing resources...");
            firstRunInit = new FirstRunInit(this);
            firstRunInit.copyDBFile();

            firstRunPrefs.edit().putBoolean("FirstRun", false).commit();
        }else{
            Log.e(LOG_TAG, "First Run! all assets GREEN...");

        }
    }
}
