package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.EventListener;
import java.util.Random;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class UfoActor extends OneSpriteStaticActor {
    public static final String ufoTexture = "box2dhelper/ufo.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(ufoTexture);
    }

    public static java.util.Random random = new Random();


    public UfoActor(MyGame game, World world, WorldBodyEditorLoader loader, float x, float y, float w, float h) {
        super(game, ufoTexture);
        setSize(w,h);
        setPosition(x,y);
        setActorWorldHelper(new Box2DWorldHelper(world, this, loader, "ufo.png", new MyFixtureDef(), BodyDef.BodyType.DynamicBody));

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getTimers().clear();
                addTimer(new IntervalTimer(0.9f, new IntervalTimerListener() {

                    @Override
                    public void onTick(IntervalTimer sender, float correction) {
                        setAlpha(1-sender.getElapsedTime());
                    }

                    @Override
                    public void onStop(IntervalTimer sender) {
                        sender.remove();
                        remove();
                    }

                    @Override
                    public void onStart(IntervalTimer sender) {
                        getListeners().clear();
                    }
                }));
            }
        });
        addTimer(new TickTimer(0.5f, true, new TickTimerListener() {
            @Override
            public void onTick(Timer sender, float correction) {
                setColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
            }

        }));
    }
}
