package cz.pohlreichlukas.ludumdare30;

import java.awt.Canvas;
import java.lang.Runnable;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import java.lang.InterruptedException;

import cz.pohlreichlukas.ludumdare30.screens.GameScreen;
import cz.pohlreichlukas.ludumdare30.input.InputListener;

public class GamePane extends Canvas implements Runnable {

    private GameScreen gameScreen;
    private InputListener input;

    public GamePane() {
        this.input = new InputListener();
        this.gameScreen = new GameScreen();
        this.addMouseListener( this.input );
    }

    public void run() {
        int FPS = 0;
        long timer = 0;
        long lastUpdate = System.currentTimeMillis();

        while ( true ) {
            long delta = System.currentTimeMillis() - lastUpdate;
            this.update( delta );
            timer += delta;
            lastUpdate = System.currentTimeMillis();

            FPS++;
            this.render();

            try {
                Thread.sleep( 1 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }

            if ( timer > 1000 ) {
                System.out.println( "FPS: " + FPS );
                FPS = 0;
                timer = 0;
            }
       }
    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();
        if ( buffer == null ) {
            this.createBufferStrategy( 2 );
            return;
        }

        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setColor( this.getBackground() );
        g.fillRect( 0, 0, this.getWidth(), this.getHeight() );
        this.gameScreen.render( this, g );

        g.dispose();
        buffer.show();
    }

    private void update( long delta ) {
        this.gameScreen.update( this, delta );
    }

    public InputListener getInput() {
        return this.input;
    }
    
}
