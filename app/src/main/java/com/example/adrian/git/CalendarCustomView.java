package com.example.adrian.git;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrian.git.Date.Eveniment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

/**
 * Created by Adrian on 08.04.2017.
 */

public class CalendarCustomView extends LinearLayout{

    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private List <Date> dayValueInCells;
    private GridView calendarGridView;
    private Button addEventButton;
    private List<Eveniment> mEvents;
    private static final int MAX_CALENDAR_COLUMN = 42;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance();
    private Context context;
    private GridAdapter mAdapter;
    private DatabaseQuery mQuery;

    public CalendarCustomView(Context context) {
        super(context);

    }
    public CalendarCustomView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }
    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setNextButtonClickEvent(){
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, +1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Date dateAtPosition = getDateAtPosition(position);
                MonthToDay.date = dateAtPosition;
                FragmentManager fragmentManager = MonthToDay.navActivity.getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new DayFragment()).commit();
            }
        });
    }

    private String getEventNameAtDate(Date dateAtPosition) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateAtPosition);
        for (Eveniment x:mEvents){
            Calendar temp = Calendar.getInstance();
            temp.setTime(x.getStartDate());
            if (temp.get(Calendar.MONTH) == c.get(Calendar.MONTH)){
                if (temp.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH))
                    return x.getName();
            }
        }
        return "No event here";
    }

    private Date getDateAtPosition(int position) {
        return dayValueInCells.get(position);
    }


    private void setPreviousButtonClickEvent() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setUpCalendarAdapter() {
        dayValueInCells = new ArrayList<>();
        mQuery = new DatabaseQuery(context);
        if (mQuery.getAllFutureEvents().size() > 0)
            mQuery.addSleepEventsToDB(mQuery.getAllFutureEvents());
        mEvents = mQuery.getAllFutureEvents();
        //List <EventObjects> mEvents = new ArrayList<>();
        Calendar mCal = (Calendar)cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 2 ;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while (dayValueInCells.size() < MAX_CALENDAR_COLUMN){
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d (TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);
        mAdapter = new GridAdapter(context, dayValueInCells, cal, mEvents);
        calendarGridView.setAdapter(mAdapter);
    }

    private void initializeUILayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        previousButton = (ImageView) view.findViewById(R.id.previous_month);
        nextButton = (ImageView) view.findViewById(R.id.next_month);
        currentDate = (TextView) view.findViewById(R.id.display_current_date);
        //addEventButton = (Button) view.findViewById(R.id.add_calendar_event);
        calendarGridView = (GridView) view.findViewById(R.id.calendar_grid);
    }

}
