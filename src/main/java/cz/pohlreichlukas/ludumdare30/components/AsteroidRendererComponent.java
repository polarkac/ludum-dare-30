package cz.pohlreichlukas.ludumdare30.components;

import java.lang.Math;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class AsteroidRendererComponent extends RendererComponent {

    private BufferedImage asteroidImage;

    public AsteroidRendererComponent() {
        this.asteroidImage = Bitmap.asteroid100;
    }
    
    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        Asteroid a = (Asteroid) e;
        if ( this.asteroidImage != null ) {
            g.rotate( Math.toRadians( a.getAngle() ), a.getX() + a.getWidth() / 2, a.getY() + a.getHeight() / 2 );
            g.drawImage( 
                    this.asteroidImage, 
                    (int) e.getX(), 
                    (int) e.getY(), 
                    e.getWidth(),
                    e.getHeight(),
                    null );
            g.rotate( Math.toRadians( -a.getAngle() ), a.getX() + a.getWidth() / 2, a.getY() + a.getHeight() / 2 );
        }
    }    
}
