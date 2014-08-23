package cz.pohlreichlukas.ludumdare30.components;

import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.entities.Entity;

public interface RendererComponent {
    
    public void render( Entity e, Graphics2D g );
}
