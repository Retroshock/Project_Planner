package com.example.adrian.git;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Adrian on 08.04.2017.
 */

public class DatabaseObject {
    private static Database dbHelper;
    private static SQLiteDatabase db;
    public DatabaseObject (Context context) {
        dbHelper = new Database(context);
        dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();
//        dbHelper.onCreate(this.getDbConnection());
    }
    public static SQLiteDatabase getDbConnection (){
        return db;
    }
    public static void closeDbConnection(){
        if (db != null)
            db.close();
    }

}
