package cz.pohlreichlukas.ludumdare30.screens;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import cz.pohlreichlukas.ludumdare30.GamePane;
import cz.pohlreichlukas.ludumdare30.input.InputListener;

public class MainScreen implements Screen {

    private static final int FONT_SIZE = 50;

    private String[] options = { "Start game", "Quit" };
    private int selectedOption;
    private Font optionFont;
    private Font authorFont;
    private String authorString;
    private int timeout;

    public MainScreen() {
        this.selectedOption = 1;
        this.optionFont = new Font( Font.SANS_SERIF, Font.PLAIN, MainScreen.FONT_SIZE );
        this.authorFont = new Font( Font.SANS_SERIF, Font.PLAIN, MainScreen.FONT_SIZE / 3 );
        this.authorString = "Lukas Pohlreich 2014 - http://github.com/polarkac/ludum-dare-30";
        this.timeout = 0;
    }
    
    public void render( GamePane pane, Graphics2D g ) {
        g.setColor( Color.black );
        g.fillRect( 0, 0, pane.getWidth(), pane.getHeight() );

        g.setColor( Color.white );
        g.setFont( this.optionFont );
        int totalHeight = this.options.length * ( MainScreen.FONT_SIZE + 25 ) - 90;
        for ( int a = 0; a < this.options.length; a++ ) {
            int stringWidth = g.getFontMetrics( this.optionFont ).stringWidth( this.options[a] );
            g.drawString( 
                    this.options[a], 
                    pane.getWidth() / 2 - stringWidth / 2, 
                    ( MainScreen.FONT_SIZE + 20 ) * a + ( pane.getHeight() / 2 - totalHeight / 2 ) 
            );
            if ( this.selectedOption == a ) {
                g.drawLine( 
                        pane.getWidth() / 2 - stringWidth / 2,
                        ( MainScreen.FONT_SIZE + 25 ) * a + ( pane.getHeight() / 2 - totalHeight / 2 ),
                        pane.getWidth() / 2 + stringWidth / 2,
                        ( MainScreen.FONT_SIZE + 25 ) * a + ( pane.getHeight() / 2 - totalHeight / 2 )
                );
            }
        }
        g.setFont( this.authorFont );
        int authorStringWidth = g.getFontMetrics( this.authorFont ).stringWidth( this.authorString );
        g.drawString( 
                this.authorString,
                pane.getWidth() / 2 - authorStringWidth / 2,
                pane.getHeight() - MainScreen.FONT_SIZE / 3
        );

    }

    public void update( GamePane pane, long delta ) {
        pane.showCursor();
        Point mousePosition = pane.getMousePosition();
        if ( mousePosition != null ) {
            if ( mousePosition.getY() > pane.getHeight() / 2 ) {
                this.selectedOption = 1;
            } else if ( mousePosition.getY() < pane.getHeight() / 2 ) {
                this.selectedOption = 0;
            }
        }

        InputListener input = pane.getInput();
        if ( input.isLeftDown() && this.timeout <= 0 ) {
            System.out.println( "LEFT CLICK" );
            switch( this.selectedOption ) {
                case 0:
                    pane.changeToGame(); break;
                case 1:
                    pane.quitGame(); break;
            }
        }
        if ( this.timeout > 0 ) {
            this.timeout -= delta;
        }
    }

    public void setTimeout( int timeout ) {
        this.timeout = timeout;
    }

}
