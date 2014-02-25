package com.droid.keja.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.webkit.WebChromeClient;

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

        //force foreign checks
        db.execSQL(Constants.FORCE_FOREIGN_KEY_CHECKS);
        return this;
    }

    public void close(){
        db.close();
    }

    //return all homes for rent
    public ArrayList<HomeRent> getHomesRent(){
        ArrayList<HomeRent> homeRentList = new ArrayList<HomeRent>();
        String sql = "SELECT * FROM "+ Constants.TBL_HOMES+" where for_sale = 0";
        Cursor cursor;
        try{
            cursor = db.rawQuery(sql, null);
        }catch (SQLiteException ex){
            Log.e(LOG_TAG, "exception in SQL: "+sql+". "+ex.getMessage());
            return homeRentList;
        }

        if(cursor.moveToFirst()){
            do{
                HomeRent homeRent = new HomeRent();

                homeRent.setName(cursor.getString(cursor.getColumnIndex("home_name")));
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
        String sql = "SELECT * FROM "+ Constants.TBL_HOMES+" where for_sale = 1";
        Cursor cursor;
        try{
            cursor = db.rawQuery(sql, null);
        }catch (SQLiteException ex){
            Log.e(LOG_TAG, "exception in SQL: "+sql+". "+ex.getMessage());
            return homeSaleList;
        }

        if(cursor.moveToFirst()){
            do{
                HomeSale homeSale = new HomeSale();

                homeSale.setName(cursor.getString(cursor.getColumnIndex("home_name")));
                homeSale.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                homeSale.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                homeSale.setBedrooms(cursor.getString(cursor.getColumnIndex("bedrooms")));
                homeSale.setBaths(cursor.getString(cursor.getColumnIndex("baths")));
                homeSale.setType(cursor.getString(cursor.getColumnIndex("type")));
                homeSale.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                homeSale.setThumbnail(cursor.getString(cursor.getColumnIndex("thumbnail")));
                homeSale.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                homeSale.setLng(cursor.getString(cursor.getColumnIndex("lng")));

                homeSaleList.add(homeSale);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return homeSaleList;
    }


    //return all homes for rent
    public ArrayList<Commercial> getCommercialProperties(){
        ArrayList<Commercial> commercialList = new ArrayList<Commercial>();
        String sql = "SELECT * FROM "+Constants.TBL_COMMERCIAL;
        Cursor cursor;
        try{
            cursor = db.rawQuery(sql, null);
        }catch (SQLiteException ex){
            Log.e(LOG_TAG, "exception in SQL: "+sql+". "+ex.getMessage());
            return commercialList;
        }

        if (cursor.moveToFirst()){
            do{
                Commercial commercial = new Commercial();

                commercial.setName(cursor.getString(cursor.getColumnIndex("name")));
                commercial.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                commercial.setSize(cursor.getString(cursor.getColumnIndex("size")));
                commercial.setType(cursor.getString(cursor.getColumnIndex("type")));

                commercialList.add(commercial);
            }while(cursor.moveToNext());
        }
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
