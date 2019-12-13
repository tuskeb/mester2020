package hu.csanyzeg.master.Demos.Box2dHelper;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class Box2dScreen extends MyScreen {
    public Box2dScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new Box2dStage(game), 1, true);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        AssetList.collectAssetDescriptor(Box2dStage.class,assetList);
        return assetList;
    }

    @Override
    public void init() {

    }
}
