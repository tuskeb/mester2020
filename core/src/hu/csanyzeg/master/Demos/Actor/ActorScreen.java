package hu.csanyzeg.master.Demos.Actor;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class ActorScreen extends MyScreen {
    public ActorScreen(MyGame game) {
        super(game);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();

        AssetList.collectAssetDescriptor(ActorStage.class, assetList);

        return assetList;
    }

    @Override
    public void init() {

    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new ActorStage(game), 1, true);
    }
}
