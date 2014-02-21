package com.droid.keja.ui.frags;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.droid.keja.R;
import com.droid.keja.adapters.HomeSaleAdapter;
import com.droid.keja.db.DBAdapter;
import com.droid.keja.model.HomeSale;

import java.util.ArrayList;

/**
 * Created by james on 17/02/14.
 */
public class ForSaleFrag extends Fragment {
    private Activity context;
    private ListView listView;
    private DBAdapter dbAdapter;
    private ArrayList<HomeSale> homeSaleList;

    public ForSaleFrag(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.frag_for_sale, container, false);

        //init ui here
        listView = (ListView)rootView.findViewById(R.id.home_sale_list);
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
        homeSaleList = dbAdapter.getHomesSale();
        listView.setAdapter(new HomeSaleAdapter(context, homeSaleList));
        listView.setOnItemClickListener(listViewListener);
    }

    private AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
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
