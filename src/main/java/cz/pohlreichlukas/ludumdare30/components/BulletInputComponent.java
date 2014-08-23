package cz.pohlreichlukas.ludumdare30.components;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class BulletInputComponent implements InputComponent {
    
    private int liveTimer;

    public BulletInputComponent() {
        this.liveTimer = 8000;
    }

    public void update( Entity e, GamePane pane, long delta ) {
        Bullet b = (Bullet) e;
        if ( b.isDownDirection() ) {
            b.setY( b.getY() + ( 50f / 1000 ) * delta );
        } else {
            b.setY( b.getY() - ( 50f / 1000 ) * delta );
        }

        this.liveTimer -= delta;
        if ( this.liveTimer <= 0 ) {
            b.setIsDead( true );
        }
    }    
}
