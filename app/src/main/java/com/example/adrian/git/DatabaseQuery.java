package com.example.adrian.git;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.example.adrian.git.Date.Eveniment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public List<Eveniment> getAllFutureEvents() {

        Date dateToday = new Date ();
        List<Eveniment> events = new ArrayList<>();
        String query = "select * from evenimente";
        //TODO  - DE MODIFICAT CA SA SELECTEZE CE NE TREBUIE -
        try {
            Cursor cursor = getDbConnection().rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String nume = cursor.getString(cursor.getColumnIndexOrThrow("nume"));
                    String startDate = cursor.getString(cursor.getColumnIndexOrThrow("startDate"));
                    String endDate = cursor.getString(cursor.getColumnIndexOrThrow("endDate"));
                    //convert start date to date object
                    Date reminderDate = convertStringToDate(startDate);
                    Eveniment ev = new Eveniment();
                    ev.setStartDate( convertStringToDate(startDate));
                    ev.setEndDate(convertStringToDate(endDate));
                    ev.setName(nume);
                    if (reminderDate.after(dateToday) || reminderDate.equals(dateToday)) {
                        events.add(ev);
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

    public void addSleepEventsToDB(List <Eveniment> events) {
        String getUp, sleep, query;
        query = "SELECT * FROM sleep;";
        Cursor cursor = DatabaseObject.getDbConnection().rawQuery(query, null);
        if (cursor.moveToFirst()){
            getUp = cursor.getString(cursor.getColumnIndexOrThrow("getUp"));
            sleep = cursor.getString(cursor.getColumnIndexOrThrow("sleepTime"));
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            Eveniment ev = new Eveniment();
            try {
                ev.setStartDate(sdf.parse(getUp));
                ev.setEndDate(sdf.parse(sleep));
                ev.setName("SLEEP");
                Date tempDate = events.get(0).getStartDate();
                GregorianCalendar gcal = new GregorianCalendar();
                gcal.setTime(tempDate);
                while (gcal.getTime().before(events.get(events.size()-1).getEndDate())){
                    Calendar c = Calendar.getInstance();
                    c.setTime(ev.getEndDate());
                    gcal.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
                    gcal.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
                    Date tempStDate = gcal.getTime();

                    c = Calendar.getInstance();
                    c.setTime(ev.getStartDate());
                    gcal.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
                    gcal.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
                    gcal.add(Calendar.DAY_OF_MONTH,1);
                    Date tempEndDate = gcal.getTime();


                    Eveniment sleepEvent = new Eveniment();
                    sleepEvent.setStartDate(tempStDate);
                    sleepEvent.setEndDate(tempEndDate);
                    sleepEvent.setName(ev.getName());

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);

                    String verifyExistQuery = "SELECT * from evenimente WHERE startDate = '" + simpleDateFormat.format(sleepEvent.getStartDate()) + "' " +
                            "and nume = 'SLEEP'";

                    Cursor cursor1 = DatabaseObject.getDbConnection().rawQuery(verifyExistQuery, null);
                    if (!cursor1.moveToFirst()) {
                        String query1 = "INSERT INTO evenimente (nume, startDate, endDate) VALUES " +
                                "('" + sleepEvent.getName() + "', '" + simpleDateFormat.format(sleepEvent.getStartDate()) + "', '" +
                                simpleDateFormat.format(sleepEvent.getEndDate()) + "')";
                        DatabaseObject.getDbConnection().execSQL(query1);
                    }
                    cursor1.close();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("eroare la inserare de somn");
            }
        }
        cursor.close();
    }

    private Date convertStringToDate(String dateInString) {
        DateFormat format = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(dateInString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
}
