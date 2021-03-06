package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.components.BulletRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.BulletInputComponent;
import cz.pohlreichlukas.ludumdare30.particles.Particle;
import cz.pohlreichlukas.ludumdare30.sounds.Sound;

public class Bullet extends Entity {
    
    private int speed;

    public Bullet( float x, float y, boolean downDirection, Color c ) {
        super( x, y, 2, 10 );
        this.renderer = new BulletRendererComponent( c );
        this.input = new BulletInputComponent();
        this.speed = 150;
        if ( downDirection ) {
            this.setVelocity( 0, this.speed );
        } else {
            this.setVelocity( 0, -this.speed );
        }
    }

    public boolean isCollidingWith( Entity e ) {
        boolean isColliding = super.isCollidingWith( e );
        if ( e == this.parent ) {
            return false;
        }

        return isColliding;
    }

    public int getSpeed() {
        return this.speed;	
    }

    public void setSpeed( int speed ) {
        this.speed = speed;
    }

    public void hitBy( World world, Entity e ) {
        if ( e != this.parent ) {
            if ( this.getVelocityY() < 0 ) {
                world.addParticle( new Particle( this.getX(), this.getY(), Particle.asteroidHit ) );
            } else {
                world.addParticle( new Particle( this.getX(), this.getY(), Particle.asteroidHitRotated ) );
            }
            this.setIsDead( true );
        }
    }
}
