package cz.pohlreichlukas.ludumdare30.sounds;

import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Exception;

public class Sound {

    private static ArrayList<Clip> clips = new ArrayList<Clip>();
    static {
        for ( int a = 0; a < 5; a++ ) {
            try {
                clips.add( AudioSystem.getClip() );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    private static AudioInputStream hit = Sound.loadSound( "hit.wav" );

    private static AudioInputStream loadSound( String name ) {
        AudioInputStream inputStream = null;
        try {
            inputStream = AudioSystem.getAudioInputStream(
                Sound.class.getResourceAsStream("/sounds/" + name )
            ); 
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return inputStream;
    }

    public static void playHit() {
        for ( Clip clip : Sound.clips ) {
            if ( !clip.isActive() ) {
                Sound.play( Sound.hit, clip );
                break;
            }
        }
    }

    private static void play( final AudioInputStream inputStream, final Clip clip ) {
        new Thread( new Runnable() { 
            public void run() {
                try {
                    clip.open( inputStream );
                    clip.stop();
                    clip.setFramePosition( 0 );
                    clip.setMicrosecondPosition( 0 );
                    clip.start(); 
                    clip.close();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
