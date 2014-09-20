package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.entities.EnemyShip;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class EnemyShipRendererComponent extends RendererComponent<EnemyShip> {
    
    public void render( EnemyShip e, Graphics2D g ) {
        super.render( e, g );
        g.drawImage( e.getImage(), (int) e.getX(), (int) e.getY(), null );
    }    
}
