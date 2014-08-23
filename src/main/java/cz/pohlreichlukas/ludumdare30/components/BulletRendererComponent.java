package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class BulletRendererComponent implements RendererComponent {
    
    public void render( Entity e, Graphics2D g ) {
        Bullet b = (Bullet) e;
        g.setColor( Color.black );
        g.fillRect( (int) b.getX(), (int) b.getY(), 2, 10 );
    }    
}
