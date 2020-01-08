package hu.csanyzeg.master.Demos.LoadingStage;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class CircleActor extends OneSpriteStaticActor {
    public static String tectureHash = "loadingscreen/circle.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(tectureHash).protect = true;
    }

    public CircleActor(MyGame game) {
        super(game, tectureHash);
    }

}
