package hu.csanyzeg.master.Demos.DemoActor;

import com.badlogic.gdx.graphics.Color;

import hu.csanyzeg.master.Demos.DemoMenu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.MyAssetManager;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class ActorScreen extends MyScreen {
    public ActorScreen(MyGame game) {
        super(game);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();

        MyAssetManager.collectAssetDescriptor(ActorStage.class, assetList);

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
