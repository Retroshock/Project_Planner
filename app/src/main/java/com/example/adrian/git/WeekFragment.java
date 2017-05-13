package com.example.adrian.git;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Iulia on 10.04.2017.
 */

public class WeekFragment extends Fragment{

    VerticalViewPager pager;
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

            //daca pui obiecte, trebuie sa fie Serializable sau Parcelable
            //eventual pui tipuri primitive
            //cheile pentru bundle le pui in WeekFragment, nu in alta parte
            //imi trebuiesc zilele si lunile din saptamana, plus toate evenimentele (evident :P
            return null;
        }

        @Override
        public int getCount() {
            //TODO returneaza numarul de fragmente (saptamani) care pot fi afisate
            //nu stiu sigur la ce foloseste, in afara de probabil sa verifice position<getCount()
            return 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_week, container, false);
        pager = (VerticalViewPager) root.findViewById(R.id.pagerWeekFrag);
        WeeklyPagerAdapter adaptor = new WeeklyPagerAdapter(getChildFragmentManager());
        //TODO dupa ce WeeklyPagerAdapter si WeeklyLayoutFragment sunt implementate, decomentez randurile astea
        //TODO gaseste o metoda sa stabilesti pozitia saptamanii curente ca un int >=0
        //TODO (nu folosi aproape de 0, altfel nu avem cum sa mergem in trecut prea mult)
        //pager.setAdapter(adaptor);
        //pager.setPageMargin(0);
        //pager.setCurrentItem(poz); //poz este pozitia corespunzatoare saptamanii curente, tu o determini
        return root;
    }
}
