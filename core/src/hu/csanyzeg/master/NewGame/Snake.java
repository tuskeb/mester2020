package hu.csanyzeg.master.NewGame;

import hu.csanyzeg.master.Demos.SimpleUI.SimpleUIScreen;
import hu.csanyzeg.master.Demos.SpaceInvaders.SpaceScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class Snake extends MyGame {

    public Snake() {
        debug = true;
    }

    @Override
    public void create() {
        super.create();
        //setScreen(new SpaceScreen(this));
        setScreen(new SimpleUIScreen(this));
    }
}
