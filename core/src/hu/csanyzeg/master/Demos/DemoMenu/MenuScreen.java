package hu.csanyzeg.master.Demos.DemoMenu;

import com.badlogic.gdx.graphics.Color;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    public void init() {
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        assetList.addFont(MenuButton.fontHash, null, 20, Color.WHITE).protect = true;
        assetList.addTexture(MenuButton.downHash, null).protect = true;
        assetList.addTexture(MenuButton.upHash, null).protect = true;
        assetList.addTexture(MenuButton.overHash, null).protect = true;
        return  assetList;
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new MenuStage(game), 1, true);
    }
}
