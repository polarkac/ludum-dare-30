package cz.pohlreichlukas.ludumdare30.entities;

import cz.pohlreichlukas.ludumdare30.components.BulletRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.BulletInputComponent;

public class Bullet extends Entity {
    
    private boolean downDirection;
    private int speed;

    public Bullet( float x, float y, boolean downDirection ) {
        super( x, y, 2, 10 );
        this.renderer = new BulletRendererComponent();
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

    public void hit( Entity e ) {
        this.setIsDead( true );
    }
}
