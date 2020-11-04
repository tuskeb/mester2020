package hu.csanyzeg.master.TimerTest;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class TimerScreen extends MyScreen {

    public TimerScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new TimerStage(game),0, true);
    }

    @Override
    public AssetList getAssetList() {
        return null;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
