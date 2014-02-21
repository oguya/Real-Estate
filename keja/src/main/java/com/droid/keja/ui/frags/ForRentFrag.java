package com.droid.keja.ui.frags;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    //change ui stuff..frag is live
    @Override
    public void onStart(){
        super.onStart();

        dbAdapter = new DBAdapter(context);
        dbAdapter.open();
//        homeRentList = new ArrayList<HomeRent>();
        homeRentList = dbAdapter.getHomesRent();

        listView = (ListView)context.findViewById(R.id.home_rent_list);
        listView.setAdapter(new HomeRentAdapter(this.context, homeRentList));
        listView.setOnItemClickListener(listListener);
    }

    private AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "item click: ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onResume(){
        super.onResume();
        dbAdapter.open();
    }

    @Override
    public void onPause(){
        super.onPause();
        dbAdapter.close();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}
