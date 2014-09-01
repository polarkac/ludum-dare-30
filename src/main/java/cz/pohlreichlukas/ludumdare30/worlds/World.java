package cz.pohlreichlukas.ludumdare30.worlds;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.entities.EnemyShip;
import cz.pohlreichlukas.ludumdare30.entities.Portal;
import cz.pohlreichlukas.ludumdare30.particles.Particle;
import cz.pohlreichlukas.ludumdare30.utils.QuadTree;

public class World {
    
    private static Random rnd = new Random();
    private ArrayList<Bullet> bullets;
    private QuadTree<Asteroid> asteroids;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<Particle> particles;
    private ArrayList<Entity> renderedEntities;
    private Portal portal;
    private Player player;
    private int asteroidTimer;
    private int enemyShipTimer;
    private BufferedImage background;

    public World( int numberBack ) {
        this.bullets = new ArrayList<Bullet>();
        this.enemyShips = new ArrayList<EnemyShip>();
        this.particles = new ArrayList<Particle>();
        this.asteroids = new QuadTree<Asteroid>( 0, 0, 800, 600 );
        this.renderedEntities = new ArrayList<Entity>();
        this.player = new Player( 100, 400 );
        this.asteroidTimer = 0;
        this.enemyShipTimer = 0;

        this.generateBackground( numberBack );
    }

    public void render( GamePane pane, Graphics2D g ) {
        if ( this.background == null ) {
            g.setColor( Color.white );
            g.fillRect( 0, 0, pane.getWidth(), pane.getHeight() );
        } else {
            g.drawImage( this.background, 0, 0, null );
        }
        
        this.renderedEntities.clear();
        this.renderedEntities.add( this.player );
        this.renderedEntities.addAll( this.asteroids.getEntities( new Rectangle( 0, 0, 800, 600 ) ) );
        for ( Entity en : this.renderedEntities ) {
            en.render( g );
        }

        this.renderQuadTree( this.asteroids, g );
    }

    private void renderQuadTree( QuadTree t, Graphics2D g ) {
        Rectangle r = t.getRegion();
        g.setColor( Color.green );
        g.drawRect( (int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight() );

        if ( t.getNorthWest() != null ) {
            this.renderQuadTree( t.getNorthWest(), g );
            this.renderQuadTree( t.getNorthEast(), g );
            this.renderQuadTree( t.getSouthWest(), g );
            this.renderQuadTree( t.getSouthEast(), g );
        }
    }

    public void update( GamePane pane, long delta ) {
        if ( this.player.isDead() ) {
            pane.resetGame();
        }

        this.player.update( this, pane, delta );

        ArrayList<Asteroid> asteroids = this.asteroids.getEntities( new Rectangle( 0, 0, 800, 600 ) );
        this.asteroids.clear();
        for ( Asteroid a : asteroids ) {
            a.update( this, pane, delta );
            this.asteroids.insert( a );
        }

        Rectangle r = new Rectangle( this.player.getBoundingBox() );
        r.grow( 20, 20 );
        asteroids = this.asteroids.getEntities( r );
        for ( Asteroid a : asteroids ) {
            if ( this.player.isCollidingWith( a ) ) {
                this.player.setIsDead( true );
            }
        }

        this.generateAsteroid( pane, delta );
        //this.generateEnemyShip( pane, delta );
    }

    public void addEntity( Bullet e ) {
        this.bullets.add( e );
        this.renderedEntities.add( e );
    }

    public void addEntity( Asteroid a ) {
        System.out.println( "ASTEROID" );
        this.asteroids.insert( a );
    }

    public void addEntity( EnemyShip e ) {
        this.enemyShips.add( e );
        this.renderedEntities.add( e );
    }

    public void addEntity( Portal p ) {
        this.renderedEntities.remove( this.portal );
        this.renderedEntities.add( p );
        this.portal = p;
    }

    public void addParticle( Particle p ) {
        this.particles.add( p );
    }

    private void generateAsteroid( GamePane pane, long delta ) {
        if ( this.asteroidTimer > 1000 ) {
            this.addEntity( new Asteroid( World.rnd.nextInt( pane.getWidth() - 100 ), 100, 100, 100 ) ); 
            this.asteroidTimer = 0;
        }

        this.asteroidTimer += delta;
    }

    private void generateEnemyShip( GamePane pane, long delta ) {
        if ( this.enemyShipTimer > 2000 && this.enemyShips.size() < 10 ) {
            this.addEntity( new EnemyShip( World.rnd.nextInt( pane.getWidth() ), 10 ) ); 
            this.enemyShipTimer = 0;
        }

        if ( this.enemyShips.size() < 10 ) {
            this.enemyShipTimer += delta;
        }
    } 

    public Player getPlayer() {
        return this.player;	
    }

    public void generateBackground( int number ) {
        try {
            this.background = ImageIO.read( World.class.getResourceAsStream( "/back" + number + ".jpg" ) );
        } catch ( IOException e ) {
            this.background = null;
            e.printStackTrace();
        }
    }
}
