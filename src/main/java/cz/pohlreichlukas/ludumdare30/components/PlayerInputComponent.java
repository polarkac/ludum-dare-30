package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Point;
import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.input.InputListener;

public class PlayerInputComponent implements InputComponent {
    
    private int bulletTimer;

    public PlayerInputComponent() {
        this.bulletTimer = 0;
    }

    public void update( Entity e, GamePane pane, long delta ) {
        Player p = (Player) e;
        Point mousePosition = pane.getMousePosition();
        if ( mousePosition !=  null ) {
            p.setX( (float) mousePosition.getX() - p.getWidth() / 2 );
            p.setY( (float) mousePosition.getY() - p.getHeight() / 2 );
        }

        ArrayList<Bullet> deadBullets = new ArrayList<Bullet>();
        for ( Bullet b : p.getBullets() ) {
            b.update( pane, delta );
            if ( b.isDead() ) {
                deadBullets.add( b );
            }
        }
        p.removeBullets( deadBullets );

        InputListener input = pane.getInput();
        if ( input.isLeftDown() && this.bulletTimer <= 0 ) {
            p.createBullet();
            this.bulletTimer = 200;
        }

        if ( this.bulletTimer > 0 ) this.bulletTimer -= delta;
    }    
}
