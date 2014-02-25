package com.droid.keja.ui.frags;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.droid.keja.R;
import com.droid.keja.ui.MainActivity;

/**
 * Created by james on 17/02/14.
 */
public class SearchSale extends Fragment {

    private static final String LOG_TAG = "SearchSale";
    private Activity context;
    private Button search_toggle_BTN;
    private Button search_reset_BTN;
    private Button search_submit_BTN;
    private View section_search_extras;

    public SearchSale(){}

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.frag_search_sale, container, false);

        //init ui here
        search_toggle_BTN = (Button)rootView.findViewById(R.id.search_toggle_BTN);
        search_toggle_BTN.setOnClickListener(btnClickListener);
        search_reset_BTN = (Button)rootView.findViewById(R.id.search_reset_BTN);
        search_reset_BTN.setOnClickListener(btnClickListener);
        search_submit_BTN = (Button)rootView.findViewById(R.id.search_submit_BTN);
        search_submit_BTN.setOnClickListener(btnClickListener);
        section_search_extras = (RelativeLayout)rootView.findViewById(R.id.section_search_extras);
        section_search_extras.setOnClickListener(btnClickListener);

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

    }

    private View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.search_toggle_BTN: //toggle view visibility
                    if(section_search_extras.getVisibility() == View.GONE){
                        section_search_extras.setVisibility(View.VISIBLE);
                        search_toggle_BTN.setText(getString(R.string.search_hidemoreoptions_btn));
                    }
                    else if(section_search_extras.getVisibility() == View.VISIBLE){
                        section_search_extras.setVisibility(View.GONE);
                        search_toggle_BTN.setText(getString(R.string.search_moreoptions_btn));
                    }
                    break;

                case R.id.search_submit_BTN: //submit search query
//                    Fragment forSaleFrag = new ForSaleFrag();
//                    FragmentTransaction transaction = context.getFragmentManager().beginTransaction();
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.beginTransaction().add(new ForSaleFrag(), "For_Sale").commit();
                    break;

                default:break;
            }
        }
    };

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
