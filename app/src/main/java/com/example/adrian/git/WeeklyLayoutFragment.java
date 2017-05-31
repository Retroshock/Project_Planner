package com.example.adrian.git;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adrian.git.Date.Eveniment;

import java.util.ArrayList;
import java.util.Calendar;

public class WeeklyLayoutFragment extends Fragment {

    private static final String luna[] = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
            "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weekly_layout, container, false);
        Bundle b = getArguments();
        ArrayList<Eveniment> events = (ArrayList<Eveniment>) b.getSerializable(BundleKeys.WEEK_KEY);
        Calendar start = (Calendar) b.getSerializable(WeekFragment.WEEK_START_KEY);
        Calendar temp = Calendar.getInstance();
        TextView luniText = (TextView) root.findViewById(R.id.luniTextWeekFrag);
        TextView martiText = (TextView) root.findViewById(R.id.martiTextWeekFrag);
        TextView miercuriText = (TextView) root.findViewById(R.id.miercuriTextWeekFrag);
        TextView joiText = (TextView) root.findViewById(R.id.joiTextWeekFrag);
        TextView vineriText = (TextView) root.findViewById(R.id.vineriTextWeekFrag);
        TextView sambataText = (TextView) root.findViewById(R.id.sambataTextWeekFrag);
        TextView duminicaText = (TextView) root.findViewById(R.id.duminicaTextWeekFrag);
        LinearLayout luniCont = (LinearLayout) root.findViewById(R.id.luniContWeekFrag);
        LinearLayout martiCont = (LinearLayout) root.findViewById(R.id.martiContWeekFrag);
        LinearLayout miercuriCont = (LinearLayout) root.findViewById(R.id.miercuriContWeekFrag);
        LinearLayout joiCont = (LinearLayout) root.findViewById(R.id.joiContWeekFrag);
        LinearLayout vineriCont = (LinearLayout) root.findViewById(R.id.vineriContWeekFrag);
        LinearLayout sambataCont = (LinearLayout) root.findViewById(R.id.sambataContWeekFrag);
        LinearLayout duminicaCont = (LinearLayout) root.findViewById(R.id.duminicaContWeekFrag);
        LinearLayout.LayoutParams parametri = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1F);
        int lunaCurenta = start.get(Calendar.MONTH);
        int[] zileleLunii = new int[7];
        zileleLunii[0] = start.get(Calendar.DAY_OF_MONTH);
        luniText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        start.add(Calendar.DAY_OF_MONTH, 1);
        zileleLunii[1] = start.get(Calendar.DAY_OF_MONTH);
        lunaCurenta = start.get(Calendar.MONTH);
        martiText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        start.add(Calendar.DAY_OF_MONTH, 1);
        zileleLunii[2] = start.get(Calendar.DAY_OF_MONTH);
        lunaCurenta = start.get(Calendar.MONTH);
        miercuriText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        start.add(Calendar.DAY_OF_MONTH, 1);
        zileleLunii[3] = start.get(Calendar.DAY_OF_MONTH);
        lunaCurenta = start.get(Calendar.MONTH);
        joiText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        start.add(Calendar.DAY_OF_MONTH, 1);
        zileleLunii[4] = start.get(Calendar.DAY_OF_MONTH);
        lunaCurenta = start.get(Calendar.MONTH);
        vineriText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        start.add(Calendar.DAY_OF_MONTH, 1);
        zileleLunii[5] = start.get(Calendar.DAY_OF_MONTH);
        lunaCurenta = start.get(Calendar.MONTH);
        sambataText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        start.add(Calendar.DAY_OF_MONTH, 1);
        zileleLunii[6] = start.get(Calendar.DAY_OF_MONTH);
        lunaCurenta = start.get(Calendar.MONTH);
        duminicaText.setText((Integer.valueOf(start.get(Calendar.DAY_OF_MONTH))).toString() + "\n" + luna[lunaCurenta]);
        int startDay = start.get(Calendar.DAY_OF_MONTH);
        for(final Eveniment ev: events)
        {
            temp.setTime(ev.getStartDate());
            int evDay = temp.get(Calendar.DAY_OF_MONTH);
            int i;
            for(i = 0; i < 6; i++)
                if(evDay == zileleLunii[i])
                    break;
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(R.drawable.logo_bun);
            switch(i)
            {
                case 0:
                {
                    luniCont.addView(iv, parametri);
                    break;
                }
                case 1:
                {
                    martiCont.addView(iv, parametri);
                    break;
                }
                case 2:
                {
                    miercuriCont.addView(iv, parametri);
                    break;
                }
                case 3:
                {
                    joiCont.addView(iv, parametri);
                    break;
                }
                case 4:
                {
                    vineriCont.addView(iv, parametri);
                    break;
                }
                case 5:
                {
                    sambataCont.addView(iv, parametri);
                    break;
                }
                case 6:
                {
                    duminicaCont.addView(iv, parametri);
                    break;
                }
                default:
                {
                    break;
                }
            }
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddActivity.class);
                    intent.putExtra(AddActivity.EVT_KEY, ev);
                    startActivity(intent);
                }
            });
        }
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
