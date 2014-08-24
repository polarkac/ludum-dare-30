package cz.pohlreichlukas.ludumdare30.entities;

import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.components.PlayerRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.PlayerInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class Player extends Entity {

    private int lifes;

    public Player( float x, float y ) {
        this( x, y, 64, 64 );
    }

    public Player( float x, float y, int width, int height ) {
        super( x, y, width, height );
        this.renderer = new PlayerRendererComponent();
        this.input = new PlayerInputComponent();
        this.lifes = 5;
        this.getBoundingBox().setSize( 50, 50 );
        this.getBoundingBox().setLocation( (int) x + 12, (int) y + 12 );
    }

    public void update( World world, GamePane pane, long delta ) {
        this.input.update( this, world, pane, delta );
        this.getBoundingBox().setLocation( (int) this.getX() + 6, (int) this.getY() + 6 );
    } 

    public void hitBy( World world, Bullet e ) {
        if ( e.getParent() != this && !e.isDead() ) {
            this.lifes--;
        }

        e.hitBy( world, this );
        if ( this.lifes == 0 ) {
            this.setIsDead( true );
        }
    }

    public void hitBy( World world, Entity e ) {
        this.setIsDead( true );
    }

    public int getLifes() {
        return this.lifes;
    }
}
