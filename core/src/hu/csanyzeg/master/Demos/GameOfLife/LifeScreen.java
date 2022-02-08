package hu.csanyzeg.master.Demos.GameOfLife;

import hu.csanyzeg.master.Demos.SimpleUI.SimpleUIScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class LifeScreen extends MyScreen {
    public LifeScreen(MyGame game) {
        super(game);
    }

    @Override
    public AssetList getAssetList() {
        return null;
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new LifeStage(game), 0,true);
    }
}
