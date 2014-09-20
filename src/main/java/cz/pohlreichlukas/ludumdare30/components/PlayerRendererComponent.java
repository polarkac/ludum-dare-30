package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.entities.Player;

public class PlayerRendererComponent extends RendererComponent<Player> {

    public void render( Player e, Graphics2D g ) {
        super.render( e, g );
        g.drawImage( e.getImage(), (int) e.getX(), (int) e.getY(), null );
    }    
}
