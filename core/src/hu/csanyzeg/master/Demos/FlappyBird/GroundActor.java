package hu.csanyzeg.master.Demos.FlappyBird;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class GroundActor extends OneSpriteStaticActor {
    public static final String groundTexture = "demoflappy/ground.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(groundTexture);
    }

    public GroundActor(MyGame game) {
        super(game, groundTexture);
        setTouchable(null);
    }
}
