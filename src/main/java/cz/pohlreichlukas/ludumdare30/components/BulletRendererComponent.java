package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class BulletRendererComponent extends RendererComponent<Bullet> {

    private Color color;

    public BulletRendererComponent( Color c ) {
        this.color = c;
    }

    public void render( Bullet e, Graphics2D g ) {
        super.render( e, g );
        g.setColor( this.color );
        g.fillRect( (int) e.getX(), (int) e.getY(), 2, 10 );
    }    
}
