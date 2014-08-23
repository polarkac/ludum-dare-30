package cz.pohlreichlukas.ludumdare30.worlds;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Asteroid;

public class World {
    
    private Color background;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> deadEntities;
    private ArrayList<Entity> newEntities;
    private Player player;

    public World() {
        this.entities = new ArrayList<Entity>();
        this.deadEntities = new ArrayList<Entity>();
        this.newEntities = new ArrayList<Entity>();
        this.player = new Player( 100, 400 );
        this.entities.add( this.player );
        this.background = Color.white;
        this.entities.add( new Asteroid() );
    }

    public void render( GamePane pane, Graphics2D g ) {
        g.setColor( this.background );
        g.fillRect( 0, 0, pane.getWidth(), pane.getHeight() );
        for ( Entity e : entities ) {
            e.render( g );
        }
    }

    public void update( GamePane pane, long delta ) {
        for ( Entity e : this.entities ) {
            e.update( this, pane, delta );
            if ( e.isDead() ) {
                this.deadEntities.add( e );
            }
            for ( Entity ee : this.entities ) {
                if ( ee.isCollidingWith( e ) ) {
                    ee.hit( e );
                }
            }
        }

        this.entities.addAll( this.newEntities );
        this.newEntities.clear();
        this.entities.removeAll( this.deadEntities );
        this.deadEntities.clear();

        if ( this.player.isDead() ) {
            pane.resetGame();
        }
    }

    public void addEntity( Entity e ) {
        this.newEntities.add( e );
    }
}
