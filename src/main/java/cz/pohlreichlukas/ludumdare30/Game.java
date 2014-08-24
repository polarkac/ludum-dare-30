package cz.pohlreichlukas.ludumdare30;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.lang.Thread;

public class Game {

    public static final boolean IS_DEBUG = false;
    
    public static void main( String[] args ) {
        GamePane gamePane = new GamePane();
        JFrame frame = new JFrame( "Ludum Dare 30" );
        frame.setResizable( false );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setLayout( new BorderLayout() );
        frame.add( gamePane, BorderLayout.CENTER );
        frame.pack();
        
        Thread gameThread = new Thread( gamePane );
        gameThread.start();
    }
}
