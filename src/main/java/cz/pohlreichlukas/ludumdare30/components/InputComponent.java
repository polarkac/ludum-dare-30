package cz.pohlreichlukas.ludumdare30.components;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;

public interface InputComponent {
    
    public void update( Entity e, GamePane pane, long delta );
}
