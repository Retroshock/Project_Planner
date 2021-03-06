package com.example.adrian.git;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrian.git.AddActivityFragments.DatePickerFragment;
import com.example.adrian.git.AddActivityFragments.DurationPickerFragment;
import com.example.adrian.git.AddActivityFragments.TimePickerFragment;
import com.example.adrian.git.Date.Eveniment;
import com.example.adrian.git.Date.EvenimentDinamic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    private EditText nume;
    private TextView stDate;
    private TextView endDate;
    private boolean dinamic;
    private CheckBox notificatie;
    private EditText nota;
    private EditText locatie;

    private int[] start;
    private int[] end;
    private int[] durata;
    private int[] deadline;
    private Calendar startCal;
    private Calendar endCal;
    private Calendar deadCal;
    private Eveniment ev;

    public static final String BOOL1 = "com.example.adrian.git.bool1";
    public static final String BOOL2 = "com.example.adrian.git.bool2";
    public static final String DATA_KEY = "com.example.adrian.git.data";
    public static final String DURATA_KEY = "com.example.adrian.git.durata";
    public static final String EVT_KEY = "com.example.adrian.git.eveniment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddAct);
        stDate = (TextView) findViewById(R.id.stDateAddAct);
        endDate = (TextView) findViewById(R.id.endDateAddAct);
        nume = (EditText) findViewById(R.id.numeAddAct);
        notificatie = (CheckBox) findViewById(R.id.notificatieAddAct);
        nota = (EditText) findViewById(R.id.notaAddAct);
        locatie = (EditText) findViewById(R.id.locatieAddAct);
        setSupportActionBar(toolbar);
        startCal = Calendar.getInstance();
        endCal = Calendar.getInstance();
        deadCal = Calendar.getInstance();
        Intent intent = getIntent();
        if(intent != null)
            ev = (Eveniment) intent.getSerializableExtra(EVT_KEY);
        if(ev != null) {
            nume.setText(ev.getName());
            locatie.setText(ev.getLocatie());
            SimpleDateFormat sdf = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
            stDate.setText(sdf.format(ev.getStartDate()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ev.getStartDate());
            start = new int[5];
            start[0] = calendar.get(Calendar.YEAR);
            start[1] = calendar.get(Calendar.MONTH);
            start[2] = calendar.get(Calendar.DAY_OF_MONTH);
            start[3] = calendar.get(Calendar.HOUR_OF_DAY);
            start[4] = calendar.get(Calendar.MINUTE);
            endDate.setText(sdf.format(ev.getEndDate()));
            calendar.setTime(ev.getEndDate());
            end = new int[5];
            end[0] = calendar.get(Calendar.YEAR);
            end[1] = calendar.get(Calendar.MONTH);
            end[2] = calendar.get(Calendar.DAY_OF_MONTH);
            end[3] = calendar.get(Calendar.HOUR_OF_DAY);
            end[4] = calendar.get(Calendar.MINUTE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m)
    {
        MenuInflater inflater = getMenuInflater();
        if(ev != null)
            inflater.inflate(R.menu.add_activity_menu_save, m);
        else
            inflater.inflate(R.menu.add_activity_menu, m);
        return true;
    }

    public void changeType(View v)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
        CheckBox src = (CheckBox) v;
        if(src.isChecked())
        {
            src.setText(R.string.static2_add_act);
            if(durata == null)
                stDate.setText(R.string.st_date2_add_act);
            else
            {
                String text = "Duration: " + Integer.toString(durata[0]) + " hours and "
                        + Integer.toString(durata[1]) + " minutes";
                stDate.setText(text);
            }
            if(deadline == null)
                endDate.setText(R.string.end_date2_add_act);
            else
            {
                String text = "Until ";
                deadCal.set(deadline[0], deadline[1], deadline[2], deadline[3], deadline[4]);
                text += sdf.format(deadCal.getTime());
                endDate.setText(text);
            }
            dinamic = true;
        }
        else
        {
            src.setText(R.string.static_add_act);
            if(start == null)
                stDate.setText(R.string.st_date_add_act);
            else
            {
                String text = "From ";
                startCal.set(start[0], start[1], start[2], start[3], start[4]);
                text += sdf.format(startCal.getTime());
                stDate.setText(text);
            }
            if(end == null)
                endDate.setText(R.string.end_date_add_act);
            else
            {
                String text = "To ";
                endCal.set(end[0], end[1], end[2], end[3], end[4]);
                text += sdf.format(endCal.getTime());
                endDate.setText(text);
            }
            dinamic = false;
        }
    }

    public void chooseDate(View v)
    {
        int id = v.getId();
        switch(id)
        {
            case R.id.stDateAddAct:
            {
                if(dinamic)
                {
                    DialogFragment dialog = new DurationPickerFragment();
                    if(durata != null)
                    {
                        Bundle b = new Bundle();
                        b.putIntArray(DURATA_KEY, durata);
                        dialog.setArguments(b);
                    }
                    dialog.show(getFragmentManager(), "Pick the duration");
                }
                else
                {
                    Bundle b = new Bundle();
                    b.putBoolean(BOOL1, false);
                    b.putBoolean(BOOL2, false);
                    DialogFragment dialog = new DatePickerFragment();
                    if(start != null)
                        b.putIntArray(DATA_KEY, start);
                    dialog.setArguments(b);
                    dialog.show(getFragmentManager(), "Pick the date");
                }
                break;
            }
            case R.id.endDateAddAct:
            {
                if(dinamic)
                {
                    Bundle b = new Bundle();
                    b.putBoolean(BOOL1, true);
                    b.putBoolean(BOOL2, true);
                    DialogFragment dialog = new DatePickerFragment();
                    if(deadline != null)
                        b.putIntArray(DATA_KEY, deadline);
                    dialog.setArguments(b);
                    dialog.show(getFragmentManager(), "Pick the date");
                }
                else
                {
                    Bundle b = new Bundle();
                    b.putBoolean(BOOL1, true);
                    b.putBoolean(BOOL2, false);
                    DialogFragment dialog = new DatePickerFragment();
                    if(end != null)
                        b.putIntArray(DATA_KEY, end);
                    dialog.setArguments(b);
                    dialog.show(getFragmentManager(), "Pick the date");
                }
                break;
            }
            default:
            {
                break;
            }
        }
    }

    public void alegeData(int an, int luna, int zi, boolean b1, boolean b2, Bundle b3)
    {
        if(!b1 && !b2)
        {
            start = new int[5];
            start[0] = an;
            start[1] = luna;
            start[2] = zi;
        }
        if(b1 && !b2)
        {
            end = new int[5];
            end[0] = an;
            end[1] = luna;
            end[2] = zi;
        }
        if(b1 && b2)
        {
            deadline = new int[5];
            deadline[0] = an;
            deadline[1] = luna;
            deadline[2] = zi;
        }
        DialogFragment dialog = new TimePickerFragment();
        Bundle b = new Bundle();
        b.putBoolean(BOOL1, b1);
        b.putBoolean(BOOL2, b2);
        b.putAll(b3);
        dialog.setArguments(b);
        dialog.show(getFragmentManager(), "Pick the time");
    }

    public void alegeTimpul(int ore, int minute, boolean b1, boolean b2)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
        if(!b1 && !b2)
        {
            start[3] = ore;
            start[4] = minute;
            String text = "From ";
            startCal.set(start[0], start[1], start[2], start[3], start[4]);
            text += sdf.format(startCal.getTime());
            /*+ Integer.toString(start[2]) + "." + Integer.toString(start[1] + 1)
                    + "." + Integer.toString(start[0]) + " " + Integer.toString(start[3])
                    + ":" + Integer.toString(start[4]);*/
            stDate.setText(text);
        }
        if(b1 && !b2)
        {
            end[3] = ore;
            end[4] = minute;
            String text = "To ";
            endCal.set(end[0], end[1], end[2], end[3], end[4]);
            text += sdf.format(endCal.getTime());
            /*+ Integer.toString(end[2]) + "." + Integer.toString(end[1] + 1)
                    + "." + Integer.toString(end[0]) + " " + Integer.toString(end[3])
                    + ":" + Integer.toString(end[4]);*/
            endDate.setText(text);
        }
        if(b1 && b2)
        {
            deadline[3] = ore;
            deadline[4] = minute;
            String text = "Until ";
            deadCal.set(deadline[0], deadline[1], deadline[2], deadline[3], deadline[4]);
            text += sdf.format(deadCal.getTime());
            /*+ Integer.toString(deadline[2]) + "." + Integer.toString(deadline[1] + 1)
                    + "." + Integer.toString(deadline[0]) + " " + Integer.toString(deadline[3])
                    + ":" + Integer.toString(deadline[4]);*/
            endDate.setText(text);
        }
    }

    public void alegeDurata(int ore, int minute)
    {
        durata = new int[2];
        durata[0] = ore;
        durata[1] = minute;
        String text = "Duration: " + Integer.toString(durata[0]) + " hours and "
                + Integer.toString(durata[1]) + " minutes";
        stDate.setText(text);
    }

    public void stergeDurata()
    {
        durata = null;
        stDate.setText(R.string.st_date2_add_act);
    }

    public void stergeData(boolean b1, boolean b2)
    {
        if(!b1 && !b2)
        {
            start = null;
            stDate.setText(R.string.st_date_add_act);
        }
        if(b1 && !b2)
        {
            end = null;
            endDate.setText(R.string.end_date_add_act);
        }
        if(b1 && b2)
        {
            deadline = null;
            endDate.setText(R.string.end_date2_add_act);
        }
    }

    public void salveaza(MenuItem m)
    {
        /*TODO adauga un eveniment facut de voi (backend) cu datele de aici (null daca nu exista)
        start[5] pentru startdate - an, luna, zi, ora, minut
        end[5] pentru enddate - la fel
        durata[2] pentru durata - ore, minute
        deadline[5] pentru deadline - la fel ca celelalte date
        dinamic - boolean, fals pentru static, true pentru dinamic
        notificatie - trebuie isChecked()
        nume - getText().toString(), verificare sa nu fie gol
        nota, locatie - la fel ca nume, dar probabil optionale
        */

        if (dinamic) {
            EvenimentDinamic ev;
            ev = new EvenimentDinamic();
            //ev.setDuration();
        }
        else {
            Eveniment ev;
            ev = new Eveniment();
            ev.setStartDate(convertArrToDateStr(start));
            ev.setEndDate(convertArrToDateStr(end));
            ev.setName(nume.getText().toString());
            ev.setID(IDGen.idCurent);
            ev.setLocatie(locatie.getText().toString());
            AddEventToDatabase.add(ev, this);
            Toast.makeText(this, "Eveniment " + ev.getName() + " Adaugat", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private Date convertArrToDateStr (int[] date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
        String result,day,month,year,minute,hour;
        year = String.valueOf(date[0]);
        month = String.valueOf(date[1] + 1);
        day = String.valueOf(date[2]);
        hour = String.valueOf(date[3]);
        minute = String.valueOf(date[4]);
        result = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        try {
            return simpleDateFormat.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void salveazaSchimbarile(MenuItem m)
    {
        //TODO salveaza modificarile (pastrate in aceleasi variabile ca pentru salvarea normala)
        Eveniment ev;
        ev = new Eveniment();
        ev.setStartDate(convertArrToDateStr(start));
        ev.setEndDate(convertArrToDateStr(end));
        ev.setName(nume.getText().toString());
        ev.setID(IDGen.idCurent);
        ev.setLocatie(locatie.getText().toString());
        String[] arg = {ev.getName()};
        DatabaseObject.getDbConnection().delete("evenimente","nume = ?" , arg);
        AddEventToDatabase.add(ev, this);
        Toast.makeText(this, "Evenimentul " + ev.getName() + " modificat", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void sterge(MenuItem m)
    {
        //TODO sterge evenimentul ev (pastrat ca variabila cu acest nume)
        Eveniment ev;
        ev = new Eveniment();
        ev.setStartDate(convertArrToDateStr(start));
        ev.setEndDate(convertArrToDateStr(end));
        ev.setName(nume.getText().toString());
        ev.setID(IDGen.idCurent);
        ev.setLocatie(locatie.getText().toString());
        String[] arg = {ev.getName()};
        DatabaseObject.getDbConnection().delete("evenimente","nume = ?" , arg);
        finish();
    }
}
