package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import cz.pohlreichlukas.ludumdare30.entities.Entity;

public class EnemyShipRendererComponent extends RendererComponent {
    
    private BufferedImage enemyShipImage;

    public EnemyShipRendererComponent() {
        try { 
            this.enemyShipImage = ImageIO.read( EnemyShipRendererComponent.class.getResourceAsStream( "/enemy_ship.png" ) );
        } catch ( IOException e ) {
            this.enemyShipImage = null;
            e.printStackTrace();
        }
    }

    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        if ( this.enemyShipImage != null ) {
            g.drawImage( this.enemyShipImage, (int) e.getX(), (int) e.getY(), null );
        }
    }    
}
