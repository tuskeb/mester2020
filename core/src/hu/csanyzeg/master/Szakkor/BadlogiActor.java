package hu.csanyzeg.master.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class BadlogiActor extends OneSpriteStaticActor {
    public BadlogiActor(MyGame game) {
        super(game, "badlogic");
        setSize(1,1);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX()+0.1f);
    }
}
