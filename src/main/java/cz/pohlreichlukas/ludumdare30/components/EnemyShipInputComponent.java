package cz.pohlreichlukas.ludumdare30.components;

import java.util.Random;
import java.awt.Point;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.EnemyShip;

public class EnemyShipInputComponent implements InputComponent<EnemyShip> {

    private Random rnd = new Random( System.currentTimeMillis() );
    private Point actualWaypoint;
    private boolean toRight;
    private int fireTimer;
    private int distancePercent;

    public EnemyShipInputComponent( float x, float y ) {
        this.actualWaypoint = null;
        this.fireTimer = 0;
        this.distancePercent = this.rnd.nextInt( 10000 ) % 80;
        if ( this.distancePercent < 30 ) {
            this.distancePercent = 30;
        }
    }
    
    public void update( EnemyShip e, World world, GamePane pane, long delta ) {
        Player p = world.getPlayer();
        if ( ( this.actualWaypoint == null ) || 
                ( e.getX() > this.actualWaypoint.getX() && e.getVelocityX() > 0 ) || 
                ( e.getX() < this.actualWaypoint.getX() && e.getVelocityX() < 0 ) ) {
            this.regenerateWaypoint( e, pane.getWidth() );
        }

        float differencePlayer = p.getY() - e.getY();
        if ( differencePlayer > pane.getHeight() / 100 * this.distancePercent ) {
            e.setVelocity( e.getVelocityX(), e.getSpeed() );
        } else if ( differencePlayer < pane.getHeight() / 100 * this.distancePercent ) {
            e.setVelocity( e.getVelocityX(), -e.getSpeed() );
        }
        e.setX( e.getX() + e.getVelocityX() / 1000f * delta );
        e.setY( e.getY() + e.getVelocityY() / 1000f * delta );
         
        if ( e.getY() < 0 ) {
            e.setY( 0 );
        }

        boolean inFireZone = e.getX() >= p.getX() && e.getX() < p.getX() + p.getWidth();
        if ( inFireZone && this.fireTimer <= 0 ) {
            e.fire( world );
            this.fireTimer = 200;
        }

        if ( this.fireTimer > 0 ) {
            this.fireTimer -= delta;
        }
    }    

    private void regenerateWaypoint( EnemyShip e, int maxWidth ) {
        if ( this.actualWaypoint == null ) {
            this.actualWaypoint = new Point( 0, 0 );
        }
        int newX = this.rnd.nextInt( 10000000 ) % maxWidth;
        this.actualWaypoint.setLocation( newX, 0 );
        if ( this.actualWaypoint.getX() < e.getX() ) {
            e.setVelocity( -e.getSpeed(), e.getVelocityY() );
        } else {
            e.setVelocity( e.getSpeed(), e.getVelocityY() );
        }
    }
}
