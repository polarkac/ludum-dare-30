package cz.pohlreichlukas.ludumdare30.screens;

import java.awt.Graphics2D;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;

public class GameScreen implements Screen {

    private World world;

    public GameScreen() {
        this.world = new World();
    }

    public void render( GamePane pane, Graphics2D g ) {
        this.world.render( pane, g );
    }

    public void update( GamePane pane, long delta ) {
       this.world.update( pane, delta ); 
    }    
}
