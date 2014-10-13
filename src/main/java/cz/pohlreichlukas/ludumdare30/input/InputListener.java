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
    private boolean lockMouse;

    public InputListener( GamePane pane ) {
        this.leftIsDown = false;
        this.rightIsDown = false;
        this.lastMouseX = this.lastMouseY = 0;
        this.mouseX = this.mouseY = 0;
        this.lockMouse = false;
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
        int deltaX = e.getX() - this.pane.getWidth() / 2;
        int deltaY = e.getY() - this.pane.getHeight() / 2;
        this.mouseX = this.mouseX + deltaX;
        this.mouseY = this.mouseY + deltaY;

        if ( this.mouseX > this.pane.getWidth() ) {
            this.mouseX = this.pane.getWidth();
        } else if ( this.mouseX < 0 ) {
            this.mouseX = 0;
        }
        if ( this.mouseY > this.pane.getHeight() ) {
            this.mouseY = this.pane.getHeight();
        } else if ( this.mouseY < 0 ) {
            this.mouseY = 0;
        }

        this.moveMouseToCenter();
    }

    public void mouseDragged( MouseEvent e ) {
        this.mouseMoved( e );
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

    public void setLockMouse( boolean isLocked ) {
        this.lockMouse = isLocked;
    }

    private void moveMouseToCenter() {
        if ( !this.lockMouse ) {
            return;
        }
        Point p = this.pane.getLocationOnScreen();
        int windowX = (int) p.getX();
        int windowY = (int) p.getY();
        this.robot.mouseMove(
                this.pane.getWidth() / 2 + windowX,
                this.pane.getHeight() / 2 + windowY
        );
    }

    public void mouseExited( MouseEvent e ) {}
    public void mouseClicked( MouseEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
}
