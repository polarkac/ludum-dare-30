package cz.pohlreichlukas.ludumdare30.screens;

import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.GamePane;

public interface Screen {
    
    public void render( GamePane pane, Graphics2D g );
    public void update( GamePane pane, long delta );
}
