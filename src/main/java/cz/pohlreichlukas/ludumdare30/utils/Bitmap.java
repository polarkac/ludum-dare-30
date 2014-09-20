package cz.pohlreichlukas.ludumdare30.utils;

import java.awt.Graphics2D;
import java.awt.Color;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.lang.IllegalArgumentException;

public class Bitmap {

    public static BufferedImage asteroid100 = Bitmap.loadImage( "asteroid_100.png" );
    public static BufferedImage player = Bitmap.loadImage( "player.png" );
    public static BufferedImage enemyShip = Bitmap.loadImage( "enemy_ship.png" );

    public static BufferedImage loadImage( String name ) {
        BufferedImage img = null;
        try { 
            img = ImageIO.read( Bitmap.class.getResourceAsStream( "/" + name ) );
        } catch ( IOException|IllegalArgumentException e ) {
            img = new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB );
            Graphics2D g = img.createGraphics();
            g.setColor( Color.red );
            g.fillRect( 0, 0, 64, 64 );
            g.dispose();
        }

        return img;
    }
}
