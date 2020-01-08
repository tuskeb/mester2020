package hu.csanyzeg.master.Demos.Demo2;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class DemoStage extends SimpleWorldStage {
    public static AssetList assetList;
    static {
        assetList = new AssetList();
        AssetList.collectAssetDescriptor(DemoActor.class, assetList);
    }


    public DemoStage(MyGame game) {
        super(new ResponseViewport(720), game);
        DemoActor da = new DemoActor(game, world,0,0);
        addActor(da);
        DemoActor da2 = new DemoActor(game, world,400,200);
        addActor(da2);
    }
}
