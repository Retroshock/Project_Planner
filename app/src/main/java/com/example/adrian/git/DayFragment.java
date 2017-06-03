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
        if (eventIndex > 25) {
            mLayout.removeViewAt(eventIndex - 1);
            eventIndex--;
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);
        currentDate.setText(displayDateInString(cal.getTime()));
        displayDailyEvents();
    }

    private void nextCalendarDate() {
        if (eventIndex > 25) {
            mLayout.removeViewAt(eventIndex - 1);
            eventIndex--;
        }
        cal.add(Calendar.DAY_OF_MONTH, 1);
        currentDate.setText(displayDateInString(cal.getTime()));
        displayDailyEvents();
    }

    private String displayDateInString(Date mDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH);
        return formatter.format(mDate);
    }

    private void displayDailyEvents() {
        Date calendarDate = cal.getTime();
        List<Eveniment> dailyEvent = mQuery.getAllFutureEvents();
        for (Eveniment eObject : dailyEvent) {
            Calendar day = Calendar.getInstance();
            day.setTime(eObject.getStartDate());

            if (day.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
                Date eventDate = eObject.getStartDate();
                Date endDate = eObject.getEndDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                if (calendar.get(Calendar.DAY_OF_MONTH) != day.get(Calendar.DAY_OF_MONTH)) {
                    String eventMessage = eObject.getName();
                    calendar.set(Calendar.HOUR_OF_DAY, 24);
                    int eventBlockHeight = getEventTimeFrame(eventDate, calendar.getTime());
                    displayEventSection(eventDate, eventBlockHeight, eventMessage);
                } else {
                    String eventMessage = eObject.getName();
                    int eventBlockHeight = getEventTimeFrame(eventDate, endDate);
                    displayEventSection(eventDate, eventBlockHeight, eventMessage);
                }
            } else break;
        }
    }

    private int getEventTimeFrame(Date start, Date end) {
        long timeDifference = end.getTime() - start.getTime();
        Calendar mCal = Calendar.getInstance();
        mCal.setTimeInMillis(timeDifference);
        int hours = mCal.get(Calendar.HOUR);
        int minutes = mCal.get(Calendar.MINUTE);
        return (hours * 60) + ((minutes * 60) / 100);
    }

    private void displayEventSection(Date eventDate, int height, String message) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String displayValue = timeFormatter.format(eventDate);
        String[] hourMinutes = displayValue.split(":");
        int hours = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);

        int topViewMargin = (hours * 60) + ((minutes * 60) / 100);

        createEventView(topViewMargin, height, message);
    }

    private void createEventView(int topMargin, int height, String message) {
        TextView mEventView = new TextView(getActivity());
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = topMargin * 2;
        lParam.leftMargin = 24;
        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(24, 0, 24, 0);
        mEventView.setHeight(height * 2);
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(message);
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));
        mLayout.addView(mEventView, eventIndex);
        eventIndex++;
    }

}
