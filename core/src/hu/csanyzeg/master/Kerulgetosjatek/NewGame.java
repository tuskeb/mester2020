package hu.csanyzeg.master.Kerulgetosjatek;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class NewGame extends MyGame {

    public NewGame(boolean debug) {
        super(debug);
    }

    public NewGame() {
    }

    @Override
    public void create() {
        super.create();
        setScreen(new NewScreen(this));
    }
}
