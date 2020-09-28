package hu.csanyzeg.master.Video;

import com.badlogic.gdx.math.RandomXS128;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class LegoActor extends OneSpriteStaticActor {

    private static RandomXS128 randomXS128 = new RandomXS128();


    public LegoActor(MyGame game) {
        super(game, "Video/legoszurke.png");
        setWidthWhithAspectRatio(1);
        setColor(randomXS128.nextFloat(),randomXS128.nextFloat(),randomXS128.nextFloat(),1f);
    }
}
