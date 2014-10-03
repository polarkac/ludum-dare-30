package cz.pohlreichlukas.ludumdare30.entities;

import java.lang.Math;
import java.util.Random;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.components.AsteroidRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.AsteroidInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.entities.Portal;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class Asteroid extends Entity {

    private int speed;
    private int lifes;
    private float angle;
    
    public Asteroid( float x, float y, int width, int height ) {
        super( x, y, width, height );
        this.renderer = new AsteroidRendererComponent();
        this.input = new AsteroidInputComponent();
        this.speed = 80;
        this.setVelocity( 0, this.speed );
        this.lifes = Math.round( width / 10 );
        this.angle = ( new Random() ).nextInt( 360 );
        this.image = Bitmap.asteroid100;
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

    public void hitBy( World world, Bullet a ) {
        this.lifes--;
        a.hitBy( world, this );

        if ( this.lifes == 0 ) {
            this.setIsDead( true );
            Random rnd = new Random( System.currentTimeMillis() );
            if ( this.getWidth() == 50 && rnd.nextInt( 4 ) == 2 ) {
                world.addEntity( new Portal( this.getX(), this.getY() ) );
            } else if ( this.getWidth() != 50 ) {
                world.addEntity( new Asteroid( this.getX() - 40, this.getY(), 50, 50 ) );
                world.addEntity( new Asteroid( this.getX() + 40, this.getY(), 50, 50 ) );
            }
        }
    }

    public int getLifes() {
        return this.lifes;	
    }

    public float getAngle() {
        return this.angle;	
    }

    public void setAngle( float angle ) {
        this.angle = angle;
    }

    public BufferedImage getImage() {
        return this.image;	
    }
}
