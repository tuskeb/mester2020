package hu.csanyzeg.master.Demos.LoadingStage;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class CimerActor  extends OneSpriteStaticActor {
    public static String cimerHash = "loadingscreen/cimertransparent.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(cimerHash).protect = true;
    }

    public CimerActor(MyGame game) {
        super(game, cimerHash);
    }

}
