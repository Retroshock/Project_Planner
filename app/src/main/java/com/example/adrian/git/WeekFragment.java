package com.example.adrian.git;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Iulia on 10.04.2017.
 */

public class WeekFragment extends Fragment{

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.week_layout, container, false);
        //myView = inflater.inflate(R.layout.fragment_week, container, false);
        return myView;
    }
}
