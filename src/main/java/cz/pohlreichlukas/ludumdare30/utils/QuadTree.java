package cz.pohlreichlukas.ludumdare30.utils;

import java.awt.Rectangle;
import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.entities.Entity;

public class QuadTree<T extends Entity> {
    
    private final static int MAX_NODES = 2;

    private Rectangle region;
    private ArrayList<T> entities;

    /*
     * NW, NE
     * SW, SE
     */
    private QuadTree<T> northWest;
    private QuadTree<T> northEast;
    private QuadTree<T> southWest;
    private QuadTree<T> southEast;


    public QuadTree( Rectangle region ) {
        this.region = region;
        this.entities = new ArrayList<T>( QuadTree.MAX_NODES ); 
        this.northWest = this.northEast = this.southWest = this.southEast = null;
    }

    public QuadTree( int x, int y, int width, int height ) {
        this( new Rectangle( x, y, width, height ) );
    }

    public void insert( T entity ) {
        Rectangle bb = entity.getBoundingBox();
        int x = (int) ( bb.getX() + bb.getWidth() / 2 );
        int y = (int) ( bb.getY() + bb.getHeight() / 2 );
        if ( !this.region.contains( x, y ) ) {
            return;
        }

        if ( this.entities.size() < QuadTree.MAX_NODES && this.northWest == null ) {
            this.entities.add( entity );
        } else {
            this.subdivide();
            this.northWest.insert( entity );
            this.northEast.insert( entity );
            this.southWest.insert( entity );
            this.southEast.insert( entity );
        }
    }

    public void subdivide() {
        int x = (int) this.region.getX();
        int y = (int) this.region.getY();
        int width = (int) this.region.getWidth();
        int height = (int) this.region.getHeight();
        Rectangle northWest = new Rectangle( x, y, width / 2, height / 2 );
        Rectangle northEast = new Rectangle( x + width / 2, y, width / 2, height / 2 );
        Rectangle southWest = new Rectangle( x, y + height / 2, width / 2, height / 2 );
        Rectangle southEast = new Rectangle( x + width / 2, y + height / 2, width / 2, height / 2 );
        this.northWest = new QuadTree<T>( northWest );
        this.northEast = new QuadTree<T>( northEast );
        this.southWest = new QuadTree<T>( southWest );
        this.southEast = new QuadTree<T>( southEast );
    }

    public ArrayList<T> getEntities( Rectangle retrieveRegion ) {
        ArrayList<T> retrievedEntities = new ArrayList<T>();

        if ( !this.region.intersects( retrieveRegion ) ) { 
            return retrievedEntities;
        }

        for ( T entity : this.entities ) {
            if ( entity.getBoundingBox().intersects( retrieveRegion ) ) {
                retrievedEntities.add( entity );
            }
        }

        if ( this.northWest != null ) {
            retrievedEntities.addAll( this.northWest.getEntities( retrieveRegion ) );
            retrievedEntities.addAll( this.northEast.getEntities( retrieveRegion ) );
            retrievedEntities.addAll( this.southWest.getEntities( retrieveRegion ) );
            retrievedEntities.addAll( this.southEast.getEntities( retrieveRegion ) );
        }

        return retrievedEntities;
    }

    public void clear() {
        this.entities.clear();

        if ( this.northWest != null ) {
            this.northWest.clear();
            this.northEast.clear();
            this.southWest.clear();
            this.southEast.clear();
        }

        this.northWest = this.northEast = this.southWest = this.southEast = null;
    }

    public Rectangle getRegion() {
        return this.region;
    }

    public QuadTree getNorthWest() {
        return this.northWest;
    }

    public QuadTree getNorthEast() {
        return this.northEast;
    }

    public QuadTree getSouthWest() {
        return this.southWest;
    }

    public QuadTree getSouthEast() {
        return this.southEast;
    }
}
