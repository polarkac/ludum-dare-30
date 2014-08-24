package cz.pohlreichlukas.ludumdare30.entities;

import java.lang.Math;

import cz.pohlreichlukas.ludumdare30.components.AsteroidRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.AsteroidInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class Asteroid extends Entity {

    private int speed;
    private int lifes;
    
    public Asteroid( float x, float y, int width, int height ) {
        super( x, y, width, height );
        this.renderer = new AsteroidRendererComponent();
        this.input = new AsteroidInputComponent();
        this.speed = 80;
        this.lifes = Math.round( width / 5 );
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

    public void hitBy( World world, Entity e ) {
        
    }

    public void hitBy( World world, Bullet a ) {
        this.lifes--;
        a.hitBy( world, this );

        if ( this.lifes == 0 ) {
            this.setIsDead( true );
        }
    }

    public int getLifes() {
        return this.lifes;	
    }

}
