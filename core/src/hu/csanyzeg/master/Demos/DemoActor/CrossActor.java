package hu.csanyzeg.master.Demos.DemoActor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class CrossActor extends OneSpriteStaticActor {
    public static String textureHash = "BADLOGIC_TEXTURE";

    public CrossActor(MyGame game) {
        super(game, textureHash);
        setPosition(300,100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX()+1f, getY()+1f);
    }
}
