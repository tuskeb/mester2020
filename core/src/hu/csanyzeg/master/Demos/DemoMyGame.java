package hu.csanyzeg.master.Demos;

import com.badlogic.gdx.Gdx;

import hu.csanyzeg.master.Demos.LoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.Demos.Menu.MenuScreen;
import hu.csanyzeg.master.Demos.SimpleClock.SWScreen;
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
		//debug = true;
		setScreen(new MenuScreen(this));

		//setScreen(new SWScreen(this));
		//setScreen(new SWScreen(this));
		//setScreen(new Box2dJoinScreen(this));
		//setScreen(new FlappyScreen(this));
	}

}
