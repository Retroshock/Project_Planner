package com.example.adrian.git.Date;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Adrian on 27.03.2017.
 */

public class Calendar {
    private ArrayList <Eveniment> normalEvnts;

    private ArrayList <EvenimentDinamic> dinamicEvnts;

    private ArrayList <EvenimentStatic> staticEvnts;

    public boolean addEvent(Eveniment ev){
        if (ev instanceof EvenimentStatic) {
            if (isCollision(normalEvnts, ev) && isValid(ev)) {
                staticEvnts.add((EvenimentStatic) ev);
                normalEvnts.add(ev);
                return true;
            }
        }
        if (ev instanceof EvenimentDinamic){
            if (isCollision(normalEvnts, ev) && isValid(ev)) {
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
