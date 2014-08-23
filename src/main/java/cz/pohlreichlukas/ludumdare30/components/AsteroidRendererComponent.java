package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.entities.Entity;

public class AsteroidRendererComponent extends RendererComponent {
    
    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        g.setColor( Color.black );
        g.fillOval( (int) e.getX(), (int) e.getY(), e.getWidth(), e.getHeight() );
    }    
}
