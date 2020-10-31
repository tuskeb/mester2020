package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;

public class BoxActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public BoxActor(MyGame game, World world, float x, float y, float size, float rotation) {
        super(game, boxTexture);
        setSize(size,size);
        setPosition(x,y);
        setRotation(rotation);
        setActorWorldHelper(new Box2DWorldHelper(world, this, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.DynamicBody));
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setPosition(getX(),9);
            }
        });

        addTimer(new IntervalTimer(1f, new IntervalTimerListener() {
            @Override
            public void onRepeat(IntervalTimer sender) {

            }

            @Override
            public void onTick(IntervalTimer sender, float correction) {
                setAlpha(sender.getElapsedTime());
            }

            @Override
            public void onStop(IntervalTimer sender) {
                setAlpha(1f);
                sender.remove();
            }

            @Override
            public void onStart(IntervalTimer sender) {
                setAlpha(0);
            }
        } ));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void contactWithBall(){
        if (!protect) {
            setSize(getWidth() * 0.9f, getHeight() * 0.9f);
            if (getWidth() < 0.05) {
                remove();
            }
            setFlash();
        }
    }

    private boolean protect = false;

    public void setFlash(){
        addTimer(new IntervalTimer(0.85f, new IntervalTimerListener() {
            @Override
            public void onRepeat(IntervalTimer sender) {

            }

            @Override
            public void onTick(IntervalTimer sender, float correction) {
                setColor(1, sender.getElapsedTime()*2f, sender.getElapsedTime()*2f,1);
            }

            @Override
            public void onStop(IntervalTimer sender) {
                setColor(1,1,1,1);
                sender.remove();
                protect = false;
            }

            @Override
            public void onStart(IntervalTimer sender) {
                protect = true;
            }
        }));
    }
}
