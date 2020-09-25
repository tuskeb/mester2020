package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Demos.Box2dJoin.ControlStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class SpaceScreen  extends MyScreen {
    public SpaceScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new SpaceStage(game), 0, true);
        addStage(new SpaceControlStage(game), 1, false);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        AssetList.collectAssetDescriptor(SpaceScreen.class, assetList);
        AssetList.collectAssetDescriptor(SpaceControlStage.class, assetList);
        return assetList;
    }

    @Override
    public void init() {

    }
}
