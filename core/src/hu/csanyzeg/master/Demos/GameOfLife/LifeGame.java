package hu.csanyzeg.master.Demos.GameOfLife;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class LifeGame extends MyGame {

    @Override
    public void onCreate() {
        setScreen(new LifeScreen(this));
        debug = true;
    }
}
