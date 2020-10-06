package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.NewGame.Snake;
import hu.csanyzeg.master.Szakkor.Start;

public class SnakeStart {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Snake(), config);

        config.width = 1028;
        config.height = 640;
    }
}
