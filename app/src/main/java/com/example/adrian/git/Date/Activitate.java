package com.example.adrian.git.Date;

import java.util.Calendar;

import javax.xml.datatype.Duration;

/**
 * Created by Adrian on 26.03.2017.
 */

public abstract class Activitate {

    private Calendar startDate, endDate;

    private String name;

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



}
