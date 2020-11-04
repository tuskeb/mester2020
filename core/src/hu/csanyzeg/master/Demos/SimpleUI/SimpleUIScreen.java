package hu.csanyzeg.master.Demos.SimpleUI;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class SimpleUIScreen extends MyScreen {
    public SimpleUIScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new SimpleUIStage(game), 0, true);
    }

    @Override
    public AssetList getAssetList() {
        return null;
    }
}
