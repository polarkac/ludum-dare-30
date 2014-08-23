package cz.pohlreichlukas.ludumdare30.components;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class BulletInputComponent implements InputComponent {
    
    private int liveTimer;

    public BulletInputComponent() {
        this.liveTimer = 4000;
    }

    public void update( Entity e, World world, GamePane pane, long delta ) {
        Bullet b = (Bullet) e;
        if ( b.isDownDirection() ) {
            b.setY( b.getY() + ( b.getSpeed() / 1000f ) * delta );
        } else {
            b.setY( b.getY() - ( b.getSpeed() / 1000f ) * delta );
        }

        this.liveTimer -= delta;
        if ( this.liveTimer <= 0 ) {
            b.setIsDead( true );
        }
    }    
}
