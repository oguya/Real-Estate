package com.droid.keja.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.droid.keja.ui.frags.CommercialFrag;
import com.droid.keja.ui.frags.ForRentFrag;
import com.droid.keja.ui.frags.ForSaleFrag;
import com.droid.keja.ui.frags.SearchCommercial;
import com.droid.keja.ui.frags.SearchRent;
import com.droid.keja.ui.frags.SearchSale;

/**
 * Created by james on 17/02/14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private String[] tabsTitle;

    public TabsPagerAdapter(FragmentManager fragmentManager, String[] tabsTitle){
        super(fragmentManager);
        this.tabsTitle = tabsTitle;
    }

    @Override
    public Fragment getItem(int index) {
        switch (index){
            case 0: //search for homes for sale
                return new SearchSale();
            case 1: //search for homes for rent
                return new SearchRent();
            case 2: //search for commercial properties
                return new SearchCommercial();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        //no of tabs
        return tabsTitle.length;
    }
}
