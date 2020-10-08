package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.Demos.DemoMyGame;
import hu.csanyzeg.master.Kerulgetosjatek.NewGame;

public class KerulgetosJatek {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new NewGame(false), config);

        config.width = 512;
        config.height = 800;
    }
}
