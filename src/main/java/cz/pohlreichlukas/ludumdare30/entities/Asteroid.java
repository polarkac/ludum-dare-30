package cz.pohlreichlukas.ludumdare30.entities;

import cz.pohlreichlukas.ludumdare30.components.AsteroidRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.AsteroidInputComponent;

public class Asteroid extends Entity {

    private int speed;
    
    public Asteroid() {
        super( 320, 100, 100, 100 );
        this.renderer = new AsteroidRendererComponent();
        this.input = new AsteroidInputComponent();
        this.speed = 250;
    }

    public int getSpeed() {
        return this.speed;	
    }

    public void setSpeed( int speed ) {
        this.speed = speed;
    }

    public void hit( Entity e ) {
    
    }
}
