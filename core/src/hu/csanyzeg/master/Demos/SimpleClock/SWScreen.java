package hu.csanyzeg.master.Demos.SimpleClock;

import hu.csanyzeg.master.Demos.Box2dJoin.ControlStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class SWScreen extends MyScreen {
    public SWScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        SWStage simpleWorldStage;
        simpleWorldStage = new SWStage(game);
        addStage(simpleWorldStage,1,true);

    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        AssetList.collectAssetDescriptor(SWStage.class, assetList);
        AssetList.collectAssetDescriptor(ControlStage.class, assetList);
        return assetList;
    }

    @Override
    public void init() {

    }
}
