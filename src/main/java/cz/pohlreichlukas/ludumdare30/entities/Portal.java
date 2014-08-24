package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Graphics2D;
import java.awt.Color;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class Portal extends Entity {

    private BufferedImage portalImage;
    
    public Portal( float x, float y ) {
        super( x, y, 30, 30 );

        try {
            this.portalImage = ImageIO.read( Portal.class.getResourceAsStream( "/portal.png" ) );
        } catch ( IOException e ) {
            this.portalImage = null;
            e.printStackTrace();
        }
    }

    public void update( World world, GamePane pane, long delta ) {
    }

    public void render( Graphics2D g ) {
        g.drawImage( this.portalImage, (int) this.getX(), (int) this.getY(), null );
    }

    public void hitBy( World world, Entity e ) {
    
    }

    public void hitBy( World world, Player e ) {
        this.setIsDead( true );
    }
}
