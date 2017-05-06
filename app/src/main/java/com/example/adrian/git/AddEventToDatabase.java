package com.example.adrian.git;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;

import com.example.adrian.git.Date.Eveniment;
import com.example.adrian.git.Date.EvenimentDinamic;
import com.example.adrian.git.Date.EvenimentStatic;

import java.util.Date;
import java.util.Locale;

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
        dbHelper.getWritableDatabase();
        this.db = dbHelper.getWritableDatabase();

        if (this.eveniment instanceof EvenimentDinamic){
            //TODO cod pentru tratat de evenimente dinamice
        }
        else{
            String query;
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm", Locale.ENGLISH);

            query = "INSERT INTO evenimente ( nume, startDate, endDate, id, locatie )" +
                    " VALUES ( '" + eveniment.getName() + "' , '" + sdf.format(eveniment.getStartDate()) + "' , '" +
                    sdf.format(eveniment.getEndDate()) + "' , " + String.valueOf(eveniment.getID()) +
                    ", '" + eveniment.getLocationToString() + "' );" ;
            StringBuilder stringBuilder = new StringBuilder();

            String pth = db.getPath();
            try{
                db.execSQL(query);
            } catch (SQLException e){
                System.out.println("exceptie SQL");

            }

        }


        //TODO aici trebuie sa introduc un element in BD - de facut!!!
//        db.execSQL(db.rawQuery("INSERT INTO reminders " +
//                "[(id, startDate, endDate, name, location, deadline, duration)] " );
    }
}
