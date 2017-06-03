package com.example.adrian.git;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

/**
 * Created by Iulia on 10.04.2017.
 */

public class MonthFragment extends Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        MonthToDay.date = null;
        myView = inflater.inflate(R.layout.activity_custom_calendar, container, false);
        return myView;
    }

    public void startNavAgain(DayFragment df, Date d){
        Intent intent = new Intent();
        intent.putExtra(BundleKeys.CURRENT_POZ_KEY, d);
        startActivity(intent);
    }

}
