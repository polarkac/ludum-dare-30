package cz.pohlreichlukas.ludumdare30.entities;

import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.components.PlayerRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.PlayerInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class Player extends Entity {

    private int lifes;

    public Player( float x, float y ) {
        this( x, y, 64, 64 );
    }

    public Player( float x, float y, int width, int height ) {
        super( x, y, width, height );
        this.renderer = new PlayerRendererComponent();
        this.input = new PlayerInputComponent();
        this.lifes = 2;
    }

    public void hit( Entity e ) {
        if ( e.getParent() != this ) {
            this.lifes--;
        }

        if ( this.lifes == 0 ) {
            this.setIsDead( true );
        }
    }
}
