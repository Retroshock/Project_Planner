package com.example.adrian.git.AddActivityFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.adrian.git.AddActivity;
import com.example.adrian.git.R;

public class DurationPickerFragment extends DialogFragment
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
            int[] durata = b.getIntArray(AddActivity.DURATA_KEY);
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