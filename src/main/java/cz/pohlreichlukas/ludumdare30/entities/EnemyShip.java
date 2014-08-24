package cz.pohlreichlukas.ludumdare30.entities;

import cz.pohlreichlukas.ludumdare30.components.EnemyShipRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.EnemyShipInputComponent;

public class EnemyShip extends Entity {
    
    public EnemyShip( float x, float y ) {
        super( x, y, 64, 64 );
        this.renderer = new EnemyShipRendererComponent();
        this.input = new EnemyShipInputComponent();
    }

    public void hitBy( Entity e ) {}

    public void hitBy( Bullet e ) {
        e.hitBy( this );
    }
}
