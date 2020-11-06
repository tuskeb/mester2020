package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.B2DVideo.BGame;
import hu.csanyzeg.master.Video.VideoGame;

public class Video {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new BGame(true), config);

        config.width = 1000;
        config.height = 600;
    }
}
