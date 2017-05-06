package com.example.adrian.git;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.adrian.git.Date.Eveniment;
import com.example.adrian.git.Date.EvenimentDinamic;
import com.example.adrian.git.Date.EvenimentStatic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import static com.example.adrian.git.BundleKeys.*;

public class AddActivity extends AppCompatActivity
        implements DurationPickerFragment.DurationPickerListener, InputConfirmFragment.InputConfirmListener{

    private boolean stDate1Set = false;
    private boolean stDate2Set = false;
    private boolean endDate1Set = false;
    private boolean endDate2Set = false;
    private boolean deadline1Set = false;
    private boolean deadline2Set = false;
    private boolean durationSet = false;
    private int stDate[];
    private int endDate[];
    private int deadline[];
    private int duration[];
    private EditText nume;
    private CheckBox dinamic;
    private static final String ID_KEY = "id";

    //public static final String REZULTAT_Eveniment = "com.example.adrian.git.Rezultat";

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        private int sourceId;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle b = getArguments();
            sourceId = b.getInt(AddActivity.ID_KEY);
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ((AddActivity) getActivity()).dateSet(year, month, dayOfMonth, sourceId);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        private int sourceId;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle b = getArguments();
            sourceId = b.getInt(AddActivity.ID_KEY);
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ((AddActivity) getActivity()).timeSet(hourOfDay, minute, sourceId);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nume = (EditText) findViewById(R.id.numeAddAct);
        dinamic = (CheckBox) findViewById(R.id.dinamicAddAct);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddAct);
        stDate = new int[5];
        endDate = new int[5];
        deadline = new int[5];
        duration = new int[2];
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stDate1Set && stDate2Set && endDate1Set && endDate2Set && durationSet && deadline1Set && deadline2Set) {
                    DialogFragment inputConfirm = new InputConfirmFragment();
                    Bundle b = new Bundle();
                    if (!dinamic.isChecked()) {
                        b.putString(NAME_KEY, nume.getText().toString());
                        b.putIntArray(ST_DATE_KEY, stDate);
                        b.putIntArray(END_DATE_KEY, endDate);
                        b.putBoolean(TYPE_KEY, false);
                    } else {
                        b.putString(NAME_KEY, nume.getText().toString());
                        b.putIntArray(DURATION_KEY, duration);
                        b.putIntArray(DEADLINE_KEY, deadline);
                        b.putBoolean(TYPE_KEY, true);
                    }
                    inputConfirm.setArguments(b);
                    inputConfirm.show(getFragmentManager(), "Confirm?");
                }
            }
        });
    }

    @Override
    public void onDurationPickerPositiveClick(DialogFragment dialog, int hours, int minutes) {
        duration[0] = hours;
        duration[1] = minutes;
        durationSet = true;
    }

    @Override
    public void onDurationPickerNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    @Override
    public void onInputConfirmPositiveClick(DialogFragment dialog, Bundle b, boolean obl) {
        boolean tip = b.getBoolean(TYPE_KEY);
        //TODO de adaugat id-uri la fiecare eveniment in parte - Robert

        if(!tip)
        {
            Eveniment evStatic = new EvenimentStatic();
            int stDate[] = b.getIntArray(ST_DATE_KEY);
            int endDate[] = b.getIntArray(END_DATE_KEY);
            if(stDate == null || endDate == null)
            {
                Toast.makeText(getApplicationContext(), "Start date sau end date null", Toast.LENGTH_LONG).show();
                return;
            }
            String name = b.getString(NAME_KEY);
            Calendar start = new GregorianCalendar(stDate[0], stDate[1], stDate[2], stDate[3], stDate[4]);
            Calendar end = new GregorianCalendar(endDate[0], endDate[1], endDate[2], endDate[3], endDate[4]);
            evStatic.setStartDate(start.getTime());
            evStatic.setEndDate(end.getTime());
            evStatic.setName(name);
            AddEventToDatabase evToDb = new AddEventToDatabase(evStatic, this.getApplicationContext());

        }
        else
        {
            /*TODO AIci trebuie sa creeze o instanta a unei clase de management al evenimentelor dinamice
                Astea nu se adauga direct in BD, ci se trimit catre clasa aia ^
            */
            EvenimentDinamic evDinamic = new EvenimentDinamic();
            int duration[] = b.getIntArray(DURATION_KEY);
            int deadline[] = b.getIntArray(DEADLINE_KEY);
            if(duration == null || deadline == null)
            {
                Toast.makeText(getApplicationContext(), "Duration sau deadline null", Toast.LENGTH_LONG).show();
                return;
            }
            String name = b.getString(NAME_KEY);
            DatatypeFactory df = new DatatypeFactory() {
                @Override
                public Duration newDuration(String lexicalRepresentation) {
                    return null;
                }

                @Override
                public Duration newDuration(long durationInMilliSeconds) {
                    return null;
                }

                @Override
                public Duration newDuration(boolean isPositive, BigInteger years, BigInteger months, BigInteger days, BigInteger hours, BigInteger minutes, BigDecimal seconds) {
                    return null;
                }

                @Override
                public XMLGregorianCalendar newXMLGregorianCalendar() {
                    return null;
                }

                @Override
                public XMLGregorianCalendar newXMLGregorianCalendar(String lexicalRepresentation) {
                    return null;
                }

                @Override
                public XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar cal) {
                    return null;
                }

                @Override
                public XMLGregorianCalendar newXMLGregorianCalendar(BigInteger year, int month, int day, int hour, int minute, int second, BigDecimal fractionalSecond, int timezone) {
                    return null;
                }
            };
            Duration dura = df.newDuration(true, 0, 0, 0, duration[0], duration[1], 0);
            Calendar dead = new GregorianCalendar(deadline[0], deadline[1], deadline[2], deadline[3], deadline[4]);
            evDinamic.setName(name);
            evDinamic.setDuration(dura);
            evDinamic.setDeadline(dead.getTime());
            //AddEventToDatabase evToDb = new AddEventToDatabase(evDinamic, this.getApplicationContext());

        }



        b.putBoolean(OBLIGATORIU_KEY, obl);


        finish();
    }



    @Override
    public void onInputConfirmNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    public void pickDate(View v)
    {
        int id = v.getId();
        Bundle b = new Bundle();
        b.putInt(ID_KEY, id);
        DialogFragment dialog = new DatePickerFragment();
        dialog.setArguments(b);
        dialog.show(getFragmentManager(), "Pick the date");
    }

    public void pickTime(View v)
    {
        int id = v.getId();
        Bundle b = new Bundle();
        b.putInt(ID_KEY, id);
        DialogFragment dialog = new TimePickerFragment();
        dialog.setArguments(b);
        dialog.show(getFragmentManager(), "Pick the time");
    }
    public void pickDuration(View v)
    {
        DialogFragment dialog = new DurationPickerFragment();
        dialog.show(getFragmentManager(), "Pick the duration");
    }

    private void dateSet(int year, int month, int dayOfMonth, int id)
    {
        switch(id)
        {
            case R.id.stDtAddAct:
            {
                stDate[2] = dayOfMonth;
                stDate[1] = month;
                stDate[0] = year;
                stDate1Set = true;
                break;
            }
            case R.id.endDtAddAct:
            {
                endDate[2] = dayOfMonth;
                endDate[1] = month;
                endDate[0] = year;
                endDate1Set = true;
                break;
            }
            case R.id.dlDtAddAct:
            {
                deadline[2] = dayOfMonth;
                deadline[1] = month;
                deadline[0] = year;
                deadline1Set = true;
                break;
            }
            default:
            {
                Toast.makeText(getApplicationContext(), "Id gresit, cumva", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void timeSet(int hour, int minute, int id) {
        switch(id)
        {
            case R.id.stTmAddAct:
            {
                stDate[3] = hour;
                stDate[4] = minute;
                stDate2Set = true;
                break;
            }
            case R.id.endTmAddAct:
            {
                endDate[3] = hour;
                endDate[4] = minute;
                endDate2Set = true;
                break;
            }
            case R.id.dlTmAddAct:
            {
                deadline[3] = hour;
                deadline[4] = minute;
                deadline2Set = true;
                break;
            }
            default:
            {
                Toast.makeText(getApplicationContext(), "Id gresit, cumva", Toast.LENGTH_LONG).show();
            }
        }
    }

}
