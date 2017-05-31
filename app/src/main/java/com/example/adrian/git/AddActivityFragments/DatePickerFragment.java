package com.example.adrian.git.AddActivityFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.DatePicker;

import com.example.adrian.git.AddActivity;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
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
        b1 = b.getBoolean(AddActivity.BOOL1);
        b2 = b.getBoolean(AddActivity.BOOL2);
        int year, month, day;
        int[] data = b.getIntArray(AddActivity.DATA_KEY);
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