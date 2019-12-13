package hu.csanyzeg.master.Demos.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class BlActor extends OneSpriteStaticActor {
    public static final String textureHash = "badlogic.jpg";

    public BlActor(MyGame game) {
        super(game, textureHash);
    }
}
