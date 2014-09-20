package cz.pohlreichlukas.ludumdare30.particles;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.GamePane;

public class Particle {
    
    public static BufferedImage asteroidHit = Particle.loadImage( "asteroid_hit.png" );
    public static BufferedImage asteroidHitRotated = Particle.createRotatedImage( Particle.asteroidHit, 180 );

    private float x;
    private float y;
    private BufferedImage particleImage;
    private int particleTimer;
    private boolean isDead;

    public Particle( float x, float y, BufferedImage particleImage ) {
        this.x = x;
        this.y = y;
        this.particleImage = particleImage;
        this.particleTimer = 0;
        this.isDead = false;
    }

    public void update( World world, GamePane pane, long delta ) {
        this.particleTimer += delta;

        if ( this.particleTimer > 300 ) {
            this.isDead = true;
        }
    }
    
    public void render( Graphics2D g ) {
        g.drawImage( this.particleImage, (int) this.x, (int) this.y, null );
    }

    public boolean isDead() {
        return this.isDead;	
    }

    public static BufferedImage loadImage( String name ) {
        BufferedImage temp = null;
         try {
            temp = ImageIO.read( Particle.class.getResourceAsStream( "/particles/" + name ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return temp;
    }

    public static BufferedImage createRotatedImage( BufferedImage img, int angle ) {
        BufferedImage rotatedImage = new BufferedImage( 
                img.getWidth(),
                img.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = rotatedImage.createGraphics();
        g.rotate( Math.toRadians( angle ), img.getWidth() / 2, img.getHeight() / 2 );
        g.drawImage( img, 0, 0, null );
        g.dispose();

        return rotatedImage;
    }
}
