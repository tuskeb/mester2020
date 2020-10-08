package hu.csanyzeg.master.NewGame;

import hu.csanyzeg.master.Demos.SpaceInvaders.SpaceScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class Snake extends MyGame {

    public Snake() {
    }

    @Override
    public void create() {
        super.create();
        setScreen(new SpaceScreen(this));
    }
}
