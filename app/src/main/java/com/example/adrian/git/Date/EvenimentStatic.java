package com.example.adrian.git.Date;

/**
 * Created by Adrian on 26.03.2017.
 */

public class EvenimentStatic extends Eveniment {

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    private boolean repeatable;

    public EvenimentStatic(){
        super();
    }
}
