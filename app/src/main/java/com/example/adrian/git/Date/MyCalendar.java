package com.example.adrian.git.Date;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Adrian on 27.03.2017.
 */

public class MyCalendar {

    private Date startHour, endHour;

    private ArrayList <Eveniment> normalEvnts;

    private ArrayList <EvenimentDinamic> dinamicEvnts;

    private ArrayList <EvenimentStatic> staticEvnts;

    public boolean addEvent(Eveniment ev){
        if (ev instanceof EvenimentStatic) {
            if (!isCollision(normalEvnts, ev) && isValid(ev)) {
                staticEvnts.add((EvenimentStatic) ev);
                normalEvnts.add(ev);
                return true;
            }
        }
        if (ev instanceof EvenimentDinamic){
            if (!isCollision(normalEvnts, ev) && isValid(ev)) {
                addProcedure((EvenimentDinamic)ev);
                dinamicEvnts.add((EvenimentDinamic) ev);
                normalEvnts.add(ev);
                return true;
            }
        }
        if (ev instanceof Eveniment){
            if (isCollision(normalEvnts, ev) && isValid(ev)) {
                normalEvnts.add(ev);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("Since15")
    private boolean addProcedure(EvenimentDinamic ev) {
        Date currDate = new Date();
        normalEvnts.sort(new Comparator<Eveniment>() {
            @Override
            public int compare(Eveniment o1, Eveniment o2) {
                return o1.compareTo(o2);
            }
        });

        long timeDifference = ev.getDeadline().getTime() - currDate.getTime();
        if (timeDifference <= 0){
            return false;
        }
        for (int i=0 ; i<normalEvnts.size(); i++)



        addDynamicEvent(startHour, endHour, ev);
        return  false;

    }

    private void addDynamicEvent(Date startHour, Date endHour, EvenimentDinamic ev) {
        //Algoritmul de adaugare a evenimentului dinamic

    }


    private boolean isCollision(ArrayList<Eveniment> staticEvnts, Eveniment ev) {

        for (Eveniment evS : staticEvnts){
            if (evS.compareTo(ev) < 0){
                if (evS.getEndDate().compareTo(ev.getStartDate()) <= 0){
                    return true;
                }
            }
            else{
                if (ev.getStartDate().compareTo(evS.getEndDate()) <= 0){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid (Eveniment ev){
        if (ev.getStartDate().compareTo(ev.getEndDate()) >= 0)
            return false;
        return true;
    }
}
