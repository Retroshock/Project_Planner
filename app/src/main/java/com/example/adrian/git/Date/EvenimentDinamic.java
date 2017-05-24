package com.example.adrian.git.Date;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by Adrian on 26.03.2017.
 */

public class EvenimentDinamic extends Eveniment {

    private Duration duration ;

    private Date deadline;

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
