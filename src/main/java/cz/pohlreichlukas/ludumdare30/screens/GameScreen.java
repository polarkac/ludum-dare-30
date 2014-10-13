package cz.pohlreichlukas.ludumdare30.screens;

import java.awt.Graphics2D;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.entities.Player;

public class GameScreen implements Screen {

    private World world;
    private int score;
    private int worldNumber;

    public GameScreen() {
        this.score = 0;
        this.worldNumber = 1;
        this.world = new World( 1 );
    }

    public void render( GamePane pane, Graphics2D g ) {
        this.world.render( pane, g );

        Player p = this.world.getPlayer();
        g.setColor( Color.red );
        for ( int a = 0; a < p.getLifes(); a++ ) {
            g.fillOval( 50 * a + 10, pane.getHeight() - 50, 25, 25 );
        }
    }

    public void update( GamePane pane, long delta ) {
        pane.hideCursor();
        pane.getInput().setLockMouse( true );
        this.world.update( pane, delta ); 
    }    

    public void reset() {
        this.score = 1;
        this.world = new World( this.score );
    }

    public void reset( boolean isNew ) {
        this.score++;
        this.worldNumber++;
        if ( this.worldNumber > 3 ) {
            this.worldNumber = 1;
        }
        this.world = new World( this.worldNumber );
    }
}
