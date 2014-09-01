package cz.pohlreichlukas.ludumdare30.components;

import java.lang.Math;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.entities.Entity;

public class AsteroidRendererComponent extends RendererComponent {

    private BufferedImage asteroidImage;

    public AsteroidRendererComponent() {
        try { 
            this.asteroidImage = ImageIO.read( AsteroidRendererComponent.class.getResourceAsStream( "/asteroid_100.png" ) );
        } catch ( IOException e ) {
            this.asteroidImage = null;
            e.printStackTrace();
        }
    }
    
    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        Asteroid a = (Asteroid) e;
        /*if ( this.asteroidImage != null ) {
            g.rotate( Math.toRadians( a.getImgAngle() ), a.getX() + a.getWidth() / 2, a.getY() + a.getHeight() / 2 );
            g.drawImage( 
                    this.asteroidImage, 
                    (int) e.getX(), 
                    (int) e.getY(), 
                    e.getWidth(),
                    e.getHeight(),
                    null );
            g.rotate( Math.toRadians( -a.getImgAngle() ), a.getX() + a.getWidth() / 2, a.getY() + a.getHeight() / 2 );
        }*/

        g.setColor( Color.white );
        Rectangle r = e.getBoundingBox();
        int x = (int) r.getX();
        int y = (int) r.getY();
        int width = (int) r.getWidth();
        int height = (int) r.getHeight();
        g.fillOval( x - 2 + width / 2, y - 2 + height / 2, 5, 5 );
    }    
}
