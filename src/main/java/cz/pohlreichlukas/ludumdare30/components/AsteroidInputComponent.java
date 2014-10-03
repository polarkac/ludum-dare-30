package cz.pohlreichlukas.ludumdare30.components;

import java.util.Random;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.GamePane;

public class AsteroidInputComponent implements InputComponent<Asteroid> {
    
    private static Random rnd = new Random();

    public void update( Asteroid e, World world, GamePane pane, long delta ) {
        e.setY( e.getY() + ( e.getVelocityY() / 1000f ) * delta );
        e.setX( e.getX() + ( e.getVelocityX() / 1000f ) * delta );

        if ( e.getY() > pane.getHeight() ) {
            e.setIsDead( true );
        }

        if ( e.isDead() && e.getLifes() == 0 && e.getWidth() > 50 ) {
            Asteroid left = new Asteroid( e.getX() - 30, e.getY(), 50, 50 );
            Asteroid right = new Asteroid( e.getX() + 30, e.getY(), 50, 50 );
            world.addEntity( left );
            world.addEntity( right );
        }

        e.setAngle( e.getAngle() + 20 / 1000f * delta );
    }
}
