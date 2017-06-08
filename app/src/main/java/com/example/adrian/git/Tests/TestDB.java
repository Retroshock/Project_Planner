package com.example.adrian.git.Tests;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adrian.git.DatabaseObject;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Adrian on 08.06.2017.
 */

public class TestDB {
    public void TestDB(){
        SQLiteDatabase db = DatabaseObject.getDbConnection();
        boolean ok = true;
        try {
            String[] args =null;
            Cursor c= db.rawQuery("Select * from sleep", args);
            if (!c.moveToFirst()){
                ok = false;
            }

        } catch (Exception e){
            ok = false;
        }
        System.out.println("Baza de date buna!");
        assertEquals(ok, true);
    }

}
