package hu.csanyzeg.master.Demos.SimpleWorld;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class SimpleBoxActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public SimpleBoxActor(MyGame game, SimpleWorld world, float x, float y, float w, float h, float rotation) {
        super(game, boxTexture);
        setSize(w, h);
        setRotation(rotation);
        setOrigin(0.3f,0.3f);
        setPosition(x,y);
        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Dinamic));
        //((SimpleWorldHelper)getActorWorldHelper()).body.addBaseCollisionCircleShape();
        ((SimpleWorldHelper)getActorWorldHelper()).body.addCollisionRectangleShape("rect", 0.2f,0.2f,0.5f,0.5f,10f);
        ((SimpleWorldHelper)getActorWorldHelper()).body.addCollisionCircleShape("circ", 0.2f,0.2f,0.25f);
        ((SimpleWorldHelper)getActorWorldHelper()).body.setAngularVelocity(10f);
        //((SimpleWorldHelper)getActorWorldHelper()).body.setLinearVelocity(0.1f,0.1f);
        //((SimpleWorldHelper)getActorWorldHelper()).body.setSizeVelocity(0.1f,0.1f);

        ((SimpleWorldHelper)getActorWorldHelper()).addContactListener(new SimpleBodyContactListener() {
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                setFlash();
            }

            @Override
            public void endContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                setFlash();
                //contactWithBall();
            }
        });

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setPosition(5,5);
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
                removeTimer(sender);
            }

            @Override
            public void onStart(IntervalTimer sender) {
                setAlpha(0);
            }
        } ));
        /*
        addTimer(new PermanentTimer(new PermanentTimerListener() {
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                setRotation(getRotation() + correction*10);
                //setX(getX()+correction);
            }

            @Override
            public void onStop(PermanentTimer sender) {

            }

            @Override
            public void onStart(PermanentTimer sender) {

            }
        }));*/
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
                removeTimer(sender);
                protect = false;
            }

            @Override
            public void onStart(IntervalTimer sender) {
                protect = true;
            }
        }));
    }
}
