package com.example.adrian.git;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adrian.git.Date.Eveniment;

/**
 * Created by Adrian on 21.04.2017.
 */

public class AddEventToDatabase {
    Eveniment eveniment;
    private static Database dbHelper;
    private SQLiteDatabase db;
    public AddEventToDatabase(Eveniment eveniment, Context context){
        this.eveniment = eveniment;
        dbHelper = new Database(context);
        this.db = dbHelper.getWritableDatabase();

        //TODO aici trebuie sa introduc un element in BD - de facut!!!
//        db.execSQL(db.rawQuery("INSERT INTO reminders " +
//                "[(id, startDate, endDate, name, location, deadline, duration)] " );
    }
}
