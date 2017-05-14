package com.example.adrian.git;

import android.support.design.widget.BaseTransientBottomBar;

import com.example.adrian.git.Date.Constants;
import com.example.adrian.git.Date.Eveniment;
import com.example.adrian.git.Date.MyCalendar;

import org.joda.time.Duration;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Robert Iacob on 22.04.2017.
 */

public class MergeEvents extends MyCalendar {

    private ArrayList <Eveniment> freeTime;
    private ArrayList <Eveniment> mergeArray;
    private ArrayList <Eveniment> storeAccEvent;
    private long sumFreeTime;

    private void detFreeTime(ArrayList <Eveniment> dbEvents){
        Eveniment start, tempEvent;
        start = dbEvents.get(0);
        freeTime = new ArrayList<>();

        for(int i = 1; i < dbEvents.size(); i++){
            if(timeDifference(start, dbEvents.get(i))> Constants.TWO_HOURS){
                tempEvent = new Eveniment();
                tempEvent.setStartDate(start.getEndDate());
                tempEvent.setEndDate(dbEvents.get(i).getStartDate());
                freeTime.add(tempEvent);
            }
            start = dbEvents.get(i);
        }

        sumFreeTime = 0;
        for(Eveniment x:freeTime){
            sumFreeTime += (x.getEndDate().getTime() - x.getStartDate().getTime());
        }
    }

    private long timeDifference(Eveniment start, Eveniment eveniment) {
        return eveniment.getStartDate().getTime() - start.getEndDate().getTime();
    }

    public long divideAlgorithm(long eventDuration){
        long tempDuration, restStartTime, eventDivision;
        storeAccEvent = new ArrayList<>();

        if(eventDuration < Constants.TWO_HOURS){
            //TODO add in baza de date
            storeAccEvent.add(freeTime.get(0));
            freeTime.get(0).setStartDate(new Date(eventDuration));
        }
        else {
            if (eventDuration % 2 == 0)
                eventDivision = eventDuration / sumFreeTime;
            else {
                eventDuration += Constants.ONE_HOUR;
                eventDivision = eventDuration / sumFreeTime;
            }

            for (int i = 0; i < freeTime.size(); i++) {
                tempDuration = freeTime.get(i).getEndDate().getTime() - freeTime.get(i).getStartDate().getTime();
                if (tempDuration < eventDivision) {
                    Eveniment storeEvent = new Eveniment();
                    storeEvent.setStartDate(freeTime.get(i).getStartDate());
                    storeEvent.setEndDate(new Date(tempDuration + storeEvent.getStartDate().getTime()));
                    storeAccEvent.add(storeEvent);

                    //freetime[i] tocmai ce a adaugat o bucatica(eventDivison). restStartTime retine cat timp mai este din freeTime-ul actual
                    restStartTime = freeTime.get(i).getStartDate().getTime() + eventDivision;
                    freeTime.get(i).setStartDate(new Date(restStartTime));
                    eventDuration -= eventDivision;
                } else if (tempDuration == eventDivision) {
                    storeAccEvent.add(freeTime.get(i));
                    eventDuration -= eventDivision;
                }
            }

            if (eventDuration > 0) {
                return divideAlgorithm(eventDuration);
            }
        }
        return eventDuration;
    }

    public void divideEvent(Eveniment event, ArrayList<Eveniment> dbEvents){
        detFreeTime(dbEvents);
        long eventDuration;
        eventDuration = event.getEndDate().getTime() - event.getStartDate().getTime();
        divideAlgorithm(eventDuration);
    }
}
