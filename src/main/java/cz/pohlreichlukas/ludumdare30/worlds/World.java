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
import cz.pohlreichlukas.ludumdare30.particles.Particle;

public class World {
    
    private static Random rnd = new Random();
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<Particle> particles;
    private ArrayList<Entity> renderedEntities;
    private Player player;
    private int asteroidTimer;
    private int enemyShipTimer;
    private BufferedImage background;

    public World() {
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

        this.generateBackground();
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
        for ( Particle p : this.particles ) {
            p.render( g );
        }
    }

    public void update( GamePane pane, long delta ) {
        ArrayList<Asteroid> copyOfAsteroids = new ArrayList<Asteroid>( this.asteroids );
        ArrayList<EnemyShip> copyOfEnemyShips = new ArrayList<EnemyShip>( this.enemyShips );

        for ( Bullet bullet : new ArrayList<Bullet>( this.bullets ) ) {
            if ( bullet.isDead() ) {
                this.bullets.remove( bullet );
                this.renderedEntities.remove( bullet );
            } 
            bullet.update( this, pane, delta );
            for ( Asteroid asteroid : copyOfAsteroids ) {
                if ( bullet.isCollidingWith( asteroid ) ) {
                    asteroid.hitBy( this, bullet );
                }
            }
            for ( EnemyShip ship : copyOfEnemyShips ) {
                if ( bullet.isCollidingWith( ship ) ) {
                    ship.hitBy( this, bullet );
                }
            }
            if ( player.isCollidingWith( bullet ) ) {
                player.hitBy( this, bullet );
            }
        }    

        for ( EnemyShip ship : copyOfEnemyShips ) {
            if ( ship.isDead() ) {
                this.enemyShips.remove( ship );
                this.renderedEntities.remove( ship );
            } 
            ship.update( this, pane, delta );
            if ( this.player.isCollidingWith( ship ) ) {
                this.player.hitBy( this, ship );
            }
        }
        for ( Asteroid asteroid : copyOfAsteroids ) {
            if ( asteroid.isDead() ) {
                this.asteroids.remove( asteroid );
                this.renderedEntities.remove( asteroid );
            } 
            asteroid.update( this, pane, delta );
            if ( this.player.isCollidingWith( asteroid ) ) {
                this.player.hitBy( this, asteroid );
            }
        }

        for ( Particle particle : new ArrayList<Particle>( this.particles ) ) {
            if ( particle.isDead() ) {
                this.particles.remove( particle );
            }
            particle.update( this, pane, delta );
        }

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

    public void generateBackground() {
        int random = World.rnd.nextInt( 3 ) + 1;
        try {
            this.background = ImageIO.read( World.class.getResourceAsStream( "/back" + random + ".jpg" ) );
        } catch ( IOException e ) {
            this.background = null;
            e.printStackTrace();
        }
    }
}
