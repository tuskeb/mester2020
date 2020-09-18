package hu.csanyzeg.master.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class Start extends MyGame {
    public Start() {
        super();
    }


    @Override
    public void create() {
        super.create();
        Kepernyo kepernyo = new Kepernyo(this);
        setScreen(kepernyo);
    }
}
