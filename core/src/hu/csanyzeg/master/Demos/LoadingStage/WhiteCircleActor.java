package hu.csanyzeg.master.Demos.LoadingStage;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class WhiteCircleActor extends OneSpriteStaticActor {
    public static String tectureHash = "loadingscreen/whitecircle.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(tectureHash).protect = true;
    }

    public WhiteCircleActor(MyGame game) {
        super(game, tectureHash);
    }

}
