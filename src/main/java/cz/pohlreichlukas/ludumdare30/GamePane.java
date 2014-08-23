package cz.pohlreichlukas.ludumdare30;

import java.awt.Canvas;
import java.lang.Runnable;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;

public class GamePane extends Canvas implements Runnable {

    public void run() {
        int FPS = 0;
        long lastUpdate = System.currentTimeMillis();

        while ( true ) {
            long delta = System.currentTimeMillis() - lastUpdate;
            this.update( delta );

            FPS++;
            this.render();
        }
    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();
        if ( buffer == null ) {
            this.createBufferStrategy( 2 );
            return;
        }

        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.fillRect( 20, 20, 50, 60 );

        g.dispose();
        buffer.show();
    }

    private void update( long delta ) {
    
    }
    
}
