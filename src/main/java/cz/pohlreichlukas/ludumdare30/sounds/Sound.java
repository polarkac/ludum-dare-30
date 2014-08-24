package cz.pohlreichlukas.ludumdare30.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.lang.Exception;

public class Sound {
    
    private static Clip hitSound = Sound.loadSound( "hit.wav" );
    private static Clip fireSound = Sound.loadSound( "fire.wav" );

    private static Clip loadSound( String name ) {
        Clip clip = null;
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Sound.class.getResourceAsStream("/sounds/" + name )
            );
            clip = AudioSystem.getClip();
            clip.open( inputStream );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return clip;
    }

    public static void playHit() {
        Sound.play( Sound.hitSound );
    }

    public static void playFire() {
        Sound.play( Sound.fireSound );
    }

    private static void play( final Clip clip ) {
        new Thread( new Runnable() { 
            public void run() {
                try {
                    synchronized( clip ) {
                        clip.stop();
                        clip.setFramePosition( 0 );
                        clip.start(); 
                        System.out.println( "SOUND" );
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
