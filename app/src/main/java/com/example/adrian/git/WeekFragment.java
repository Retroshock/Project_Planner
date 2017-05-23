package com.example.adrian.git;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adrian.git.Date.Eveniment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Iulia on 10.04.2017.
 */

public class WeekFragment extends Fragment{

    ViewPager pager;
    public static final String TEST = "test 123";

    /**
     * Clasa care se ocupa de transmiterea fragmentelor catre ViewPager
     */
    private class WeeklyPagerAdapter extends FragmentStatePagerAdapter {

        public WeeklyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //TODO interogare baza de date si calendarul pentru a trimite date spre fragment in bundle
            //position 0 ar trebui sa fie cea mai inapoiata saptamana pe care sa o afisam
            //position x ar trebui sa fie saptamana 0+x

            /*Model de transmitere a datelor catre fragment:
            int date;//exemplu de date
            Fragment f = new MyFragment();//exemplu de fragment
            Bundle b = new Bundle();
            b.putInt(CHEIE, date);
            f.setArguments(b);
            return f;
             */

            Long firstDateInMillis = position * 604800000L + 345600000L;
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(firstDateInMillis));
            Calendar cal1 = (Calendar)cal.clone();
            cal1.add(Calendar.DAY_OF_MONTH, 6);
            SimpleDateFormat sdf = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
            DatabaseQuery dbq = new DatabaseQuery(getActivity());
            ArrayList<Eveniment> events = (ArrayList<Eveniment>)dbq.getAllFutureEvents();
            ArrayList<Eveniment> eventsBundle = new ArrayList<>();
            for (Eveniment ev:events){
                if (ev.getStartDate().before(cal1.getTime())){
                    eventsBundle.add(ev);
                } else break;
            }
            Bundle b = new Bundle();
            b.putSerializable(BundleKeys.WEEK_KEY, eventsBundle);
            Fragment f = new WeeklyLayoutFragment();
            f.setArguments(b);
            return f;
            //daca pui obiecte, trebuie sa fie Serializable sau Parcelable
            //eventual pui tipuri primitive
            //cheile pentru bundle le pui in WeekFragment, nu in alta parte
            //imi trebuiesc zilele si lunile din saptamana, plus toate evenimentele (evident :P
        }

        @Override
        public int getCount() {
            //TODO returneaza numarul de fragmente (saptamani) care pot fi afisate
            //nu stiu sigur la ce foloseste, in afara de probabil sa verifice position<getCount()

            return 99999;
        }
    }
        private Date convertStringToDate(String dateInString) {
            DateFormat format = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
            Date date = null;
            try{
                date = format.parse(dateInString);
            }catch (ParseException e){
                e.printStackTrace();
            }
            return date;
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_week, container, false);
        pager = (ViewPager) root.findViewById(R.id.pagerWeekFrag);
        WeeklyPagerAdapter adaptor = new WeeklyPagerAdapter(getChildFragmentManager());

        //TODO dupa ce WeeklyPagerAdapter si WeeklyLayoutFragment sunt implementate, decomentez randurile astea
        //TODO gaseste o metoda sa stabilesti pozitia saptamanii curente ca un int >=0
        //TODO (nu folosi aproape de 0, altfel nu avem cum sa mergem in trecut prea mult)
        pager.setAdapter(adaptor);
        pager.setPageMargin(0);



        // get today and clear time of day
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        // get start of this week in milliseconds
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 1);

        Long currentMilli = cal.getTimeInMillis();
        Long seconds = currentMilli / 1000;
        Long minutes = seconds / 60;
        Long hours = minutes / 60;
        Long days = hours / 24;
        Long weeks = days/7;


//        Date d = new Date(weeks);
//        SimpleDateFormat sdf = new SimpleDateFormat(Strings.DATE_FORMAT, Locale.ENGLISH);
//        Toast.makeText(getActivity(), sdf.format(new Date(chestie)), Toast.LENGTH_SHORT).show();
        pager.setCurrentItem(Integer.parseInt(weeks.toString())); //poz este pozitia corespunzatoare saptamanii curente, tu o determini
        return root;
    }
}
