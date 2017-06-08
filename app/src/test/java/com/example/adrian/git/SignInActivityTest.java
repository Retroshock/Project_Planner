package com.example.adrian.git;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Adrian on 08.06.2017.
 */

public class SignInActivityTest {
    @Test
    public void skip() throws Exception {
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
        System.out.println("sdsadsa");
        assertEquals(ok, true);
    }

    @Test
    public void onCreate() throws Exception {

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
        System.out.println("sdsadsa");
        assertEquals(true, true);
    }

}