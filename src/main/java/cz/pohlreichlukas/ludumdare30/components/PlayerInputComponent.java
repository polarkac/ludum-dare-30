package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Point;
import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.input.InputListener;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class PlayerInputComponent implements InputComponent {
    
    private int bulletTimer;

    public PlayerInputComponent() {
        this.bulletTimer = 0;
    }

    public void update( Entity e, World world, GamePane pane, long delta ) {
        Player p = (Player) e;
        Point mousePosition = pane.getMousePosition();
        if ( mousePosition !=  null ) {
            p.setX( (float) mousePosition.getX() - p.getWidth() / 2 );
            p.setY( (float) mousePosition.getY() - p.getHeight() / 2 );
        }

        InputListener input = pane.getInput();
        if ( input.isLeftDown() && this.bulletTimer <= 0 ) {
            this.createBullet( p, world );
            this.bulletTimer = 200;
        }

        if ( this.bulletTimer > 0 ) this.bulletTimer -= delta;
    }    

    private void createBullet( Player p, World world ) {
        Bullet b = new Bullet( p.getX() + p.getWidth() / 2, p.getY() - 15, false );
        b.setParent( p );
        world.addEntity( b );
    }
}
