package com.example.adrian.git;

import android.content.Context;
import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by Adrian on 08.04.2017.
 */

class DatabaseQuery extends DatabaseObject{
    public DatabaseQuery(Context context) {
        super(context);
    }

    public List<EventObjects> getAllFutureEvents() {
        Date dateToday = new Date ();
        List<EventObjects> events = new ArrayList<>();
        String query = "select * from evenimente";
        //TODO  - DE MODIFICAT CA SA SELECTEZE CE NE TREBUIE -
        try {
            Cursor cursor = getDbConnection().rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String nume = cursor.getString(cursor.getColumnIndexOrThrow("nume"));
                    String startDate = cursor.getString(cursor.getColumnIndexOrThrow("startDate"));
                    //convert start date to date object
                    Date reminderDate = convertStringToDate(startDate);
                    if (reminderDate.after(dateToday) || reminderDate.equals(dateToday)) {
                        events.add(new EventObjects(id, nume, reminderDate));
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            return events;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Date convertStringToDate(String dateInString) {
        DateFormat format = new SimpleDateFormat("d-MM-yyyy", Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(dateInString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
}
