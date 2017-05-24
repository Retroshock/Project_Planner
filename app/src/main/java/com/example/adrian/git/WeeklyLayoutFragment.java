package com.example.adrian.git;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrian.git.Date.Eveniment;

import java.util.ArrayList;

public class WeeklyLayoutFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weekly_layout, container, false);
        Bundle b = getArguments();
        ArrayList<Eveniment> events = (ArrayList<Eveniment>) b.getSerializable(BundleKeys.WEEK_KEY);
//        Eveniment ev = events.get(0);
//        ImageView iv = new ImageView(getActivity());
//        iv.setImageResource(R.drawable.buton);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.
//            }
//        });
        //TODO dupa ce getItem din WeekFragment.WeeklyPagerAdapter este implementat, atribui datele
        return root;
    }

}
