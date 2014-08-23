package cz.pohlreichlukas.ludumdare30.worlds;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Entity;

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
        this.player = new Player();
        this.entities.add( this.player );
        this.background = Color.white;
    }

    public void render( GamePane pane, Graphics2D g ) {
        g.setColor( this.background );
        g.fillRect( 0, 0, pane.getWidth(), pane.getHeight() );
        for ( Entity e : entities ) {
            e.render( g );
        }
    }

    public void update( GamePane pane, long delta ) {
        for ( Entity e : entities ) {
            e.update( this, pane, delta );
            if ( e.isDead() ) {
                this.deadEntities.add( e );
            }
        }

        this.entities.addAll( this.newEntities );
        this.newEntities.clear();
        this.entities.removeAll( this.deadEntities );
        this.deadEntities.clear();
    }

    public void addEntity( Entity e ) {
        this.newEntities.add( e );
    }
}
