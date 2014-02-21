package com.droid.keja.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.droid.keja.constants.Constants;
import com.droid.keja.model.Commercial;
import com.droid.keja.model.HomeRent;
import com.droid.keja.model.HomeSale;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by james on 20/02/14.
 */
public class DBAdapter {
    private static final int DB_VERSION = 1;
    private static final String LOG_TAG = "dbAdapter";

    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBAdapter(Context _context){
        this.context = _context;

        dbHelper = new DBHelper(context);
    }

    public DBAdapter open(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    //return all homes for rent
    public ArrayList<HomeRent> getHomesRent(){
        ArrayList<HomeRent> homeRentList = new ArrayList<HomeRent>();
        String sql = "SELECT * FROM "+ Constants.TBL_HOMES;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                HomeRent homeRent = new HomeRent();

                homeRent.setName(cursor.getString(cursor.getColumnIndex("name")));
                homeRent.setRent(cursor.getString(cursor.getColumnIndex("rent")));
                homeRent.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                homeRent.setBedrooms(cursor.getString(cursor.getColumnIndex("bedrooms")));
                homeRent.setBaths(cursor.getString(cursor.getColumnIndex("baths")));
                homeRent.setType(cursor.getString(cursor.getColumnIndex("type")));
                homeRent.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                homeRent.setThumbnail(cursor.getString(cursor.getColumnIndex("thumbnail")));
                homeRent.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                homeRent.setLng(cursor.getString(cursor.getColumnIndex("lng")));

                homeRentList.add(homeRent);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return homeRentList;
    }

    //return all homes for sale
    public ArrayList<HomeSale> getHomesSale(){
        ArrayList<HomeSale> homeSaleList = new ArrayList<HomeSale>();

        return homeSaleList;
    }


    //return all homes for rent
    public ArrayList<Commercial> getCommercialProperties(){
        ArrayList<Commercial> commercialList = new ArrayList<Commercial>();

        return commercialList;
    }


    public static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context, Constants.DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
