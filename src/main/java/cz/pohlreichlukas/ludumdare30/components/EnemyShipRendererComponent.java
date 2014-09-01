package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class EnemyShipRendererComponent extends RendererComponent {
    
    private BufferedImage enemyShipImage;

    public EnemyShipRendererComponent() {
        this.enemyShipImage = Bitmap.enemyShip;
    }

    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        if ( this.enemyShipImage != null ) {
            g.drawImage( this.enemyShipImage, (int) e.getX(), (int) e.getY(), null );
        }
    }    
}
