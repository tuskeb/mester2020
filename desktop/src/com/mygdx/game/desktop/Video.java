package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.Demos.DemoMyGame;
import hu.csanyzeg.master.Video.VideoGame;

public class Video {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new VideoGame(), config);

        config.width = 1380;
        config.height = 720;
    }
}
