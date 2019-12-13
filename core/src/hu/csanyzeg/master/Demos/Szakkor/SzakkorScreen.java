package hu.csanyzeg.master.Demos.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class SzakkorScreen extends MyScreen {


    public SzakkorScreen(MyGame game) {
        super(game);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        assetList.addTexture(BlActor.textureHash);
        return assetList;
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new BlStage(game), 1, true);
    }

    @Override
    public void init() {

    }
}
