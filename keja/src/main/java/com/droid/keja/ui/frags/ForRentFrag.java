package com.droid.keja.ui.frags;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.droid.keja.R;
import com.droid.keja.adapters.HomeRentAdapter;
import com.droid.keja.db.DBAdapter;
import com.droid.keja.model.HomeRent;
import com.droid.keja.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by james on 17/02/14.
 */
public class ForRentFrag extends Fragment {

    Activity context;
    private final String LOG_TAG = "ForRentFrag";
    private ListView listView;
    public ArrayList<HomeRent> homeRentList;
    private DBAdapter dbAdapter;

    public ForRentFrag(){}

    public ForRentFrag(DBAdapter dbAdapter){
        this.dbAdapter = dbAdapter;
        this.dbAdapter.open();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.frag_for_rent, container, false);

        //init ui here
        listView = (ListView)rootView.findViewById(R.id.home_rent_list);
        listView.setOnItemClickListener(listListener);
        return rootView;
    }

    private AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    //change ui stuff..frag is live
    @Override
    public void onStart(){
        super.onStart();

        homeRentList = new ArrayList<HomeRent>();
        homeRentList = dbAdapter.getHomesRent();
        listView.setAdapter(new HomeRentAdapter(this.context, homeRentList));
        listView.setOnItemClickListener(listListener);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}
