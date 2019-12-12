package hu.csanyzeg.master.Demos;

import hu.csanyzeg.master.Demos.DemoLoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.Demos.DemoMenu.MenuScreen;
import hu.csanyzeg.master.Demos.DemoSzakkor.SzakkorScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class DemoMyGame extends MyGame {

	@Override
	public void create () {
		super.create();
		setLoadingStage(new DemoLoadingStage(this));
		setScreen(new MenuScreen(this));
		//setScreen(new SzakkorScreen(this));
	}

}
