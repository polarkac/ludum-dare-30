package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.Game;

abstract public class RendererComponent {
    
    public void render( Entity e, Graphics2D g ) {
        if ( Game.IS_DEBUG ) {
            g.setColor( Color.green );
            Rectangle bb = e.getBoundingBox();
            g.drawRect( (int) bb.getX(), (int) bb.getY(), (int) bb.getWidth(), (int) bb.getHeight() );
        }
    }
}
