package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.NewGame.Snake;

public class LifeGame {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new hu.csanyzeg.master.Demos.GameOfLife.LifeGame(), config);

        config.width = 1280;
        config.height = 720;
    }
}
