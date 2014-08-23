package cz.pohlreichlukas.ludumdare30.entities;

import cz.pohlreichlukas.ludumdare30.components.BulletRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.BulletInputComponent;

public class Bullet extends Entity {
    
    private boolean downDirection;

    public Bullet( float x, float y, boolean downDirection ) {
        super( x, y );
        this.renderer = new BulletRendererComponent();
        this.input = new BulletInputComponent();
        this.downDirection = downDirection;
    }

    public boolean isDownDirection() {
        return this.downDirection;	
    }

    public void setDownDirection( boolean downDirection ) {
        this.downDirection = downDirection;
    }
}
