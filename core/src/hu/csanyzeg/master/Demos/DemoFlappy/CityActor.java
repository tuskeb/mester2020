package hu.csanyzeg.master.Demos.DemoFlappy;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class CityActor extends OneSpriteStaticActor {
    public static final String hatterTexture = "demoflappy/wallpaper.jpg";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(hatterTexture);
    }

    public CityActor(MyGame game) {
        super(game, hatterTexture);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX()-5);
        if(getX() < -getWidth()) setX(getWidth());
    }
}
