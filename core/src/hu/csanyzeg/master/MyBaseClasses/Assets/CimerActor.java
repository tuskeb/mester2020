package hu.csanyzeg.master.MyBaseClasses.Assets;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class CimerActor  extends OneSpriteStaticActor {
    public static String cimerHash = "loadingscreen/cimer.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(cimerHash).protect = true;
    }

    public CimerActor(MyGame game) {
        super(game, cimerHash);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (elapsedTime < 0.5){
            sprite.setAlpha(elapsedTime * 2);
        }
        if (elapsedTime  < 1.5f) {
            sprite.setScale(0.8f + (float) Math.abs(Math.sin(elapsedTime) / 2 ) * 0.3f);
        }
    }
}
