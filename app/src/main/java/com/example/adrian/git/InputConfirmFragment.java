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
import android.widget.TextView;

public class InputConfirmFragment extends DialogFragment {

    private TextView nume;
    private TextView tip;
    private TextView dyn1;
    private TextView dyn2;
    private TextView dyn3;
    private TextView dyn4;
    private Bundle b;

    public interface InputConfirmListener {
        void onInputConfirmPositiveClick(DialogFragment dialog, Bundle b);
        void onInputConfirmNegativeClick(DialogFragment dialog);
    }

    private InputConfirmListener inputListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_duration_picker, null);
        builder.setView(v);
        nume = (TextView) v.findViewById(R.id.numeInputConfirmFrag);
        tip = (TextView) v.findViewById(R.id.tipInputConfirmFrag);
        dyn1 = (TextView) v.findViewById(R.id.dyn1InputConfirmFrag);
        dyn2 = (TextView) v.findViewById(R.id.dyn2InputConfirmFrag);
        dyn3 = (TextView) v.findViewById(R.id.dyn3InputConfirmFrag);
        dyn4 = (TextView) v.findViewById(R.id.dyn4InputConfirmFrag);
        b = getArguments();
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputListener.onInputConfirmPositiveClick(InputConfirmFragment.this, b);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputListener.onInputConfirmNegativeClick(InputConfirmFragment.this);
            }
        });
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        boolean t = b.getBoolean(AddActivity.TYPE_KEY);
        if(!t)
        {
            dyn1.setText(R.string.tv2_add_act);
            dyn2.setText(R.string.tv3_add_act);
            int stDate[] = b.getIntArray(AddActivity.ST_DATE_KEY);
            int endDate[] = b.getIntArray(AddActivity.END_DATE_KEY);
            tip.setText("Static");
            String startDate = "" +  stDate[0] + "." + stDate[1] + "." + stDate[2] + " " + stDate[3] + ":" + stDate[4];
            String eDate = "" +  endDate[0] + "." + endDate[1] + "." + endDate[2] + " " + endDate[3] + ":" + endDate[4];
            dyn3.setText(startDate);
            dyn4.setText(eDate);
        }
        else
        {
            dyn1.setText(R.string.tv5_add_act);
            dyn2.setText(R.string.tv6_add_act);
            int duration[] = b.getIntArray(AddActivity.DURATION_KEY);
            int deadline[] = b.getIntArray(AddActivity.DEADLINE_KEY);
            tip.setText("Dynamic");
            String dura = "" + duration[0] + " hours and " + duration[1] + "minutes";
            String dead = "" +  deadline[0] + "." + deadline[1] + "." + deadline[2] + " " + deadline[3] + ":" + deadline[4];
            dyn3.setText(dura);
            dyn4.setText(dead);
        }
        return null;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try {
            inputListener = (InputConfirmListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement InputConfirmListener");
        }
    }

}
