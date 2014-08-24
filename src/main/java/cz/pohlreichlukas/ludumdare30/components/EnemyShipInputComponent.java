package cz.pohlreichlukas.ludumdare30.components;

import java.util.Random;
import java.awt.Point;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.EnemyShip;

public class EnemyShipInputComponent implements InputComponent {

    private Random rnd = new Random( System.currentTimeMillis() );
    private Point actualWaypoint;
    private boolean toRight;
    private int fireTimer;
    private int distancePercent;

    public EnemyShipInputComponent( float x, float y ) {
        this.actualWaypoint = new Point( 0, 0 );
        this.regenerateWaypoint( 200, x );
        this.fireTimer = 0;
        this.distancePercent = this.rnd.nextInt( 10000 ) % 80;
        if ( this.distancePercent < 30 ) {
            this.distancePercent = 30;
        }
    }
    
    public void update( Entity e, World world, GamePane pane, long delta ) {
        Player p = world.getPlayer();
        EnemyShip ship = (EnemyShip) e;
        if ( this.toRight ) {
            ship.setX( ship.getX() + ship.getSpeed() / 1000f * delta );
            if ( ship.getX() > this.actualWaypoint.getX() ) {
                this.regenerateWaypoint( pane.getWidth(), ship.getX() );
            }
        } else {
            ship.setX( ship.getX() - ship.getSpeed() / 1000f * delta );
            if ( ship.getX() < this.actualWaypoint.getX() ) {
                this.regenerateWaypoint( pane.getWidth(), ship.getX() );
            }
        }

        float differencePlayer = p.getY() - ship.getY();
        if ( differencePlayer > pane.getHeight() / 100 * this.distancePercent ) {
            ship.setY( ship.getY() + ship.getSpeed() / 1000f * delta );
        } else if ( differencePlayer < pane.getHeight() / 100 * this.distancePercent ) {
            ship.setY( ship.getY() - ship.getSpeed() / 1000f * delta );
        }
         
        if ( ship.getY() < 0 ) {
            ship.setY( 0 );
        }

        boolean inFireZone = ship.getX() >= p.getX() && ship.getX() < p.getX() + p.getWidth();
        if ( inFireZone && this.fireTimer <= 0 ) {
            ship.fire( world );
            this.fireTimer = 200;
        }

        if ( this.fireTimer > 0 ) {
            this.fireTimer -= delta;
        }
    }    

    private void regenerateWaypoint( int maxWidth, float x ) {
        int newX = this.rnd.nextInt( 10000000 ) % maxWidth;
        this.actualWaypoint.setLocation( newX, 0 );
        if ( this.actualWaypoint.getX() < x ) {
            this.toRight = false;
        } else {
            this.toRight = true;
        }
    }
}
