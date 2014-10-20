package cz.pohlreichlukas.ludumdare30.events;

import cz.pohlreichlukas.ludumdare30.GamePane;

public class GuiObserver implements Observer {

    private GamePane pane;

    public GuiObserver( GamePane pane ) {
        this.pane = pane;
    }

    public void onNotify( Subject subject, Event event ) {
    
    }
}
