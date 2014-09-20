package cz.pohlreichlukas.ludumdare30.components;

import java.lang.Math;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.entities.Asteroid;

public class AsteroidRendererComponent extends RendererComponent<Asteroid> {

    public void render( Asteroid e, Graphics2D g ) {
        super.render( e, g );
        g.rotate( 
                Math.toRadians( e.getAngle() ), 
                e.getX() + e.getWidth() / 2, 
                e.getY() + e.getHeight() / 2 
        );
        g.drawImage( 
                e.getImage(), 
                (int) e.getX(), 
                (int) e.getY(), 
                e.getWidth(),
                e.getHeight(),
                null );
        g.rotate( 
                Math.toRadians( -e.getAngle() ), 
                e.getX() + e.getWidth() / 2, 
                e.getY() + e.getHeight() / 2 
        );
    }    
}
