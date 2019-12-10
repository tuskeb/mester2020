package hu.csanyzeg.master.Demos.DemoMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {

    public MenuScreen(MyGame game) {
        super(game);
        addStage(new MenuStage(game), 1, true);
    }

    @Override
    public void init() {
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        assetList.addFont(MenuButton.fontHash, null, 20, Color.WHITE);
        assetList.addTexture(MenuButton.downHash, null);
        assetList.addTexture(MenuButton.upHash, null);
        assetList.addTexture(MenuButton.overHash, null);
        return  assetList;
    }
}
