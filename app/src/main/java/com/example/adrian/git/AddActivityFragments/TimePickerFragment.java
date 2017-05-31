package com.example.adrian.git.AddActivityFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.example.adrian.git.AddActivity;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
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
        b1 = b.getBoolean(AddActivity.BOOL1);
        b2 = b.getBoolean(AddActivity.BOOL2);
        int hour, minute;
        int[] data = b.getIntArray(AddActivity.DATA_KEY);
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