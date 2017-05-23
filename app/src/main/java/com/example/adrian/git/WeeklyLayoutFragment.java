package com.example.adrian.git;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeeklyLayoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weekly_layout, container, false);
        Bundle b = getArguments();
        //TODO dupa ce getItem din WeekFragment.WeeklyPagerAdapter este implementat, atribui datele
        return root;
    }

}
