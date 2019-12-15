package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
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
                remove();
            }
        });
        addTimer(new TickTimer(0.5f, true, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {

            }

            @Override
            public void onTick(Timer sender, float correction) {
                setColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));
    }
}
