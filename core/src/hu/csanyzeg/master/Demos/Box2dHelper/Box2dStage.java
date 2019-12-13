package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class Box2dStage extends MyStage {

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(BoxActor.class, assetList);
    }

    private World world;

    public Box2dStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        world  = new World(new Vector2(1,1), false);
        addActor(new BoxActor(game, world,3,3,0));
    }

    @Override
    public void init() {

    }

}
