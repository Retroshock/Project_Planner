package com.example.adrian.git;

import android.database.Cursor;

/**
 * Created by Adrian on 23.05.2017.
 */

public class IDGen {
    public static long idCurent;
    public static void setIdCurent(){
        String query = "SELECT max(id) from evenimente";
        Cursor c = DatabaseObject.getDbConnection().rawQuery(query, null);
        if (c.moveToFirst()){
            idCurent = c.getLong(c.getColumnIndexOrThrow("id"));
        }
        c.close();
    }
    public static long getIdForEveniment(){
        return ++idCurent;
    }
}
