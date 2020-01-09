package hu.csanyzeg.master.Demos.Box2dHelper;

import hu.csanyzeg.master.Demos.FlappyBird.FlappyStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class Box2dHelperScreen extends MyScreen {
    public static AssetList assetList;
    static {
        assetList = new AssetList();
        AssetList.collectAssetDescriptor(Box2dHelperStage.class,assetList);
    }


    public Box2dHelperScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new Box2dHelperStage(game), 1, true);
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void init() {

    }
}
