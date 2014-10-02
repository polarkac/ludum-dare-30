package cz.pohlreichlukas.ludumdare30.entities;

import java.awt.Graphics2D;
import java.awt.Color;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.worlds.World;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class Portal extends Entity {

    public Portal( float x, float y ) {
        super( x, y, 30, 30 );
        this.image = Bitmap.portal;
    }

    public void update( World world, GamePane pane, long delta ) {
    }

    public void render( Graphics2D g ) {
        g.drawImage( this.image, (int) this.getX(), (int) this.getY(), null );
    }

    public void hitBy( World world, Player e ) {
        this.setIsDead( true );
    }
}
