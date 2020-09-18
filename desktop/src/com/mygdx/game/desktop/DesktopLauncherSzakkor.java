package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanyzeg.master.Demos.DemoMyGame;
import hu.csanyzeg.master.Szakkor.Start;

public class DesktopLauncherSzakkor {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Start(), config);

		config.width = 1280;
		config.height = 720;
	}
}
