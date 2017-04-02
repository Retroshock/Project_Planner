package com.example.adrian.git.Date;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.Calendar;

import javax.xml.datatype.Duration;

/**
 * Created by Adrian on 26.03.2017.
 */

public class Eveniment implements Comparable<Eveniment> {

    private Calendar startDate, endDate;

    private String name;

    private Location locatie;

    private boolean obligatoriu;

    public boolean isObligatoriu() {
        return obligatoriu;
    }

    public void setObligatoriu(boolean obligatoriu) {
        this.obligatoriu = obligatoriu;
    }

    public Location getLocatie() {
        return locatie;
    }

    public void setLocatie(Location locatie) {
        this.locatie = locatie;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(@NonNull Eveniment o) {
        return startDate.compareTo(o.getStartDate());
    }
}
