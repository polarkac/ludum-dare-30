package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class PlayerRendererComponent extends RendererComponent {

    private BufferedImage playerImage;

    public PlayerRendererComponent() {
        this.playerImage = Bitmap.player; 
    }
    
    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        Player p = (Player) e;
        if ( this.playerImage != null ) {
            g.drawImage( this.playerImage, (int) e.getX(), (int) e.getY(), null );
        }
    }    
}
