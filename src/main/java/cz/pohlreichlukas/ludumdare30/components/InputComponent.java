package cz.pohlreichlukas.ludumdare30.components;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public interface InputComponent {
    
    public void update( Entity e, World world, GamePane pane, long delta );
}
