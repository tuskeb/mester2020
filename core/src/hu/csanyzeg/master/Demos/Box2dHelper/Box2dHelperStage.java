package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.LoadingStage.DemoPreLoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class Box2dHelperStage extends hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage {

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(BoxActor.class, assetList);
        AssetList.collectAssetDescriptor(WallActor.class, assetList);
        AssetList.collectAssetDescriptor(BallActor.class, assetList);
        AssetList.collectAssetDescriptor(UfoActor.class, assetList);
    }

    java.util.Random random = new Random();

    public Box2dHelperStage(final MyGame game) {
        super(new ExtendViewport(16, 9), game);
        addBackButtonScreenBackByStackPopListenerWithPreloadedAssets(new DemoPreLoadingStage(game));

        setCameraResetToLeftBottomOfScreen();

        final WorldBodyEditorLoader loader = new WorldBodyEditorLoader("box2dhelper/teszt.json");

        addActor(new UfoActor(game, world, loader, 7, 7, 2, 2));

        //addActor(new WallActor(game, world, 0, getViewport().getWorldHeight() - 1, 16, 1, 0));
        addActor(new WallActor(game, world, 0, 0, 16, 1, 0));
        addActor(new WallActor(game, world, -7.5f, 7.5f, 16, 1, 90));
        addActor(new WallActor(game, world, 7.5f, 7.5f, 16, 1, 90));

        for (int i = 0; i < 10; i++) {
            addActor(new BoxActor(game, world, random.nextFloat() * 14 + 2, random.nextFloat() * 4 + 4, random.nextFloat() + 0.5f, random.nextFloat() * 360));
        }

        addActor(new BallActor(game, world, 9, 6, 1));


        addTimer(new TickTimer(1, true, new TickTimerListener() {
                       @Override
            public void onTick(Timer sender, float correction) {
                addActor(new BoxActor(game, world, random.nextFloat() * 14 + 2, random.nextFloat() * 4 + 4, random.nextFloat() + 0.5f, random.nextFloat() * 360));
            }

        }));


        addTimer(new TickTimer(10.5f, true, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                addActor(new BallActor(game, world, random.nextFloat() * 14 + 2, random.nextFloat() * 4 + 4, 1));
            }

        }));


        addTimer(new TickTimer(7.5f, true, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                addActor(new UfoActor(game, world, loader, random.nextFloat() * 14 + 2, random.nextFloat() * 4 + 4, 2, 2));
            }
        }));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.setGravity(new Vector2(Gdx.input.getAccelerometerY()*7,-9.81f-Gdx.input.getAccelerometerX()*5));
    }
}
