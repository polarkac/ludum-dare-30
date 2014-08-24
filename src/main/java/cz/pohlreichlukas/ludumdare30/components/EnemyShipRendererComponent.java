package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Color;
import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.entities.Entity;

public class EnemyShipRendererComponent extends RendererComponent {
    
    public void render( Entity e, Graphics2D g ) {
        g.setColor( Color.black );
        g.fillRect( (int) e.getX(), (int) e.getY(), e.getWidth(), e.getHeight() );
    }    
}
