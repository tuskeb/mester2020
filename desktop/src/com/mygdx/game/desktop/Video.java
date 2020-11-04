package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.Demos.DemoMyGame;
import hu.csanyzeg.master.TimerTest.TimerTest;
import hu.csanyzeg.master.Video.VideoGame;

public class Video {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new TimerTest(), config);

        config.width = 60;
        config.height = 60;
    }
}
