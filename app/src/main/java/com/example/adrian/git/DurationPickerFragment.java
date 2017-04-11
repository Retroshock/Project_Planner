package com.example.adrian.git;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DurationPickerFragment extends DialogFragment {

    private EditText hours;
    private EditText minutes;

    public interface DurationPickerListener
    {
        void onDurationPickerPositiveClick(DialogFragment dialog, int hours, int minutes);
        void onDurationPickerNegativeClick(DialogFragment dialog);
    }

    private DurationPickerListener durationListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_duration_picker, null);
        builder.setView(v);
        hours = (EditText) v.findViewById(R.id.hoursDurationDialFrag);
        minutes = (EditText) v.findViewById(R.id.minutesDurationDialFrag);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int h, m;
                if(hours.getText().toString().isEmpty())
                    h = 0;
                else
                    h = Integer.parseInt(hours.getText().toString());
                if(minutes.getText().toString().isEmpty())
                    m = 0;
                else
                    m = Integer.parseInt(minutes.getText().toString());
                durationListener.onDurationPickerPositiveClick(DurationPickerFragment.this, h, m);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                durationListener.onDurationPickerNegativeClick(DurationPickerFragment.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            durationListener = (DurationPickerListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DurationPickerListener");
        }
    }
}
