package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;

public class PlayerRendererComponent extends RendererComponent {

    private BufferedImage playerImage;

    public PlayerRendererComponent() {
        try {
            this.playerImage = ImageIO.read( PlayerRendererComponent.class.getResourceAsStream( "/player.png" ) );
        } catch ( IOException e ) {
            this.playerImage = null;
            e.printStackTrace();
        }
    }
    
    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        Player p = (Player) e;
        if ( this.playerImage != null ) {
            g.drawImage( this.playerImage, (int) e.getX(), (int) e.getY(), null );
        }
    }    
}
