package com.example.adrian.git.Date;

import java.util.Date;

import javax.xml.datatype.Duration;


public class ActivitateDinamica extends Activitate {
    private Duration duration ;
    private Date deadline = new Date();

    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
