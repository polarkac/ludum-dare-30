package cz.pohlreichlukas.ludumdare30.input;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class InputListener implements MouseListener {

    private boolean leftIsDown;
    private boolean rightIsDown;

    public InputListener() {
        this.leftIsDown = false;
        this.rightIsDown = false;
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

    public boolean isLeftDown() {
        return this.leftIsDown;
    }

    public boolean isRightDown() {
        return this.rightIsDown;
    }

    public void mouseClicked( MouseEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
    public void mouseExited( MouseEvent e ) {}

}
