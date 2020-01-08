package hu.csanyzeg.master.MyBaseClasses.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Map;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public abstract class LoadingStage extends SimpleWorldStage implements AssetCollector {

    public LoadingStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        game.getMyAssetManager().loadAsset(getAssetList());
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

    public void show(){
        getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void hide() {
    }
}
