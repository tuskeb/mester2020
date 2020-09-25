package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class SpaceControlStage extends MyStage {

    public static AssetList assetList = new AssetList();
    static {
        //AssetList.collectAssetDescriptor(EnemyActor.class, assetList);
    }


    public SpaceControlStage(MyGame game) {
        super(new ExtendViewport(160, 90), game);
    }
}
