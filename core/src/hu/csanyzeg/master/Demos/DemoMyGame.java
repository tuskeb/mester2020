package hu.csanyzeg.master.Demos;

import hu.csanyzeg.master.Demos.Box2dJoin.Box2dJoinScreen;
import hu.csanyzeg.master.Demos.LoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.Demos.Menu.MenuScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class DemoMyGame extends MyGame {

	public DemoMyGame(boolean debug) {
		super(debug);
	}

	public DemoMyGame() {
	}

	@Override
	public void create () {
		super.create();
		setLoadingStage(new DemoLoadingStage(this));
		setScreen(new MenuScreen(this));
		//setScreen(new Box2dJoinScreen(this));
		//setScreen(new Box2dHelperStage(this));
		//setScreen(new FlappyScreen(this));
	}

}
