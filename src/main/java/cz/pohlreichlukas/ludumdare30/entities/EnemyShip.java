package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.components.EnemyShipRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.EnemyShipInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class EnemyShip extends Entity {

    private int lifes;
    private int speed;
    
    public EnemyShip( float x, float y ) {
        super( x, y, 64, 64 );
        this.renderer = new EnemyShipRendererComponent();
        this.input = new EnemyShipInputComponent( x, y );
        this.lifes = 5;
        this.speed = 150;
    }

    public void hitBy( World world, Bullet e ) {
        this.lifes--;
        e.hitBy( world, this );

        if ( this.lifes == 0 ) {
            this.setIsDead( true );
        }
    }

    public int getSpeed() {
        return this.speed;	
    }

    public void setSpeed( int speed ) {
        this.speed = speed;
    }

    public void fire( World world ) {
        Bullet b = new Bullet( this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() + 5, true, Color.green );
        b.setParent( this );
        world.addEntity( b );
    }
}
