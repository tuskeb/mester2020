package hu.csanyzeg.master.Demos.DemoMenu;

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
        AssetList.collectAssetDescriptor(MenuStage.class, assetList);
        return  assetList;
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new MenuStage(game), 1, true);
    }
}
