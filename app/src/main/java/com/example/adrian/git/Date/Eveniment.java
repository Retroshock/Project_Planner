package com.example.adrian.git.Date;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by Adrian on 26.03.2017.
 */

public class Eveniment implements Comparable<Eveniment> {

    private Date startDate, endDate;

    private long ID ;

    private String name;

    private String locatie;

    private boolean obligatoriu;

    public boolean isObligatoriu() {
        return obligatoriu;
    }

    public void setObligatoriu(boolean obligatoriu) {
        this.obligatoriu = obligatoriu;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
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

    @Override
    public int compareTo(@NonNull Eveniment o) {
        return startDate.compareTo(o.getStartDate());
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }
}
