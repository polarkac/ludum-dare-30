package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Color;
import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;

public class PlayerRendererComponent extends RendererComponent {
    
    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        Player p = (Player) e;
        g.setColor( Color.red );
        g.fillRect( (int) p.getX(), (int) p.getY(), 50, 50 );
    }    
}
