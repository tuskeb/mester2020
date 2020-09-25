package hu.csanyzeg.master.NewGame;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class SnakeScreen extends MyScreen {
    public SnakeScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new SnakeStage(game), 0, true);
    }

    @Override
    public AssetList getAssetList() {
        return null;
    }

    @Override
    public void init() {

    }
}
