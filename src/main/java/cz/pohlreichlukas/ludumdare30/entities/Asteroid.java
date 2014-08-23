package cz.pohlreichlukas.ludumdare30.entities;

import java.lang.Math;

import cz.pohlreichlukas.ludumdare30.components.AsteroidRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.AsteroidInputComponent;

public class Asteroid extends Entity {

    private int speed;
    private int lifes;
    
    public Asteroid( float x, float y, int width, int height ) {
        super( x, y, width, height );
        this.renderer = new AsteroidRendererComponent();
        this.input = new AsteroidInputComponent();
        this.speed = 250;
        this.lifes = Math.round( width / 20 );
    }

    public Asteroid( float x, float y ) {
        this( x, y, 100, 100 );
    }

    public int getSpeed() {
        return this.speed;	
    }

    public void setSpeed( int speed ) {
        this.speed = speed;
    }

    public void hit( Entity e ) {
        if ( e instanceof Asteroid ) return;
        this.lifes--;

        if ( this.lifes == 0 ) {
            this.setIsDead( true );
        }
    }

    public int getLifes() {
        return this.lifes;	
    }

}
