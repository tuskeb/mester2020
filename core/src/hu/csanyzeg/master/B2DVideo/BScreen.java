package hu.csanyzeg.master.B2DVideo;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class BScreen extends MyScreen {
    public BScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new BStage(game), 0, true);
    }

    @Override
    public AssetList getAssetList() {
        return null;
    }
}
