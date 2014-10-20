package cz.pohlreichlukas.ludumdare30.events;

import cz.pohlreichlukas.ludumdare30.entities.Entity;

public interface Observer {
    public void onNotify( Subject subject, Event event );
}
