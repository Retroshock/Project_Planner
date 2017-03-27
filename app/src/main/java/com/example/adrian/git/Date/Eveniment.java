package com.example.adrian.git.Date;

import android.location.Location;

import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by Adrian on 26.03.2017.
 */

public class Eveniment {

    private Date startDate, endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
