package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.components.RendererComponent;
import cz.pohlreichlukas.ludumdare30.components.InputComponent;
import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;

abstract public class Entity {
    
    private int height;
    private int width;
    private float x, y;
    private float velocityX, velocityY;
    private boolean isDead;
    private Rectangle boundingBox;
    protected RendererComponent renderer;
    protected InputComponent input;
    protected Entity parent;
    protected BufferedImage image;

    public Entity( float x, float y ) {
        this( x, y, 0, 0 );
    }

    public Entity( float x, float y, int width, int height ) {
        this.x = x;
        this.y = y;
        this.velocityX = this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.isDead = false;
        this.boundingBox = new Rectangle( (int) this.x, (int) this.y, this.width, this.height );
        this.parent = null;
    }

    public void render( Graphics2D g ) {
        this.renderer.render( this, g );
    }

    public void update( World world, GamePane pane, long delta ) {
        this.input.update( this, world, pane, delta );
        this.boundingBox.setLocation( (int) this.x, (int) this.y );
    }

    public boolean isCollidingWith( Entity e ) {
        if ( this.isDead() || e.isDead() ) return false;
        if ( e == this ) {
            return false;
        }
        return this.boundingBox.intersects( e.boundingBox );
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

    public void setVelocity( float x, float y ) {
        this.velocityX = x;
        this.velocityY = y;
    }

    public float getVelocityX() {
        return this.velocityX;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public int getWidth() {
        return this.width;	
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;	
    }
    
    public void setHeight( int height ) {
        this.height = height;
    }

    public boolean isDead() {
        return this.isDead;	
    }

    public void setIsDead( boolean isDead ) {
        this.isDead = isDead;
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;	
    }

    public Entity getParent() {
        return this.parent;	
    }

    public void setParent( Entity parent ) {
        this.parent = parent;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
