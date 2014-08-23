package cz.pohlreichlukas.ludumdare30.entities;

import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.components.PlayerRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.PlayerInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class Player extends Entity {

    private ArrayList<Bullet> bullets;
    
    public Player() {
        super( 0, 0 );
        this.renderer = new PlayerRendererComponent();
        this.input = new PlayerInputComponent();
        this.bullets = new ArrayList<Bullet>();
    }

    public void createBullet() {
        Bullet b = new Bullet( this.getX() + this.getWidth() / 2, this.getY() - 5, false );
        this.bullets.add( b );
    }

    public void removeBullets( ArrayList<Bullet> b ) {
        this.bullets.removeAll( b );
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }
}
