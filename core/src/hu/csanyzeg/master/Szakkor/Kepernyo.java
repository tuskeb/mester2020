package hu.csanyzeg.master.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.MyAssetDescriptor;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class Kepernyo extends MyScreen {
    public Kepernyo(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new SecondStage(game), 0, true);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        assetList.addTexture("badlogic.jpg", "badlogic");
        return assetList;
    }

    @Override
    public void init() {

    }
}
