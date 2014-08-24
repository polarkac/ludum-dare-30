package cz.pohlreichlukas.ludumdare30.worlds;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class World {
    
    private static Random rnd = new Random();
    private Color background;
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Entity> renderedEntities;
    private Player player;
    private int asteroidTimer;

    public World() {
        this.bullets = new ArrayList<Bullet>();
        this.asteroids = new ArrayList<Asteroid>();
        this.renderedEntities = new ArrayList<Entity>();
        this.player = new Player( 100, 400 );
        this.background = Color.white;
        this.asteroids.add( new Asteroid( 320, 10 ) );
        this.asteroidTimer = 0;
    }

    public void render( GamePane pane, Graphics2D g ) {
        g.setColor( this.background );
        g.fillRect( 0, 0, pane.getWidth(), pane.getHeight() );

        this.renderedEntities.clear();
        this.renderedEntities.addAll( this.bullets );
        this.renderedEntities.addAll( this.asteroids );
        this.renderedEntities.add( this.player );
        for ( Entity e : this.renderedEntities ) {
            e.render( g );
        }
    }

    public void update( GamePane pane, long delta ) {
        ArrayList<Asteroid> copyOfAsteroids = new ArrayList<Asteroid>( this.asteroids );

        for ( Bullet bullet : new ArrayList<Bullet>( this.bullets ) ) {
            bullet.update( this, pane, delta );
            for ( Asteroid asteroid : copyOfAsteroids ) {
                if ( bullet.isCollidingWith( asteroid ) ) {
                    asteroid.hitBy( bullet );
                }
                if ( this.player.isCollidingWith( asteroid ) ) {
                    this.player.hitBy( asteroid );
                }
            }
            if ( bullet.isDead() ) {
                this.bullets.remove( bullet );
            }
        }    

        for ( Asteroid asteroid : copyOfAsteroids ) {
            asteroid.update( this, pane, delta );
            if ( asteroid.isDead() ) {
                this.asteroids.remove( asteroid );
            }
        }

        this.player.update( this, pane, delta );

        if ( this.player.isDead() ) {
            pane.resetGame();
        }

        this.generateAsteroid( pane, delta );
    }

    public void addEntity( Bullet e ) {
        this.bullets.add( e );
    }

    public void addEntity( Asteroid a ) {
        this.asteroids.add( a );
    }

    public void generateAsteroid( GamePane pane, long delta ) {
        if ( this.asteroidTimer > 3000 ) {
            this.addEntity( new Asteroid( World.rnd.nextInt( pane.getWidth() ), 10 ) ); 
            this.asteroidTimer = 0;
        }

        this.asteroidTimer += delta;
    }
}
