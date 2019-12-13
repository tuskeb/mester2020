package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
        AssetList.collectAssetDescriptor(WallActor.class, assetList);
    }

    private World world;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    public Box2dStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        world  = new World(new Vector2(0,-9.81f), false);
        addActor(new BoxActor(game, world,9,9,0));
        addActor(new BoxActor(game, world,8.2f,5,0));
        addActor(new WallActor(game, world,0,0,16,1,0));
    }

    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        world.step(delta,5,5);
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
        box2DDebugRenderer.render(world, getCamera().combined);

    }
}
