package hu.csanyzeg.master.Demos.Demo2;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class DemoScr extends MyScreen {
    public DemoScr(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new DemoStage(game), 1, true);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        AssetList.collectAssetDescriptor(DemoStage.class, assetList);
        return assetList;
    }

    @Override
    public void init() {

    }
}
