package cz.pohlreichlukas.ludumdare30.input;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Point;

import cz.pohlreichlukas.ludumdare30.GamePane;

public class InputListener implements MouseListener, MouseMotionListener {

    private boolean leftIsDown;
    private boolean rightIsDown;
    private int lastMouseX;
    private int lastMouseY;
    private int mouseX;
    private int mouseY;
    private GamePane pane;
    private Robot robot;

    public InputListener( GamePane pane ) {
        this.leftIsDown = false;
        this.rightIsDown = false;
        this.lastMouseX = this.lastMouseY = 0;
        this.mouseX = this.mouseY = 0;
        this.pane = pane;
        try {
            this.robot = new Robot();
        } catch ( AWTException e ) {
            e.printStackTrace();
        }
    }

    public void mousePressed( MouseEvent e ) {
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            this.leftIsDown = true;
        }
        if ( e.getButton() == MouseEvent.BUTTON2 ) {
            this.rightIsDown = true;
        }
    }

    public void mouseReleased( MouseEvent e ) { 
        if ( e.getButton() == MouseEvent.BUTTON1 ) {
            this.leftIsDown = false;
        }
        if ( e.getButton() == MouseEvent.BUTTON2 ) {
            this.rightIsDown = false;
        }
    }

    public void mouseMoved( MouseEvent e ) {
        this.lastMouseX = this.mouseX;
        this.lastMouseY = this.mouseY;
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    public void mouseDragged( MouseEvent e ) {
        this.mouseMoved( e );
    }

    public void mouseExited( MouseEvent e ) {
        Point p = this.pane.getLocationOnScreen();
        int panePosX = (int) p.getX();
        int panePosY = (int) p.getY();
        this.robot.mouseMove( this.lastMouseX + panePosX, this.lastMouseY + panePosY );
    }

    public boolean isLeftDown() {
        return this.leftIsDown;
    }

    public boolean isRightDown() {
        return this.rightIsDown;
    }

    public int getMouseX() {
        return this.mouseX;
    }

    public int getMouseY() {
        return this.mouseY;
    }

    public int getLastMouseX() {
        return this.lastMouseX;
    }

    public int getLastMouseY() {
        return this.lastMouseY;
    }

    public void mouseClicked( MouseEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
}
