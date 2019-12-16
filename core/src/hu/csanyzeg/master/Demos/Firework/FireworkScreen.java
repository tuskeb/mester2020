package hu.csanyzeg.master.Demos.Firework;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class FireworkScreen extends MyScreen {
    public FireworkScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new FireworkStage(game),1,true);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        AssetList.collectAssetDescriptor(FireworkStage.class, assetList);
        return assetList;
    }

    @Override
    public void init() {

    }
}
