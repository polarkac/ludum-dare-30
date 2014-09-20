package cz.pohlreichlukas.ludumdare30.components;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class BulletInputComponent implements InputComponent<Bullet> {
    
    private int liveTimer;

    public BulletInputComponent() {
        this.liveTimer = 4000;
    }

    public void update( Bullet e, World world, GamePane pane, long delta ) {
        if ( e.isDownDirection() ) {
            e.setY( e.getY() + ( e.getSpeed() / 1000f ) * delta );
        } else {
            e.setY( e.getY() - ( e.getSpeed() / 1000f ) * delta );
        }

        this.liveTimer -= delta;
        if ( this.liveTimer <= 0 ) {
            e.setIsDead( true );
        }
    }    
}
