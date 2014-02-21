package com.droid.keja.utils;

import android.content.Context;
import android.util.Log;

import com.droid.keja.constants.Constants;
import com.droid.keja.db.DBUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by james on 21/02/14.
 */
public class FirstRunInit {
    private static final String LOG_TAG = "FirstRunInit";
    private Context context;
    private DBUtils dbUtils;

    public FirstRunInit(){}


    public FirstRunInit(Context context){
        this.context = context;
        this.dbUtils = new DBUtils(this.context);

    }

    public void copyDBFile(){
        File dbDir = new File(Constants.DB_DIR);

        dbDir.mkdirs();
        try {
            dbUtils.copyDB(Constants.DB_NAME, Constants.DB_DIR);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "unable to copy DB! "+e.getMessage());
        }
    }



}
