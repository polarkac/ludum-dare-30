package cz.pohlreichlukas.ludumdare30.screens;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Point;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.input.InputListener;
import cz.pohlreichlukas.ludumdare30.entities.Player;

public class GameScreen implements Screen {

    private Player player;

    public GameScreen() {
        this.player = new Player();
    }

    public void render( Graphics2D g ) {
        this.player.render( g );
    }

    public void update( GamePane pane, long delta ) {
       this.player.update( pane, delta ); 
    }    
}
