package cz.pohlreichlukas.ludumdare30.entities;

import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.components.PlayerRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.PlayerInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class Player extends Entity {

    public Player( float x, float y ) {
        this( x, y, 50, 50 );
    }

    public Player( float x, float y, int width, int height ) {
        super( x, y, width, height );
        this.renderer = new PlayerRendererComponent();
        this.input = new PlayerInputComponent();
    }

    public void hit( Entity e ) {
        if ( e.getParent() != this ) {
            this.setIsDead( true );
        }
    }
}
