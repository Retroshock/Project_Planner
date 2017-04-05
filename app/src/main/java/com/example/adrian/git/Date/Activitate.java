package com.example.adrian.git.Date;

import java.util.Date;

import javax.xml.datatype.Duration;

public abstract class Activitate {

    private Date startDate, endDate;
    private String name;

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
