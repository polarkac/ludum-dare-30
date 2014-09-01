package cz.pohlreichlukas.ludumdare30.worlds;

import java.awt.Graphics2D;
import java.awt.Color;
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

public class World {
    
    private static Random rnd = new Random();
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
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
        this.asteroids = new ArrayList<Asteroid>();
        this.enemyShips = new ArrayList<EnemyShip>();
        this.particles = new ArrayList<Particle>();
        this.renderedEntities = new ArrayList<Entity>();
        this.player = new Player( 100, 400 );
        this.renderedEntities.add( this.player );
        this.addEntity( new Asteroid( 320, 10 ) );
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

        for ( Entity e : this.renderedEntities ) {
            e.render( g );
        }
    }

    public void update( GamePane pane, long delta ) {
        this.player.update( this, pane, delta );

        if ( this.player.isDead() ) {
            pane.resetGame();
        }

        this.generateAsteroid( pane, delta );
        this.generateEnemyShip( pane, delta );
    }

    public void addEntity( Bullet e ) {
        this.bullets.add( e );
        this.renderedEntities.add( e );
    }

    public void addEntity( Asteroid a ) {
        this.asteroids.add( a );
        this.renderedEntities.add( a );
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
        if ( this.asteroidTimer > 3000 ) {
            this.addEntity( new Asteroid( World.rnd.nextInt( pane.getWidth() ), -100, 100, 100 ) ); 
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
