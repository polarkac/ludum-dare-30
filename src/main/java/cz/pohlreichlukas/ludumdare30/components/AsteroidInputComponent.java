package cz.pohlreichlukas.ludumdare30.components;

import java.util.Random;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.GamePane;

public class AsteroidInputComponent implements InputComponent {
    
    private static Random rnd = new Random();

    public void update( Entity e, World world, GamePane pane, long delta ) {
        Asteroid a = (Asteroid) e;
        a.setY( a.getY() + ( a.getSpeed() / 1000f ) * delta );

        if ( a.getY() > pane.getHeight() ) {
            a.setIsDead( true );
        }

        if ( a.isDead() && a.getLifes() == 0 && e.getWidth() > 50 ) {
            Asteroid left = new Asteroid( a.getX() - 30, a.getY(), 50, 50 );
            Asteroid right = new Asteroid( a.getX() + 30, a.getY(), 50, 50 );
            world.addEntity( left );
            world.addEntity( right );
        }
    }
}
