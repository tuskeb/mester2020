package hu.csanyzeg.master.Kerulgetosjatek;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class NewScreen extends MyScreen {
    public NewScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new NewStage(game), 0, true);
    }

    @Override
    public AssetList getAssetList() {
        return null;
    }

    @Override
    public void init() {

    }
}
