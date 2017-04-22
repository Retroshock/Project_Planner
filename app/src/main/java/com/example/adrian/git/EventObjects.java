package com.example.adrian.git;

import java.util.Date;

/**
 * Created by Adrian on 08.04.2017.
 */

class EventObjects {
    private int id;
    private String message;
    private Date date;

    public EventObjects (String message, Date date){
        this.message = message;
        this.date = date;
    }

    public EventObjects(int id, String message, Date reminderDate) {
        this.date = reminderDate;
        this.message = message;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getMessage(){
        return message;
    }

    public Date getDate() {
        return date;
    }
}
