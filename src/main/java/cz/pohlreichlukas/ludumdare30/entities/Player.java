package cz.pohlreichlukas.ludumdare30.entities;

import java.util.ArrayList;

import cz.pohlreichlukas.ludumdare30.components.PlayerRendererComponent;
import cz.pohlreichlukas.ludumdare30.components.PlayerInputComponent;
import cz.pohlreichlukas.ludumdare30.entities.Bullet;

public class Player extends Entity {

    public Player() {
        super( 0, 0 );
        this.renderer = new PlayerRendererComponent();
        this.input = new PlayerInputComponent();
    }
}
