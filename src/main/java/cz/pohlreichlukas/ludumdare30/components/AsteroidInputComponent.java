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
            // TODO
            int n = pane.getWidth() - a.getWidth();
            if ( n < 0 ) {
                n = 200;
            }
            a.setX( AsteroidInputComponent.rnd.nextInt( n ) );
            a.setY( 0 );
        }
    }
}
