package com.example.adrian.git;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Adrian on 08.04.2017.
 */

class GridAdapter extends ArrayAdapter{

    public static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List <Date> monthlyDates;
    private Calendar currentDate;
    private List <EventObjects> allEvents;

    public GridAdapter(Context context, List<Date> dayValueInCells, Calendar cal, List<EventObjects> mEvents) {
        super(context,R.layout.single_cell_layout);
        this.monthlyDates = dayValueInCells;
        this.currentDate = cal;
        this.allEvents = mEvents;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH);
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if (view == null){
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        if (displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(Color.parseColor("#FF5733"));
        }else{
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        //Add a day to calendar
        TextView cellNumber = (TextView) view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf (dayValue));

        //TODO Add events to the calendar

        TextView eventIndicator = (TextView) view.findViewById(R.id.calendar_date_id);
        Calendar eventCalendar = Calendar.getInstance();
        for(int i = 0; i < allEvents.size(); i++){
            eventCalendar.setTime(allEvents.get(i).getDate());
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) &&
                    displayMonth == eventCalendar.get(Calendar.MONTH) + 1 &&
                    displayYear == eventCalendar.get(Calendar.YEAR)){
                eventIndicator.setBackgroundColor( Color.parseColor("#FF4081"));
            }
        }
        return view;
    }
    @Override
    public int getCount() {
        return monthlyDates.size();
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }
    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }

}
