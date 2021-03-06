package com.example.adrian.git;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adrian.git.Date.Eveniment;

import org.joda.time.Period;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Iulia on 10.04.2017.
 */

public class DayFragment extends Fragment {
    private Date currDateInDate = MonthToDay.date;
    private ImageView previousDay;
    private ImageView nextDay;
    private TextView currentDate;
    private Calendar cal = Calendar.getInstance();
    private DatabaseQuery mQuery;
    private RelativeLayout mLayout;
    private int eventIndex;
    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_day, container, false);
        mQuery = new DatabaseQuery(getActivity());
        mLayout = (RelativeLayout) myView.findViewById(R.id.left_event_column);
        eventIndex = mLayout.getChildCount();
        currentDate = (TextView) myView.findViewById(R.id.day_number);
        if (currDateInDate == null)
            currentDate.setText(displayDateInString(cal.getTime()));
        else {
            currentDate.setText(displayDateInString(currDateInDate));
            cal.setTime(currDateInDate);
        }
        displayDailyEvents();
        previousDay = (ImageView) myView.findViewById(R.id.previous_day);
        nextDay = (ImageView) myView.findViewById(R.id.next_day);
        previousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousCalendarDate();
            }
        });
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextCalendarDate();
            }
        });
        return myView;
    }

    private void previousCalendarDate() {
        while (eventIndex > 25) {
            mLayout.removeViewAt(eventIndex - 1);
            eventIndex--;
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);
        currentDate.setText(displayDateInString(cal.getTime()));
        displayDailyEvents();
    }

    private void nextCalendarDate() {
        while (eventIndex > 25) {
            mLayout.removeViewAt(eventIndex - 1);
            eventIndex--;
        }
        cal.add(Calendar.DAY_OF_MONTH, 1);
        currentDate.setText(displayDateInString(cal.getTime()));
        displayDailyEvents();
    }

    private String displayDateInString(Date mDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM", Locale.ENGLISH);
        return formatter.format(mDate);
    }

    private void displayDailyEvents() {
        Date calendarDate = cal.getTime();
        List<Eveniment> dailyEvent = mQuery.getAllFutureEvents();
        for (Eveniment eObject : dailyEvent) {
            if (!eObject.getName().equals("SLEEP")) {
                Calendar day = Calendar.getInstance();
                day.setTime(eObject.getStartDate());

                if (day.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) &&
                        day.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {
                    Date eventDate = eObject.getStartDate();
                    Date endDate = eObject.getEndDate();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(endDate);
                    if (calendar.get(Calendar.DAY_OF_MONTH) != day.get(Calendar.DAY_OF_MONTH)) {
                        String eventMessage = eObject.getName();
                        calendar.set(Calendar.HOUR_OF_DAY, 24);
                        int eventBlockHeight = getEventTimeFrame(eventDate, calendar.getTime());
                        //TODO aici, in displayEventSection e partea care iti creeaza casuta cu evenimentul
                        displayEventSection(eventDate, eventBlockHeight, eventMessage);
                    } else {
                        String eventMessage = eObject.getName();
                        int eventBlockHeight = getEventTimeFrame(eventDate, endDate);
                        displayEventSection(eventDate, eventBlockHeight, eventMessage);
                    }
                }
            }
        }
    }

    private int getEventTimeFrame(Date start, Date end) {
        //long timeDifference = end.getTime() - start.getTime();
        // TODO aici teoretic incepe gasirea diferentei de timp intre startdate si endDate
        /*long secs = (end.getTime() - start.getTime()) / 1000;
        int hours = (int)secs / 3600;
        secs = secs % 1000;
        int mins = (int)secs % 60;*/
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(start);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        int hours = endCal.get(Calendar.HOUR_OF_DAY) - startCal.get(Calendar.HOUR_OF_DAY);
        int minEnd = endCal.get(Calendar.MINUTE);
        int minStart = startCal.get(Calendar.MINUTE);
        if(minEnd < minStart) {
            hours--;
            minEnd += 60;
        }
        int mins = minEnd - minStart;
        return hours * 60 +  mins;
    }

    private void displayEventSection(Date eventDate, int height, String message) {

        /*SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String displayValue = timeFormatter.format(eventDate);
        String[] hourMinutes = displayValue.split(":");
        int hours = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(eventDate);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        int topViewMargin = hours * 60 + minutes;
        //TODO Aici in createEventView teoretic se deseneaza evenimentul >
        createEventView(topViewMargin, height, message);
    }

    private void createEventView(int topMargin, int height, String message) {
        //TODO aici se deseneaza efectiv ...
        TextView mEventView = new TextView(getActivity());
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = Math.round(topMargin * getResources().getDisplayMetrics().density);
        lParam.leftMargin = 24;
        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(24, 0, 24, 0);
        mEventView.setHeight(Math.round(height * getResources().getDisplayMetrics().density));
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(message);
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));
        mLayout.addView(mEventView, eventIndex);

        //Nu te juca cu eventIndexul asta :D
        eventIndex++;
    }

}
