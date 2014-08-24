package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class BulletRendererComponent extends RendererComponent {

    private Color color;

    public BulletRendererComponent( Color c ) {
        this.color = c;
    }

    public void render( Entity e, Graphics2D g ) {
        super.render( e, g );
        Bullet b = (Bullet) e;
        g.setColor( this.color );
        g.fillRect( (int) b.getX(), (int) b.getY(), 2, 10 );
    }    
}
