package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.components.BulletRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.BulletInputComponent;
import cz.pohlreichlukas.ludumdare30.particles.Particle;
import cz.pohlreichlukas.ludumdare30.sounds.Sound;

public class Bullet extends Entity {
    
    private boolean downDirection;
    private int speed;

    public Bullet( float x, float y, boolean downDirection, Color c ) {
        super( x, y, 2, 10 );
        this.renderer = new BulletRendererComponent( c );
        this.input = new BulletInputComponent();
        this.downDirection = downDirection;
        this.speed = 150;
    }

    public boolean isCollidingWith( Entity e ) {
        boolean isColliding = super.isCollidingWith( e );
        if ( e == this.parent ) {
            return false;
        }

        return isColliding;
    }

    public boolean isDownDirection() {
        return this.downDirection;	
    }

    public void setDownDirection( boolean downDirection ) {
        this.downDirection = downDirection;
    }

    public int getSpeed() {
        return this.speed;	
    }

    public void setSpeed( int speed ) {
        this.speed = speed;
    }

    public void hitBy( World world, Entity e ) {
        //world.addParticle( new Particle( this.getX(), this.getY(), Particle.asteroidHit ) );
        //this.setIsDead( true );
    }
}
