package cz.pohlreichlukas.ludumdare30;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet {

	private static final long serialVersionUID = 1L;
	private GamePane game = new GamePane();
	
	public void init() {
		this.setLayout( new BorderLayout() );
		this.add( this.game, BorderLayout.CENTER );
	}
	
	public void start() {
        Thread thread = new Thread( this.game ); 
        thread.start();
	}
	
	public void stop() {
        System.exit( 0 );
	}

}
