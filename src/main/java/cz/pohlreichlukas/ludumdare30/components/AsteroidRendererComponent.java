package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
        if ( this.asteroidImage != null ) {
            g.drawImage( 
                    this.asteroidImage, 
                    (int) e.getX(), 
                    (int) e.getY(), 
                    e.getWidth(),
                    e.getHeight(),
                    null );
        }
    }    
}
