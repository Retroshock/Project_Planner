package com.example.adrian.git;

import java.util.Date;

/**
 * Created by Adrian on 08.04.2017.
 */

class EventObjects {
    private int id;
    private String nume;
    private Date date;

    public EventObjects (String nume, Date date){
        this.nume = nume;
        this.date = date;
    }

    public EventObjects(int id, String nume, Date reminderDate) {
        this.date = reminderDate;
        this.nume = nume;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNume(){
        return nume;
    }

    public Date getDate() {
        return date;
    }
}
