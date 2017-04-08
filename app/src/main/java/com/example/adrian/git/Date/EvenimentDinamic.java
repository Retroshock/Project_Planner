package com.example.adrian.git.Date;

import java.util.Calendar;

import javax.xml.datatype.Duration;

/**
 * Created by Adrian on 26.03.2017.
 */

public class EvenimentDinamic extends Eveniment {

    private Duration duration ;

    private Calendar deadline;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }


}
