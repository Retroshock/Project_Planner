package com.example.adrian.git;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Adrian on 08.04.2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS evenimente" ;
    private static final String SQL_CREATE_EVENIMENTE_ENTRIES =
            "CREATE TABLE evenimente" + " (" + "nume" + " TEXT, " +
                    "startDate" + " DATETIME, " +
                    "endDate" + " DATETIME, " +
                    "id" + " INTEGER, " +
                    "locatie" + " TEXT " +
                    ")";
    private static final String SQL_CREATE_SLEEP_ENTRIES =
            "CREATE TABLE sleep" + " (" + "getUp" + " TEXT, " +
                    "sleepTime" + " TEXT" +
                    ")";
    private static final String DATABASE_NAMES = "events";
    private static final int DATABASE_VERSION = 3;


    public Database(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SQL_CREATE_EVENIMENTE_ENTRIES);
       db.execSQL(SQL_CREATE_SLEEP_ENTRIES);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        this.onCreate(db);
    }


}
