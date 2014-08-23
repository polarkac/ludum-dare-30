package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Color;
import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class PlayerRendererComponent implements RendererComponent {
    
    public void render( Entity e, Graphics2D g ) {
        Player p = (Player) e;
        g.setColor( Color.red );
        g.fillRect( (int) p.getX(), (int) p.getY(), 50, 50 );

        for ( Bullet b : p.getBullets() ) {
            b.render( g );
        }
    }    
}