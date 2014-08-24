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
import cz.pohlreichlukas.ludumdare30.entities.EnemyShip;

public class World {
    
    private static Random rnd = new Random();
    private Color background;
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<EnemyShip> enemyShips;
    private ArrayList<Entity> renderedEntities;
    private Player player;
    private int asteroidTimer;
    private int enemyShipTimer;

    public World() {
        this.bullets = new ArrayList<Bullet>();
        this.asteroids = new ArrayList<Asteroid>();
        this.enemyShips = new ArrayList<EnemyShip>();
        this.renderedEntities = new ArrayList<Entity>();
        this.player = new Player( 100, 400 );
        this.background = Color.white;
        this.asteroids.add( new Asteroid( 320, 10 ) );
        this.asteroidTimer = 0;
        this.enemyShipTimer = 0;
    }

    public void render( GamePane pane, Graphics2D g ) {
        g.setColor( this.background );
        g.fillRect( 0, 0, pane.getWidth(), pane.getHeight() );

        this.renderedEntities.clear();
        this.renderedEntities.addAll( this.bullets );
        this.renderedEntities.addAll( this.asteroids );
        this.renderedEntities.addAll( this.enemyShips );
        this.renderedEntities.add( this.player );
        for ( Entity e : this.renderedEntities ) {
            e.render( g );
        }
    }

    public void update( GamePane pane, long delta ) {
        ArrayList<Asteroid> copyOfAsteroids = new ArrayList<Asteroid>( this.asteroids );
        ArrayList<EnemyShip> copyOfEnemyShips = new ArrayList<EnemyShip>( this.enemyShips );

        for ( Bullet bullet : new ArrayList<Bullet>( this.bullets ) ) {
            if ( bullet.isDead() ) {
                this.bullets.remove( bullet );
            } 
            bullet.update( this, pane, delta );
            for ( Asteroid asteroid : copyOfAsteroids ) {
                if ( bullet.isCollidingWith( asteroid ) ) {
                    asteroid.hitBy( bullet );
                }
            }
            for ( EnemyShip ship : copyOfEnemyShips ) {
                if ( bullet.isCollidingWith( ship ) ) {
                    ship.hitBy( bullet );
                }
            }
        }    

        for ( EnemyShip ship : copyOfEnemyShips ) {
            if ( ship.isDead() ) {
                this.enemyShips.remove( ship );
            } 
            ship.update( this, pane, delta );
            if ( this.player.isCollidingWith( ship ) ) {
                this.player.hitBy( ship );
            }
        }
        for ( Asteroid asteroid : copyOfAsteroids ) {
            if ( asteroid.isDead() ) {
                this.asteroids.remove( asteroid );
            } 
            asteroid.update( this, pane, delta );
            if ( this.player.isCollidingWith( asteroid ) ) {
                this.player.hitBy( asteroid );
            }
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
    }

    public void addEntity( Asteroid a ) {
        this.asteroids.add( a );
    }

    public void addEntity( EnemyShip e ) {
        this.enemyShips.add( e );
    }

    private void generateAsteroid( GamePane pane, long delta ) {
        if ( this.asteroidTimer > 3000 ) {
            this.addEntity( new Asteroid( World.rnd.nextInt( pane.getWidth() ), 10, 100, 100 ) ); 
            this.asteroidTimer = 0;
        }

        this.asteroidTimer += delta;
    }

    private void generateEnemyShip( GamePane pane, long delta ) {
        if ( this.enemyShipTimer > 2000 ) {
            this.addEntity( new EnemyShip( World.rnd.nextInt( pane.getWidth() ), 10 ) ); 
            this.enemyShipTimer = 0;
        }

        this.enemyShipTimer += delta;
    } 
}
