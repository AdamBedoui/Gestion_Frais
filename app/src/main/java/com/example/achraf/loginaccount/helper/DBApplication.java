package com.example.achraf.loginaccount.helper;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.achraf.loginaccount.helper.DatabaseHelper;

/**
 *
 */

public class DBApplication extends Application {

    private DatabaseHelper db;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate() {

        db = new DatabaseHelper(getApplicationContext());

    }

    public DatabaseHelper getDB(){
        return db;
    }
}