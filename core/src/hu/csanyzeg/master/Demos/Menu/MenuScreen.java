package hu.csanyzeg.master.Demos.Menu;

import hu.csanyzeg.master.Demos.FlappyBird.FlappyStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {
    public static AssetList assetList;
    static {
        assetList = new AssetList();
        AssetList.collectAssetDescriptor(MenuStage.class, assetList);
        AssetList.collectAssetDescriptor(BootStage.class, assetList);
    }

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    public void init() {
    }

    @Override
    public AssetList getAssetList() {
        return  assetList;
    }

    @Override
    protected void afterAssetsLoaded() {
        if(BootStage.firstBoot) addStage(new BootStage(game),2,false);
        else addStage(new MenuStage(game), 1, true);
    }
}
