package cz.pohlreichlukas.ludumdare30.events;

import java.util.List;
import java.util.ArrayList;

public class Subject {
    
    private List<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver( Observer ob ) {
        this.observers.add( ob );
    }

    public void notify( Subject subject, Event event ) {
        for ( Observer o : this.observers ) {
            o.onNotify( subject, event );
        }
    }
}
