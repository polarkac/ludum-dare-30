package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.input.InputListener;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class PlayerInputComponent implements InputComponent<Player> {
    
    private int bulletTimer;

    public PlayerInputComponent() {
        this.bulletTimer = 0;
    }

    public void update( Player e, World world, GamePane pane, long delta ) {
        Point mousePosition = pane.getMousePosition();
        if ( mousePosition !=  null ) {
            e.setX( (float) mousePosition.getX() - e.getWidth() / 2 );
            e.setY( (float) mousePosition.getY() - e.getHeight() / 2 );
        }

        InputListener input = pane.getInput();
        if ( input.isLeftDown() && this.bulletTimer <= 0 ) {
            this.createBullet( e, world );
            this.bulletTimer = 200;
        }

        if ( this.bulletTimer > 0 ) this.bulletTimer -= delta;
    }    

    private void createBullet( Player p, World world ) {
        Bullet b = new Bullet( p.getX() + p.getWidth() / 2, p.getY() - 15, false, Color.blue );
        b.setParent( p );
        world.addEntity( b );
    }
}
