package com.example.adrian.git;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Adrian on 08.04.2017.
 */

public class DatabaseObject {
    private static Database dbHelper;
    private SQLiteDatabase db;
    public DatabaseObject (Context context) {
        dbHelper = new Database(context);
        dbHelper.getWritableDatabase();
        this.db = dbHelper.getReadableDatabase();
//        dbHelper.onCreate(this.getDbConnection());
    }
    public SQLiteDatabase getDbConnection (){
        return this.db;
    }
    public void closeDbConnection(){
        if (this.db != null)
            this.db.close();
    }

}
