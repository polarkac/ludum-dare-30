package cz.pohlreichlukas.ludumdare30.worlds;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.entities.Player;
import cz.pohlreichlukas.ludumdare30.entities.Entity;
import cz.pohlreichlukas.ludumdare30.entities.Asteroid;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;
import cz.pohlreichlukas.ludumdare30.entities.EnemyShip;
import cz.pohlreichlukas.ludumdare30.entities.Portal;
import cz.pohlreichlukas.ludumdare30.particles.Particle;
import cz.pohlreichlukas.ludumdare30.utils.QuadTree;
import cz.pohlreichlukas.ludumdare30.utils.Bitmap;

public class World {
    
    private static Random rnd = new Random();

    private ArrayList<Bullet> bullets;
    private QuadTree<Bullet> bulletTree;

    private ArrayList<Asteroid> asteroids;
    private QuadTree<Asteroid> asteroidTree;

    private ArrayList<EnemyShip> enemyShips;
    private QuadTree<EnemyShip> enemyShipTree;

    private ArrayList<Particle> particles;
    private Portal portal;
    private Player player;
    private int asteroidTimer;
    private int enemyShipTimer;
    private BufferedImage background;

    public World( int numberBack ) {
        this.particles = new ArrayList<Particle>();

        this.bullets = new ArrayList<Bullet>();
        this.bulletTree = new QuadTree<Bullet>( 0, 0, 800, 600 );

        this.enemyShips = new ArrayList<EnemyShip>();
        this.enemyShipTree = new QuadTree<EnemyShip>( 0, 0, 800, 600 );

        this.asteroids = new ArrayList<Asteroid>();
        this.asteroidTree = new QuadTree<Asteroid>( 0, 0, 800, 600 );

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

        if ( this.portal != null ) {
            this.portal.render( g );
        }
        this.player.render( g );
        
        for ( Asteroid a : this.asteroids ) {
            a.render( g );
        }

        for ( EnemyShip e : this.enemyShips ) {
            e.render( g );
        }

        for ( Bullet b : this.bullets ) {
            b.render( g );
        }

        for ( Particle p : this.particles ) {
            p.render( g );
        }

        this.renderQuadTree( this.bulletTree, g );
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

        for ( Particle p : new ArrayList<Particle>( this.particles ) ) {
            p.update( this, pane, delta );
            if ( p.isDead() ) {
                this.particles.remove( p );
            }
        }

        this.asteroidTree.clear();
        for ( Asteroid a : new ArrayList<Asteroid>( this.asteroids ) ) {
            a.update( this, pane, delta );
            if ( a.isDead() ) {
                this.asteroids.remove( a );
            } else {
                this.asteroidTree.insert( a );
            }
        }

        if ( this.portal != null && this.player.isCollidingWith( this.portal ) ) {
            pane.newGame();
        }

        this.bulletTree.clear();
        Rectangle bulletBox = new Rectangle( 0, 0, 0, 0 );
        for ( Bullet b : new ArrayList<Bullet>( this.bullets ) ) {
            b.update( this, pane, delta );
            if ( b.isDead() ) {
                this.bullets.remove( b );
            } else {
                this.bulletTree.insert( b );
            }
            bulletBox.setBounds( b.getBoundingBox() );
            bulletBox.grow( 5, 5 );
            for ( Asteroid a : this.asteroidTree.getEntities( bulletBox ) ) {
                if ( a.isCollidingWith( b ) ) {
                    a.hitBy( this, b );
                }
            }
            for ( EnemyShip e : this.enemyShipTree.getEntities( bulletBox ) ) {
                if ( e.isCollidingWith( b ) ) {
                    e.hitBy( this, b );
                }
            }
        }

        this.enemyShipTree.clear();
        for ( EnemyShip e : new ArrayList<EnemyShip>( this.enemyShips ) ) {
            e.update( this, pane, delta );
            if ( e.isDead() ) {
                this.enemyShips.remove( e );
            } else {
                this.enemyShipTree.insert( e );
            }
        }

        Rectangle playerSearchBox = new Rectangle( this.player.getBoundingBox() );
        playerSearchBox.grow( 20, 20 );
        for ( Asteroid a : this.asteroidTree.getEntities( playerSearchBox ) ) {
            if ( this.player.isCollidingWith( a ) ) {
                this.player.hitBy( this, a );
            }
        }

        for ( Bullet b : this.bulletTree.getEntities( playerSearchBox ) ) {
            if ( this.player.isCollidingWith( b ) ) {
                this.player.hitBy( this, b );
            }
        }

        for ( EnemyShip e : this.enemyShipTree.getEntities( playerSearchBox ) ) {
            if ( this.player.isCollidingWith( e ) ) {
                this.player.hitBy( this, e );
            }
        }

        this.generateAsteroid( pane, delta );
        this.generateEnemyShip( pane, delta );
    }

    public void addEntity( Bullet e ) {
        this.bullets.add( e );
        this.bulletTree.insert( e );
    }

    public void addEntity( Asteroid a ) {
        this.asteroids.add( a );
        this.asteroidTree.insert( a );
    }

    public void addEntity( EnemyShip e ) {
        this.enemyShips.add( e );
        this.enemyShipTree.insert( e );
    }

    public void addEntity( Portal p ) {
        this.portal = p;
    }

    public void addParticle( Particle p ) {
        this.particles.add( p );
    }

    private void generateAsteroid( GamePane pane, long delta ) {
        if ( this.asteroidTimer > 3000 ) {
            this.addEntity( new Asteroid( 
                        World.rnd.nextInt( pane.getWidth() - 100 ), 
                        -100, 100, 100 ) 
            ); 
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
        this.background = Bitmap.loadImage( "back" + number + ".jpg" );
    }
}
