package cz.pohlreichlukas.ludumdare30;

import java.awt.Canvas;
import java.lang.Runnable;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import java.lang.InterruptedException;

import cz.pohlreichlukas.ludumdare30.screens.GameScreen;
import cz.pohlreichlukas.ludumdare30.screens.MainScreen;
import cz.pohlreichlukas.ludumdare30.screens.Screen;
import cz.pohlreichlukas.ludumdare30.input.InputListener;

public class GamePane extends Canvas implements Runnable {

    private GameScreen gameScreen;
    private MainScreen mainScreen;
    private Screen activeScreen;
    private InputListener input;
    private boolean isRunning;

    public GamePane() {
        this.input = new InputListener();
        this.gameScreen = new GameScreen();
        this.mainScreen = new MainScreen();
        this.activeScreen = this.mainScreen;
        this.addMouseListener( this.input );
        this.isRunning = true;
    }

    public void run() {
        int FPS = 0;
        long timer = 0;
        long lastUpdate = System.currentTimeMillis();

        while ( this.isRunning ) {
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
        this.activeScreen.render( this, g );

        g.dispose();
        buffer.show();
    }

    private void update( long delta ) {
        this.activeScreen.update( this, delta );
    }

    public InputListener getInput() {
        return this.input;
    }

    public void changeToGame() {
        this.activeScreen = this.gameScreen;
    }

    public void changeToMenu() {
        this.activeScreen = this.mainScreen;
    }

    public void resetGame() {
        this.changeToMenu();
        this.gameScreen.reset();
    }
    public void quitGame() {
        System.exit(0);
    }
    
}
