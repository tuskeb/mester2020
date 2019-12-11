package hu.csanyzeg.master.MyBaseClasses.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Map;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public abstract class LoadingStage extends MyStage {

    protected MyAssetManager assetManager;

    public LoadingStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        assetManager = new MyAssetManager();
        assetManager.loadAsset(getAssetList());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        game.getMyAssetManager().updateManager();
    }

    public int getPercent(){
        return game.getMyAssetManager().getProgress();
    }

    public String getActualLoadingName(){
        return game.getMyAssetManager().getActualLoadingName();
    }

    public abstract AssetList getAssetList();

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }
}
