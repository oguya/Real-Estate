package com.droid.keja.ui.frags;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.droid.keja.R;
import com.droid.keja.adapters.CommercialAdapter;
import com.droid.keja.db.DBAdapter;
import com.droid.keja.model.Commercial;

import java.util.ArrayList;

/**
 * Created by james on 17/02/14.
 */
public class CommercialFrag extends Fragment {

    Activity context;
    private final String LOG_TAG = "CommercialFrag";
    private ListView listView;
    private TextView no_itemsTXT;
    public ArrayList<Commercial> commercialList;
    private DBAdapter dbAdapter;

    public CommercialFrag(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.frag_commercial, container, false);

        //init ui here
        listView = (ListView)rootView.findViewById(R.id.commercial_list);
        no_itemsTXT = (TextView)rootView.findViewById(R.id.no_items_txt);
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

        commercialList = dbAdapter.getCommercialProperties();

        //check for empty lists
        if(commercialList.size() <= 0){
            no_itemsTXT.setVisibility(View.VISIBLE);
        }else{
            listView.setAdapter(new CommercialAdapter(context, commercialList));
            listView.setOnItemClickListener(listListener);
        }
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
