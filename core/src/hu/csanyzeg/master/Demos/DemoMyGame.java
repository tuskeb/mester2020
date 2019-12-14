package hu.csanyzeg.master.Demos;

import hu.csanyzeg.master.Demos.Box2dHelper.Box2dScreen;
import hu.csanyzeg.master.Demos.FlappyBird.FlappyScreen;
import hu.csanyzeg.master.Demos.LoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.Demos.Menu.MenuScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class DemoMyGame extends MyGame {

	@Override
	public void create () {
		super.create();
		setLoadingStage(new DemoLoadingStage(this));
		//setScreen(new MenuScreen(this));
		setScreen(new Box2dScreen(this));
		//setScreen(new FlappyScreen(this));
	}

}
