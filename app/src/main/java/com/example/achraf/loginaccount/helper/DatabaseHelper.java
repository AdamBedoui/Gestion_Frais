package com.example.achraf.loginaccount.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Achraf on 12/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Projet.db";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME="account_table";
    private static final String COL_1="ID";
    private static final String COL_2="USERNAME";
    private static final String COL_3="PASSWORD";

    public DatabaseHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);

    }



    public boolean insertData(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        long result =db.insert(TABLE_NAME,null,contentValues);

        return result != -1;
    }

    public Cursor getAllData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME,null);
    }


    //getSinlgeEntry:
    public String getSinlgeEntry(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String req= "select * from "+TABLE_NAME +" where "+ COL_2 +" = '" +username+"'";

        Cursor cursor=db.rawQuery(req,null);

// Cursor cursor=this.getReadableDatabase().query(TABLE_NAME, null, " USERNAME=?", new String[]{username}, null, null, null);

        // Username Not Exist
            if(cursor.getCount()<1)

        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(COL_3));
        cursor.close();
        return password;
    }

    public boolean IsThereUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String req= "select * from "+TABLE_NAME +" where "+ COL_2 +"='achraf'";
        Cursor cursor=db.rawQuery(req,null);

        if(cursor.getCount()<1)

        {
            cursor.close();
            return false;
        }
        return true;
    }


}
