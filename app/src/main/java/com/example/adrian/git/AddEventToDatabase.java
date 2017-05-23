package com.example.adrian.git;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;

import com.example.adrian.git.Date.Eveniment;
import com.example.adrian.git.Date.EvenimentDinamic;

import java.util.Locale;

/**
 * Created by Adrian on 21.04.2017.
 */

public class AddEventToDatabase {
    private Eveniment eveniment;
    private static SQLiteDatabase db;

    public AddEventToDatabase(){

    }
    public static void add(Eveniment eveniment){
        db = DatabaseObject.getDbConnection();

        if (eveniment instanceof EvenimentDinamic){
            //TODO cod pentru tratat de evenimente dinamice
        }
        else{
            String query;
            SimpleDateFormat sdf = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);

            query = "INSERT INTO evenimente ( nume, startDate, endDate, id, locatie )" +
                    " VALUES ( '" + eveniment.getName() + "' , '" + sdf.format(eveniment.getStartDate()) + "' , '" +
                    sdf.format(eveniment.getEndDate()) + "' , " + String.valueOf(eveniment.getID()) +
                    ", '" + eveniment.getLocationToString() + "' );" ;
//            StringBuilder stringBuilder = new StringBuilder();

//            String pth = db.getPath();
            try{
                db.execSQL(query);
            } catch (SQLException e){
                System.out.println("exceptie SQL");

            }

        }


    }
}
