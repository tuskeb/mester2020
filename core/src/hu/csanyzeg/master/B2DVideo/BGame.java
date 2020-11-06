package hu.csanyzeg.master.B2DVideo;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class BGame extends MyGame {
    public BGame(boolean debug) {
        super(debug);
    }

    @Override
    public void create() {
        super.create();
        setScreen(new BScreen(this));
    }
}
