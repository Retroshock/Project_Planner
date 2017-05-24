package com.example.adrian.git;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.adrian.git.Date.Eveniment;

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

    private static final String BOOL1 = "com.example.adrian.git.bool1";
    private static final String BOOL2 = "com.example.adrian.git.bool2";
    private static final String DATA_KEY = "com.example.adrian.git.data";
    private static final String DURATA_KEY = "com.example.adrian.git.durata";

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        boolean b1, b2;
        Bundle b;

        private class MyDatePickerDialog extends DatePickerDialog
        {

            public MyDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
                super(context, listener, year, month, dayOfMonth);
            }

            public void onClick(@NonNull DialogInterface dialog, int which)
            {
                if(which == DialogInterface.BUTTON_NEGATIVE)
                {
                    ((AddActivity) getActivity()).stergeData(b1, b2);
                    dialog.cancel();
                }
                else
                    super.onClick(dialog, which);
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            b = getArguments();
            b1 = b.getBoolean(BOOL1);
            b2 = b.getBoolean(BOOL2);
            int year, month, day;
            int[] data = b.getIntArray(DATA_KEY);
            if(data != null)
            {
                year = data[0];
                month = data[1];
                day = data[2];
            }
            else
            {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
            }
            return new MyDatePickerDialog(getActivity(), this, year, month, day);

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ((AddActivity) getActivity()).alegeData(year, month, dayOfMonth, b1, b2, b);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        boolean b1, b2;

        private class MyTimePickerDialog extends TimePickerDialog
        {

            MyTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
                super(context, listener, hourOfDay, minute, is24HourView);
            }

            public void onClick(DialogInterface dialog, int which)
            {
                if(which == DialogInterface.BUTTON_NEGATIVE)
                {
                    ((AddActivity) getActivity()).stergeData(b1, b2);
                    dialog.cancel();
                }
                else
                    super.onClick(dialog, which);
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle b = getArguments();
            b1 = b.getBoolean(BOOL1);
            b2 = b.getBoolean(BOOL2);
            int hour, minute;
            int[] data = b.getIntArray(DATA_KEY);
            if(data != null)
            {
                hour = data[3];
                minute = data[4];
            }
            else
            {
                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
            }
            return new MyTimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ((AddActivity) getActivity()).alegeTimpul(hourOfDay, minute, b1, b2);
        }

    }

    public static class DurationPickerFragment extends DialogFragment
            implements DialogInterface.OnClickListener {

        private EditText hours;
        private EditText minutes;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.fragment_duration_picker, null);
            builder.setView(v);
            hours = (EditText) v.findViewById(R.id.hoursDurationDialFrag);
            minutes = (EditText) v.findViewById(R.id.minutesDurationDialFrag);
            Bundle b = getArguments();
            if(b != null)
            {
                int[] durata = b.getIntArray(DURATA_KEY);
                hours.setText(Integer.toString(durata[0]));
                minutes.setText(Integer.toString(durata[1]));
            }
            return builder.setPositiveButton(R.string.save, this)
                    .setNegativeButton(R.string.cancel, this)
                    .create();
        }
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which)
            {
                case DialogInterface.BUTTON_POSITIVE:
                {
                    int ore, minute;
                    String h = hours.getText().toString();
                    String m = minutes.getText().toString();
                    if(h.isEmpty())
                        ore = 0;
                    else
                        ore = Integer.parseInt(h);
                    if(m.isEmpty())
                        minute = 0;
                    else
                        minute = Integer.parseInt(m);
                    ((AddActivity) getActivity()).alegeDurata(ore, minute);
                    break;
                }
                case DialogInterface.BUTTON_NEGATIVE:
                {
                    ((AddActivity) getActivity()).stergeDurata();
                    dialog.cancel();
                    break;
                }
            }
        }
    }

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_activity_menu, m);
        return true;
    }

    public void changeType(View v)
    {
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
                String text = "Until " + Integer.toString(deadline[2]) + "." + Integer.toString(deadline[1] + 1)
                        + "." + Integer.toString(deadline[0]) + " " + Integer.toString(deadline[3])
                        + ":" + Integer.toString(deadline[4]);
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
                String text = "From " + Integer.toString(start[2]) + "." + Integer.toString(start[1] + 1)
                        + "." + Integer.toString(start[0]) + " " + Integer.toString(start[3])
                        + ":" + Integer.toString(start[4]);
                stDate.setText(text);
            }
            if(end == null)
                endDate.setText(R.string.end_date_add_act);
            else
            {
                String text = "To " + Integer.toString(end[2]) + "." + Integer.toString(end[1] + 1)
                        + "." + Integer.toString(end[0]) + " " + Integer.toString(end[3])
                        + ":" + Integer.toString(end[4]);
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
        if(!b1 && !b2)
        {
            start[3] = ore;
            start[4] = minute;
            String text = "From " + Integer.toString(start[2]) + "." + Integer.toString(start[1] + 1)
                    + "." + Integer.toString(start[0]) + " " + Integer.toString(start[3])
                    + ":" + Integer.toString(start[4]);
            stDate.setText(text);
        }
        if(b1 && !b2)
        {
            end[3] = ore;
            end[4] = minute;
            String text = "To " + Integer.toString(end[2]) + "." + Integer.toString(end[1] + 1)
                    + "." + Integer.toString(end[0]) + " " + Integer.toString(end[3])
                    + ":" + Integer.toString(end[4]);
            endDate.setText(text);
        }
        if(b1 && b2)
        {
            deadline[3] = ore;
            deadline[4] = minute;
            String text = "Until " + Integer.toString(deadline[2]) + "." + Integer.toString(deadline[1] + 1)
                    + "." + Integer.toString(deadline[0]) + " " + Integer.toString(deadline[3])
                    + ":" + Integer.toString(deadline[4]);
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
        Eveniment ev = new Eveniment();
        ev.setStartDate(convertArrToDateStr(start));
        ev.setEndDate(convertArrToDateStr(end));
        ev.setName(nume.getText().toString());
        ev.setID(IDGen.idCurent);
        //ev.setLocatie();
        AddEventToDatabase.add(ev);
        Toast.makeText(this, "Eveniment " + ev.getName() + " Adaugat", Toast.LENGTH_SHORT).show();
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

}
