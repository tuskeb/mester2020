package hu.csanyzeg.master.Video;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class VideoActor extends OneSpriteStaticActor {
    public static String asset =  "badlogic.jpg";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(asset);
    }


    public VideoActor(MyGame game) {
        super(game, asset);
        setSize(1,1);
    }
}
