package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.components.RendererComponent;
import cz.pohlreichlukas.ludumdare30.components.InputComponent;

abstract public class Entity {
    
    private final int height = 50;
    private final int width = 50;
    private float x, y;
    private boolean isDead;
    protected RendererComponent renderer;
    protected InputComponent input;

    public Entity() {
        this( 0, 0 );
    }

    public Entity( float x, float y ) {
        this.x = x;
        this.y = y;
        this.isDead = false;
    }

    public void render( Graphics2D g ) {
        this.renderer.render( this, g );
    }

    public void update( GamePane pane, long delta ) {
        this.input.update( this, pane, delta );
    }

    public float getX() {
        return this.x;	
    }

    public void setX( float x ) {
        this.x = x;
    }

    public float getY() {
        return this.y;	
    }

    public void setY( float y ) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;	
    }

    public int getHeight() {
        return this.height;	
    }

    public boolean isDead() {
        return this.isDead;	
    }

    public void setIsDead( boolean isDead ) {
        this.isDead = isDead;
    }

}
