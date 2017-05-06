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
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE evenimente" + " (" + "nume" + " TEXT, " +
                    "startDate" + " DATETIME, " +
                    "endDate" + " DATETIME, " +
                    "id" + " INTEGER, " +
                    "locatie" + " TEXT " +
                    ")";
    private static final String DATABASE_NAMES = "events";
    private static final int DATABASE_VERSION = 3;

    public SQLiteDatabase myDataBase;

    public Database(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);

    }

//    private void openDatabase() {
//        //Open the database
//        String myPath = DB_PATH + DB_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
//    }

//    public synchronized void close() {
//        if(myDataBase != null) {
//            myDataBase.close();
//        }
//        super.close();
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SQL_CREATE_ENTRIES);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        this.onCreate(db);
    }

//    public void createdatabase() throws IOException {
//        boolean dbexist = checkdatabase();
//        if(dbexist) {
//            System.out.println(" Database exists.");
//        } else {
//            myDataBase = this.getReadableDatabase();
//            boolean exists = checkdatabase();
//            try {
//                copydatabase();
//            } catch(IOException e) {
//                throw new Error("Error copying database");
//            }
//        }
//    }

//    private void copydatabase() throws IOException {
//        //Open your local db as the input stream
//        InputStream myinput = mycontext.getAssets().open(DB_NAME);
//
//        // Path to the just created empty db
//        String outfilename = DB_PATH + DB_NAME;
//
//        //Open the empty db as the output stream
//        OutputStream myoutput = new FileOutputStream(outfilename);
//
//        // transfer byte to inputfile to outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myinput.read(buffer))>0) {
//            myoutput.write(buffer,0,length);
//        }
//
//        //Close the streams
//        myoutput.flush();
//        myoutput.close();
//        myinput.close();
//    }

//    private boolean checkdatabase() {
//        boolean checkdb = false;
//        try {
//            String myPath = DB_PATH + DB_NAME;
//            File dbfile = new File(myPath);
//            checkdb = dbfile.exists();
//        } catch(SQLiteException e) {
//            System.out.println("Database doesn't exist");
//        }
//        return checkdb;
//
//    }
}
